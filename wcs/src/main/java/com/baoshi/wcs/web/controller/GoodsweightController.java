package com.baoshi.wcs.web.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baoshi.wcs.common.response.WCSApiResponse;
import com.baoshi.wcs.entity.GoodsWeight;
import com.baoshi.wcs.pojo.GoodsWeight4ExcelExportPOJO;
import com.baoshi.wcs.service.GoodsWeightService;
import com.baoshi.wcs.vo.GoodsWeightSearchVO;
import com.baoshi.wcs.vo.GoodsWeightVO;
import com.baoshi.wcs.web.basic.BaseController;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/goodsweight")
public class GoodsweightController extends BaseController {

    @Autowired
    GoodsWeightService goodsWeightService;

    @GetMapping("/list")
    @ResponseBody
    public Object all(Page<GoodsWeightSearchVO> page, GoodsWeightVO goodsWeightVO){

        WCSApiResponse<Object> res = new WCSApiResponse<>();
        Page<GoodsWeight> gwPage = new Page<>();
        BeanUtils.copyProperties(page,gwPage);
        gwPage.setDesc("modify_time");

        QueryWrapper<GoodsWeight> gwQuerryWrapper = new QueryWrapper<>();
//        gwQuerryWrapper.ne("weight",0.00d);
        gwQuerryWrapper.gt("version",0);
        if(goodsWeightVO!=null){
            if(!StringUtils.isEmpty(goodsWeightVO.getBarCode())){
                //查询快递单号
                gwQuerryWrapper.eq("bar_code",goodsWeightVO.getBarCode());
            }
            if(!StringUtils.isEmpty(goodsWeightVO.getId())){
                //根据机器id查询
                gwQuerryWrapper.eq("gw_robot_id",goodsWeightVO.getId());
            }
            if(goodsWeightVO.getBeginTime()!=null && goodsWeightVO.getEndTime()!=null){
                gwQuerryWrapper.between("modify_time",goodsWeightVO.getBeginTime(),goodsWeightVO.getEndTime());
            }
        }

        IPage<GoodsWeight> resPage = goodsWeightService.page(gwPage, gwQuerryWrapper);
        res.success(resPage);
        return res;
    }

    @GetMapping("/excel/export")
    public void downloadGoodsweightExcel(HttpServletResponse response,Page<GoodsWeightSearchVO> page, GoodsWeightVO goodsWeightVO) throws IOException {
        try {
            // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
//            response.setContentType("application/vnd.ms-excel");
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("称重数据导出文件", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), GoodsWeight4ExcelExportPOJO.class).sheet("称重数据").doWrite(getGoodsWeight(goodsWeightVO));
        } catch (IOException e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
//            Map<String, String> map = new HashMap<String, String>();
//            map.put("status", "failure");
//            map.put("message", "下载文件失败" + e.getMessage());
            WCSApiResponse<Object> res = new WCSApiResponse<>();
            res.failed("数据文件下载失败",logger);
            response.getWriter().println(JSON.toJSONString(res));
        }
    }

    public List<GoodsWeight4ExcelExportPOJO> getGoodsWeight(GoodsWeightVO goodsWeightVO){
//        QueryWrapper<GoodsWeight> gwQuerryWrapper = new QueryWrapper<>();
//        if(goodsWeightVO!=null){
//            if(!StringUtils.isEmpty(goodsWeightVO.getBarCode())){
//                //查询快递单号
//                gwQuerryWrapper.eq("bar_code",goodsWeightVO.getBarCode());
//            }
//            if(!StringUtils.isEmpty(goodsWeightVO.getId())){
//                //根据机器id查询
//                gwQuerryWrapper.eq("gw_robot_id",goodsWeightVO.getId());
//            }
//            if(goodsWeightVO.getBeginTime()!=null && goodsWeightVO.getEndTime()!=null){
//                gwQuerryWrapper.between("modify_time",goodsWeightVO.getBeginTime(),goodsWeightVO.getEndTime());
//            }
//        }
        ArrayList<GoodsWeight4ExcelExportPOJO> goodsWeights = new ArrayList<>();
        long pageSize = 500L;
        Page<GoodsWeight4ExcelExportPOJO> gwPage = new Page<GoodsWeight4ExcelExportPOJO>(1L,pageSize);
//        gwPage.setOptimizeCountSql(false);
        Page<GoodsWeight4ExcelExportPOJO> resPage = goodsWeightService.pageGoodsWeightAndRobot(gwPage, goodsWeightVO);
//        IPage<GoodsWeight> resPage = goodsWeightService.page(gwPage, gwQuerryWrapper);
        if(resPage == null){
            return goodsWeights;
        }
        if(CollectionUtils.isEmpty(resPage.getRecords())){
            return goodsWeights;
        }

        long total = resPage.getTotal();
        if(total<=pageSize){
            return resPage.getRecords();
        }else{

            long times = total/pageSize-1; //前面已经查询了一次
            if((total%pageSize)>0 ){
                times+=1;
            }
            for(long i = 0 ;i<times;i++){
                //已经查询过一次 , 从第二页开始
                gwPage.setCurrent(i+2);
                IPage<GoodsWeight4ExcelExportPOJO> tmpPageGW = goodsWeightService.pageGoodsWeightAndRobot(gwPage, goodsWeightVO);
//                IPage<GoodsWeight> tmpPageGW = goodsWeightService.page(gwPage, gwQuerryWrapper);
                if(tmpPageGW== null){
                    break;
                }
                if(tmpPageGW.getSize()>0){
                    goodsWeights.addAll(tmpPageGW.getRecords());
                }
            }
        }

        return goodsWeights;
    }

}
