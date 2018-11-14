package com.baoshi.wcs.common.enumeration;

/**
 * 服务请求事件类型
 */
public enum EventCodeEnum {

    UNKNOWN_EVENT(0,"未知事件");


    private int eventCode;

    private String event;

    EventCodeEnum(int eventCode, String event) {
        this.eventCode = eventCode;
        this.event = event;
    }

    public static String getEvent(int eventCode){
        for(EventCodeEnum eventCodeEnum:EventCodeEnum.values()){
            if(eventCodeEnum.getEventCode() == eventCode){
                return eventCodeEnum.getEvent();
            }
        }
        return null;
    }

    public static String getUnknownEvent(){
        return getEvent(0);
    }

    public int getEventCode() {
        return eventCode;
    }

    public String getEvent() {
        return event;
    }
}
