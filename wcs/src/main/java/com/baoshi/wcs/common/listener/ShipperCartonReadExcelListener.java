package com.baoshi.wcs.common.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baoshi.wcs.common.utils.ExpressMathUtil;
import com.baoshi.wcs.entity.ShipperCarton;
import com.baoshi.wcs.service.ShipperCartonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Component
public class ShipperCartonReadExcelListener extends AnalysisEventListener<ShipperCarton> {
    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<ShipperCarton> list = new ArrayList<ShipperCarton>();

    @Autowired
    ShipperCartonService shipperCartonService;

    private static ShipperCartonService localShipperCartonService;

    @PostConstruct
    public void init() {
        localShipperCartonService = shipperCartonService;
    }
    @Override
    public void invoke(ShipperCarton shipperCarton, AnalysisContext analysisContext) {
//        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(shipperCarton));
        List<Double> lgAndWAndH = ExpressMathUtil.sortLengthAndWidthAndHeight(shipperCarton.getCartonLength()
                , shipperCarton.getCartonWidth()
                , shipperCarton.getCartonHeight());
        shipperCarton.setCartonLength(lgAndWAndH.get(2));
        shipperCarton.setCartonWidth(lgAndWAndH.get(1));
        shipperCarton.setCartonHeight(lgAndWAndH.get(0));
        list.add(shipperCarton);
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
        LOGGER.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
//        LOGGER.info("{}条数据，开始存储数据库！", list.size());
        localShipperCartonService.saveBatch(list);
//        LOGGER.info("存储数据库成功！");
    }
}
