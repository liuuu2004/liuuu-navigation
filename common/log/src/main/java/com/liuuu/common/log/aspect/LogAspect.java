package com.liuuu.common.log.aspect;

import com.liuuu.common.log.annotation.Log;
import com.liuuu.common.log.dto.LogDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

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
    @AfterThrowing(pointcut = "@annotation(logAnnotation", throwing = "exception")
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
//    private void dealLog(JoinPoint joinPoint, Log logAnnotation, Exception exception, Object returnResult) {
//        try {
//            LogDTO logDTO = new LogDTO();
//            logDTO.setGmtOperate(new Date());
//
//            try {
//                logDTO.setUserId(SecurityUtils);
//            }
//        }
//    }
}
