package com.baoshi.wcs.web.controller;


import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baoshi.wcs.common.FilePath;
import com.baoshi.wcs.common.listener.ShipperCartonReadExcelListener;
import com.baoshi.wcs.common.response.WCSApiResponse;
import com.baoshi.wcs.entity.Shelves;
import com.baoshi.wcs.entity.ShipperCarton;
import com.baoshi.wcs.service.ShipperCartonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.baoshi.wcs.web.basic.BaseController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.SocketException;
import java.util.UUID;

/**
货主纸箱 数据管理
 * @author jobob
 * @since 2019-10-08
 */
@RestController
@RequestMapping("/shipper_carton")
public class ShipperCartonController extends BaseController {

    @Autowired
    ShipperCartonService shipperCartonService;

    @RequestMapping(value = "/import/excel")
    public Object upload(MultipartFile file, HttpServletRequest request) throws SocketException, IOException {
        WCSApiResponse<Object> res = new WCSApiResponse<>();
        // 获取文件名
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf(".")+1);
        if((!"xls".equals(suffixName)) && (!"xlxs".equals(suffixName)) ){
            res.failed("文件格式错误!");
        }
        File excelFilePath = new File(FilePath.shipperCartonExcelFilePath);
        if(!excelFilePath.exists()){
            excelFilePath.mkdirs();
        }
        String newFileName = FilePath.shipperCartonExcelFilePath+file.getOriginalFilename();
        file.transferTo(new File(newFileName));

        EasyExcel.read(newFileName, ShipperCarton.class, new ShipperCartonReadExcelListener()).sheet().doRead();

        res.success(null,"上传成功");
        return res;
    }

    @GetMapping("/list")
    public Object list(Page<ShipperCarton> page){
        WCSApiResponse<Object> res = new WCSApiResponse<>();
        IPage<ShipperCarton> shipperCartonIPage = shipperCartonService.page(page, new QueryWrapper<ShipperCarton>());
        res.setData(shipperCartonIPage);
        return res;
    }

}
