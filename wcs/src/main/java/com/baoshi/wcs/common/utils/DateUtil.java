package com.baoshi.wcs.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static final SimpleDateFormat YYYY_MM_DD_HHMMSS_Formater = new SimpleDateFormat("yyyy-NM-dd HH:mm:ss");

    public static Date format(SimpleDateFormat formator, String dateStr) throws ParseException {
        Date date = formator.parse(dateStr);
        return date;
    }
}
