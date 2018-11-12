package com.baoshi.wcs.common.response;

import com.baoshi.wcs.entity.Requestor;
import com.baoshi.wcs.vo.Requestor4ResponseVO;

import org.springframework.beans.BeanUtils;

import java.util.Date;

public class WCSApiResponse<T> extends ApiResponse<T> {

    private static final long serialVersionUID = 3571818710910772372L;

    Requestor4ResponseVO requestor;

    public void buildRequestor(String requestor, String eventCode, Date reqDate){
        Requestor4ResponseVO requestorBuild = new Requestor4ResponseVO(requestor,eventCode,reqDate);
        this.requestor = requestorBuild;
    }

    public Requestor4ResponseVO getRequestor() {
        return requestor;
    }

    public void setRequestor(Requestor4ResponseVO requestor) {
        this.requestor = requestor;
    }

    public void setRequestor(Requestor requestor) {
        Requestor4ResponseVO requestor4ResponseVO = new Requestor4ResponseVO();
        requestor4ResponseVO.setRequestor(requestor.getRequestor());
        requestor4ResponseVO.setEventCode(requestor.getEventCode());
        requestor4ResponseVO.setReqDate(requestor.getReqDate());
        requestor4ResponseVO.setResDate(new Date());
        this.requestor = requestor4ResponseVO;
    }
}
