package com.baoshi.wcs.aspect;

import com.alibaba.fastjson.JSON;
import com.baoshi.wcs.common.enumeration.EventCodeEnum;
import com.baoshi.wcs.common.enumeration.RequestorEnum;
import com.baoshi.wcs.common.response.ApiResponse;
import com.baoshi.wcs.common.response.WCSApiResponse;
import com.baoshi.wcs.common.utils.DateUtil;
import com.baoshi.wcs.entity.Requestor;
import com.baoshi.wcs.service.RequestorService;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

@Aspect   //定义一个切面
@Configuration
public class LogRecordAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogRecordAspect.class);

    @Autowired
    RequestorService requestorService;

    // 定义切点Pointcut
    @Pointcut("execution(* com.baoshi.wcs.web.controller.*Controller.*(..))")
    public void excudeService() {
    }

    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

//        String url = request.getRequestURL().toString();
//        String method = request.getMethod();
//        String uri = request.getRequestURI();
//        String queryString = request.getQueryString();
//        logger.info("请求开始, 各个参数, url: {}, method: {}, uri: {}, params: {}", url, method, uri, queryString);

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

        // result的值就是被拦截方法的返回值
        Object result = pjp.proceed();
        WCSApiResponse response = null;
        if (result instanceof WCSApiResponse) {
            response = (WCSApiResponse) result;
        }
//        logger.info("请求结束，controller的返回值是 " + JSON.toJSONString(result));
        if(saveRes){
            response.setRequestor(requestor);
        }else{
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMsg("请求方数据保存失败");
        }
        return response;
    }
}