package com.baoshi.wcs.common.response;

import com.alibaba.fastjson.JSON;
import com.baoshi.wcs.entity.Requestor;
import com.baoshi.wcs.vo.Requestor4ResponseVO;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;

//@ApiModel(value = "com.baoshi.wcs.common.response.WCSApiResponse", description = "通用返回对象")
public class WCSApiResponse<T> extends ApiResponse<T> implements Serializable {

    private static final long serialVersionUID = 3571818710910772372L;

    private String serverMsg;

    Requestor4ResponseVO requestor;

    public void buildRequestor(String requestor, String eventCode, Date reqDate){
        Requestor4ResponseVO requestorBuild = new Requestor4ResponseVO(requestor,eventCode,reqDate);
        this.requestor = requestorBuild;
    }

    public Requestor4ResponseVO getRequestor() {
        return requestor;
    }

    public void setRequestorVO(Requestor4ResponseVO requestor) {
        this.requestor = requestor;
    }

    public void setRequestorVO(Requestor requestor) {
        Requestor4ResponseVO requestor4ResponseVO = new Requestor4ResponseVO();
        requestor4ResponseVO.setRequestor(requestor.getRequestor());
        requestor4ResponseVO.setEventCode(requestor.getEventCode());
        requestor4ResponseVO.setReqDate(requestor.getReqDate());
        requestor4ResponseVO.setResDate(new Date());
        this.requestor = requestor4ResponseVO;
    }

    public String getServerMsg() {
        return serverMsg;
    }

    public void setServerMsg(String serverMsg) {
        this.serverMsg = serverMsg;
    }

    public <T> void setServerMsg(String serverMsg, T... data) {
        this.serverMsg = serverMsg;
        if(!StringUtils.isEmpty(data)){
            for(T d:data){
                this.serverMsg+=" | "+d.getClass().getDeclaredFields()[0] + " : ";
                this.serverMsg+=d.toString();
            }
        }
    }
}
