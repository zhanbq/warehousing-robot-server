package com.baoshi.wcs.aspect;

import com.baoshi.wcs.common.enumeration.EventCodeEnum;
import com.baoshi.wcs.common.enumeration.RequestorEnum;
import com.baoshi.wcs.common.response.NewWMSResponse;
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

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;

@Aspect
@Configuration
public class LogRecordAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogRecordAspect.class);

    @Autowired
    RequestorService requestorService;

    /**
     * 定义切点Pointcut
     */
    @Pointcut("execution(* com.baoshi.wcs.web.controller.*Controller.*(..))")
    public void excudeService() {
    }

    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        WCSApiResponse response = new WCSApiResponse();
        Requestor requestor = this.getReqeestorInfo(request);

        // result的值就是被拦截方法的返回值
        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable ex) {
            logger.error("aop ex :", ex);
            if (ex instanceof org.springframework.web.servlet.NoHandlerFoundException) {
                response.setCode(HttpStatus.NOT_FOUND.value());
                response.setMsg("服务路径访问错误.");
            } else {
                response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                response.setMsg("服务内部错误");
            }
            response.setRequestorVO(requestor);
            response.setServerMsg(ex.getMessage());
            return response;
        }

//        boolean saveRes = requestorService.save(requestor);
        if(result instanceof NewWMSResponse){
            return (NewWMSResponse) result;
        }
        if (result instanceof WCSApiResponse) {
            //如果返回的类型是WCSApiResponse 则强转  代替之前声明的WCSApiResponse
            response = (WCSApiResponse) result;

        } else {
            response.setData(result);
            response.setCode(HttpStatus.OK.value());
        }
        response.setRequestorVO(requestor);
        return response;
    }


    private Requestor getReqeestorInfo(HttpServletRequest request) {
        String requestorCode = request.getParameter("requestor");
        String reqDate = request.getParameter("reqDate");
        String eventCode = request.getParameter("eventCode");

        /**
         * 校验调用者
         */
        if (StringUtils.isEmpty(requestorCode)) {
            requestorCode = Integer.toString(RequestorEnum.GENERAL_USER.getCode());
        } else {

            String role = RequestorEnum.getRole(Integer.parseInt(requestorCode));
            if (StringUtils.isEmpty(role)) {
                //TODO 错误的请求角色编码,暂时处理为普通用户
                requestorCode = Integer.toString(RequestorEnum.GENERAL_USER.getCode());
            }
        }
        Requestor requestor = new Requestor();
        requestor.setRequestor(requestorCode);
        /**
         * 校验请求时间
         */
        if (StringUtils.isEmpty(reqDate)) {
            requestor.setReqDate(new Date());
        } else {
            Date reqDateParam = null;
            try {
                reqDateParam = DateUtil.format(DateUtil.YYYY_MM_DD_HHMMSS_Formater, reqDate);
            } catch (ParseException e) {
                reqDateParam = new Date();
            }
            requestor.setReqDate(reqDateParam);
        }

        /**
         * 校验事件编码
         */
        if (StringUtils.isEmpty(eventCode)) {
            eventCode = Integer.toString(EventCodeEnum.UNKNOWN_EVENT.getEventCode());
        }
        requestor.setEventCode(eventCode);

        return requestor;
    }


//    private void resolveRequest() {
//        //        String url = request.getRequestURL().toString();
////        String method = request.getMethod();
////        String uri = request.getRequestURI();
////        String queryString = request.getQueryString();
////        logger.info("请求开始, 各个参数, url: {}, method: {}, uri: {}, params: {}", url, method, uri, queryString);
//    }

}