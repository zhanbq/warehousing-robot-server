package com.baoshi.wcs.interceptor;

import com.baoshi.wcs.common.enumeration.EventCodeEnum;
import com.baoshi.wcs.common.enumeration.RequestorEnum;
import com.baoshi.wcs.common.utils.DateUtil;
import com.baoshi.wcs.entity.Requestor;
import com.baoshi.wcs.service.RequestorService;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestorInterceptor implements HandlerInterceptor {

    RequestorService requestorService;

    public RequestorInterceptor(RequestorService requestorService) {
        this.requestorService=requestorService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestorCode = request.getParameter("requestor");
        String reqDate = request.getParameter("reqDate");
        String eventCode = request.getParameter("eventCode");

        /**
         * 校验调用者
         */
        if(StringUtils.isEmpty(requestorCode)){
            requestorCode = Integer.toString( RequestorEnum.GENERAL_USER.getCode());
        }else{

            String role = RequestorEnum.getRole(Integer.parseInt(requestorCode));
            if(StringUtils.isEmpty(role)){
                //TODO 错误的请求角色编码,暂时处理为普通用户
                requestorCode = Integer.toString( RequestorEnum.GENERAL_USER.getCode());
            }
        }


        Requestor requestor = new Requestor();
        requestor.setRequestor(requestorCode);
        /**
         * 校验请求时间
         */
        if(StringUtils.isEmpty(reqDate)){
            requestor.setReqDate(new Date());
        }else{
            Date reqDateParam = DateUtil.format(DateUtil.YYYY_MM_DD_HHMMSS_Formater,reqDate);
            requestor.setReqDate(reqDateParam);
        }

        /**
         * 校验事件编码
         */
        if(StringUtils.isEmpty(eventCode)){
            eventCode=Integer.toString(EventCodeEnum.UNKNOWN_EVENT.getEventCode());
        }
        requestor.setEventCode(eventCode);

        boolean saveRes = requestorService.save(requestor);

        if(saveRes){
            return true;
        }
        return false;
    }

}
