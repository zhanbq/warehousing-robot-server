package com.baoshi.wcs.common.utils;

import java.math.BigDecimal;

public class VolumeUtils {
    /**
     * 值为1，使用刻度为0。
     */
    public static final BigDecimal ONE = BigDecimal.ONE;


    /**
     * 值为10，使用刻度为0。
     */
    public static final BigDecimal TEN = BigDecimal.TEN;

    /**
     * 值为0，使用刻度为0。
     */
    public static final BigDecimal ZERO = BigDecimal.ZERO;

    /**
     * 舍入模式舍向正无穷。正数效果同ROUND_UP举例如：1.239得到的是1.24；负数效果举例如：-1.239得到的是-1.23
     */
    public static final int ROUND_CEILING = BigDecimal.ROUND_CEILING;


    /**
     * 舍入模式，向零舍入（即舍弃后边所有，不向前进1）。
     */
    public static final int ROUND_DOWN = BigDecimal.ROUND_DOWN;

    /**
     * 舍入模式接近负无穷大。正数效果同ROUND_DOWN举例如：1.239得到的是1.23；负数效果举例如：-1.239得到的是-1.23
     */
    public static final int ROUND_FLOOR = BigDecimal.ROUND_FLOOR;

    /**
     * 遵循四舍五入规则，大于5向前一位进1。
     */
    public static final int ROUND_HALF_DOWN = BigDecimal.ROUND_HALF_DOWN;

    /**
     * 舍入模式舍对“近邻”如果与两个相邻数字的距离相等，在这种情况下，舍入向着更加相邻（正负数都是向着0相邻舍入）。
     */
    public static final int ROUND_HALF_EVEN = BigDecimal.ROUND_HALF_EVEN;

    /**
     * 遵循四舍五入规则，大于等于5向前一位进1。
     */
    public static final int ROUND_HALF_UP = BigDecimal.ROUND_HALF_UP;

    /**
     * 舍入模式断言请求的操作具有精确的结果，因此不需要舍入。用于获取运算结果。
     */
    public static final int ROUND_UNNECESSARY = BigDecimal.ROUND_UNNECESSARY;

    /**
     * 舍入模式，舍入去零，零不舍入。
     */
    public static final int ROUND_UP = BigDecimal.ROUND_UP;

    public static final int DECIMAL_DIGITS = 2;



    public static BigDecimal countVolume(BigDecimal length, BigDecimal width, BigDecimal height) {
        double doubleVolume = length.doubleValue()*width.doubleValue()*height.doubleValue();
        BigDecimal volume = new BigDecimal(doubleVolume).setScale(2, BigDecimal.ROUND_DOWN);
        return volume;
    }

//    public static void main(String[] args) {
//        BigDecimal   a   =   new   BigDecimal(1.26666);
//        BigDecimal   b   =   new   BigDecimal(1.235555);
//        BigDecimal   c   =   new   BigDecimal(1.44444);
//        BigDecimal volume = VolumeUtils.countVolume(a, b, c);
//        System.out.println(volume);
//        System.out.println(b.setScale(2,   BigDecimal.ROUND_DOWN).doubleValue());
//        System.out.println(a.doubleValue()*b.doubleValue());
//    }
}
