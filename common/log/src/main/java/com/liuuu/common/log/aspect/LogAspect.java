package com.liuuu.common.log.aspect;

import com.alibaba.fastjson.JSON;
import com.liuuu.common.framework.manager.AsyncManager;
import com.liuuu.common.core.util.ip.IPUtils;
import com.liuuu.common.core.util.servlet.ServletUtils;
import com.liuuu.common.log.annotation.Log;
import com.liuuu.common.log.dto.LogDTO;
import com.liuuu.common.log.enums.LogStatus;
import com.liuuu.common.log.factory.LogAsyncFactory;
import com.liuuu.framework.security.util.SecurityUtils;
import io.swagger.models.HttpMethod;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * 日志切面
 *
 * @Author Liuuu
 * @Date 2024/7/19
 */

@Aspect
@Component
public class LogAspect {

    private final Logger log = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 请求完成后处理
     *
     * @param joinPoint  切点
     * @param logAnnotation  日志
     * @param returnResult  返回结果
     */
    @AfterReturning(pointcut = "@annotation(logAnnotation)", returning = "returnResult")
    public void afterRunning(JoinPoint joinPoint, Log logAnnotation, Object returnResult) {
        dealLog(joinPoint, logAnnotation, null, returnResult);
    }

    /**
     * 抛出异常后处理
     *
     * @param joinPoint  切点
     * @param logAnnotation  日志
     * @param exception  异常
     */
    @AfterThrowing(pointcut = "@annotation(logAnnotation)", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Log logAnnotation, Exception exception) {
        dealLog(joinPoint, logAnnotation, exception, null);
    }

    /**
     * 处理日志
     *
     * @param joinPoint  切点
     * @param logAnnotation  日志
     * @param exception  异常
     * @param returnResult  返回结果
     */
    private void dealLog(JoinPoint joinPoint, Log logAnnotation, Exception exception, Object returnResult) {
        try {
            LogDTO logDTO  = new LogDTO();
            logDTO.setGmtOperate(new Date());
            try {
                logDTO.setUserId(SecurityUtils.getUserId());
            } catch (Exception e) {

            }
            HttpServletRequest request = ServletUtils.getRequest();

            String ip = IPUtils.getIpAddress(request);
            logDTO.setIpAddress(ip);
            logDTO.setRequestUrl(request.getRequestURI());

            if (exception != null) {
                logDTO.setStatus(LogStatus.ERROR.code);
                logDTO.setErrorMessage(exception.getMessage());
            } else {
                logDTO.setStatus(LogStatus.SUCCESS.code);
            }

            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            logDTO.setClassMethod(className + "." + methodName + "()");

            logDTO.setRequestMethod(ServletUtils.getRequest().getMethod());

            dealAnnotationParam(joinPoint, logAnnotation, logDTO, returnResult);

            AsyncManager.me().execute(LogAsyncFactory.addLog(logDTO));
        } catch (Exception e) {
            log.error("操作日志记录前置通知异常, 异常信息: {}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 处理注解参数
     * @param joinPoint
     * @param logAnnotation
     * @param logDTO
     * @param returnResult
     */
    private void dealAnnotationParam(JoinPoint joinPoint, Log logAnnotation, LogDTO logDTO, Object returnResult) {
        logDTO.setBusinessType(logAnnotation.businessType().code);
        logDTO.setModuleName(logAnnotation.moduleName());
        logDTO.setOperateType(logAnnotation.operateType().code);
        if (logAnnotation.isSaveRequestData()) {
            setRequestData(joinPoint, logDTO);
        }
        if (logAnnotation.isSaveResponseData() && returnResult != null) {
            logDTO.setReturnResult(JSON.toJSONString(returnResult));
        }
    }

    /**
     * 设置请求参数数据
     * @param joinPoint
     * @param logDTO
     */
    private void setRequestData(JoinPoint joinPoint, LogDTO logDTO) {
        String requestMethod = logDTO.getRequestMethod();
        if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod)) {
            String params = argsArrayToString(joinPoint.getArgs());
            logDTO.setRequestParam(params);
        } else {
            Map<?, ?> paramMap = (Map<?, ?>) ServletUtils.getRequest().getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            logDTO.setRequestParam(paramMap.toString());
        }
    }

    /**
     * 将数组参数转换为字符串
     * @param paramArray
     * @return
     */
    private String argsArrayToString(Object[] paramArray) {
        if (paramArray == null) {
            return "";
        }
        StringBuilder params = new StringBuilder();
        for (Object param : paramArray) {
            if (param != null && !isFilterObject(param)) {
                try {
                    Object jsonObject = JSON.toJSON(param);
                    params.append(jsonObject.toString());
                } catch (Exception e) {

                }
            }
        }
        return params.toString().trim();
    }

    /**
     * 判断是否为需要过滤的对象
     * @param object
     * @return
     */
    private boolean isFilterObject(final Object object) {
        Class<?> clazz = object.getClass();
        if (clazz.isArray()) {
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        } else if (Collection.class.isAssignableFrom(clazz)) {
            Collection collection = (Collection) object;
            for (Object value : collection) {
                return value instanceof MultipartFile;
            }
        } else if (Map.class.isAssignableFrom(clazz)) {
            Map map = (Map) object;
            for (Object value : map.entrySet()) {
                Map.Entry entry = (Map.Entry) value;
                return entry.getValue() instanceof MultipartFile;
            }
        }
        return object instanceof MultipartFile || object instanceof HttpServletRequest
                || object instanceof HttpServletResponse || object instanceof BindingResult;

    }

}
