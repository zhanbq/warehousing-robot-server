package com.baoshi.wcs.common.utils;

import com.baoshi.wcs.entity.ShipperCarton;
import com.baoshi.wcs.pojo.ShipperCartonPojo;
import com.baoshi.wcs.vo.GoodsWeightVO;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 快递信息 计算 工具类
 */
public class ExpressMathUtil {

    public static final String LENGTH ="0";

    public static final String WIDTH = "1";

    public static final String HEIGHT = "2";

    /**
     *
     * @param length
     * @param width
     * @param height
     * @return
     */
    public static Double getVolume(Double length, Double width, Double height){
        BigDecimal lengthDecimal = new BigDecimal(length);
        BigDecimal widthDecimal = new BigDecimal(width);
        BigDecimal heightDecimal = new BigDecimal(height);
        return lengthDecimal.multiply(widthDecimal).multiply(heightDecimal).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
    }

    /**
     * 排序并分别取出长宽高
     * @param length
     * @param width
     * @param height
     * @return
     */
    public static void sortAndGetLengthAndWidthAndHeight(GoodsWeightVO goodsWeightVO,Double length, Double width, Double height){
        List<Double> list = new ArrayList<>();
        list.add(length);
        list.add(width);
        list.add(height);
        quickSort(list,0,(list.size()-1));
        goodsWeightVO.setHeight(list.get(2));
        goodsWeightVO.setWidth(list.get(1));
        goodsWeightVO.setLength(list.get(0));
    }

    public static List<Double> sortLengthAndWidthAndHeight(Double length, Double width, Double height){
        List<Double> list = new ArrayList<>();
        list.add(length);
        list.add(width);
        list.add(height);
        quickSort(list,0,(list.size()-1));
        return list;
    }

    /**
     * 无序数组 快速排序 升序排序
     */
    public static void quickSort(List<Double> arr,int low,int high){
        int i,j;
        double temp,t;
        if(low>high){
            return;
        }
        i=low;
        j=high;
        //temp就是基准位
        temp = arr.get(low);

        while (i<j) {
            //先看右边，依次往左递减
            while (temp<= arr.get(j) &&i<j) {
                j--;
            }
            //再看左边，依次往右递增
            while (temp>= arr.get(i) &&i<j) {
                i++;
            }
            //如果满足条件则交换
            if (i<j) {
                t = arr.get(j);
                arr.set(j, arr.get(i));
                arr.set(i, t);
            }

        }
        //最后将基准为与i和j相等位置的数字交换
        arr.set(low, arr.get(i));
        arr.set(i, temp);
        //递归调用左半数组
        quickSort(arr, low, j-1);
        //递归调用右半数组
        quickSort(arr, j+1, high);
    }


    /**
     * 计算两个数字的误差, 目前用于计算 纸箱的长宽高误差 来选取 货主的纸箱
     * @param machineData 称重机测量值
     * @param actualData 实际纸箱边长
     * @return
     */
    public static double calculatedNumberError(double machineData, double actualData){
        BigDecimal machineDecimalLength = new BigDecimal(machineData);
        BigDecimal actualDecimalLength = new BigDecimal(actualData);
        return machineDecimalLength.subtract(actualDecimalLength).doubleValue();
    }

    /**
     *  @param shipperCartonList
     * @param machineSideLength
     */
    public static List<ShipperCartonPojo> sortAscShipperCartonList(List<ShipperCarton> shipperCartonList, double machineSideLength, String sideFlag ){

        List<ShipperCartonPojo> ShipperCartonPojoList = new ArrayList<>();
        switch (sideFlag){
                case LENGTH:
                    for(ShipperCarton sc:shipperCartonList){
                        double numberError = calculatedNumberError(machineSideLength, sc.getCartonLength());
                        collectDigitalErrorVal(ShipperCartonPojoList, sc, numberError);
                    }
                    break;
                case WIDTH:
                    for(ShipperCarton sc:shipperCartonList){
                        double numberError = calculatedNumberError(machineSideLength,sc.getCartonWidth());
                        collectDigitalErrorVal(ShipperCartonPojoList, sc, numberError);
                    }
                    break;
                case HEIGHT:
                    for(ShipperCarton sc:shipperCartonList){
                        double numberError = calculatedNumberError(machineSideLength,sc.getCartonHeight());
                        collectDigitalErrorVal(ShipperCartonPojoList, sc, numberError);
                    }
                    break;

            }
        ShipperCartonPojoList.sort(new Comparator<ShipperCartonPojo>() {
            @Override
            public int compare(ShipperCartonPojo o1, ShipperCartonPojo o2) {
                //生序排序
                return Double.valueOf(o1.getDigitalErrorValue()).compareTo(Double.valueOf(o2.getDigitalErrorValue()));
            }
        });
        return ShipperCartonPojoList;
    }

    private static void collectDigitalErrorVal(List<ShipperCartonPojo> shipperCartonPojoList, ShipperCarton sc, double numberError) {
        ShipperCartonPojo shipperCartonPojo = new ShipperCartonPojo();
        BeanUtils.copyProperties(sc, shipperCartonPojo);
        shipperCartonPojo.setDigitalErrorValue(numberError);
        shipperCartonPojoList.add(shipperCartonPojo);
    }


}
