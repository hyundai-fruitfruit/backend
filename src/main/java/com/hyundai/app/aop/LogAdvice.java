package com.hyundai.app.aop;

import lombok.extern.log4j.Log4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

/**
 * @author 황수영
 * @since 2024/02/10
 * 로그 일괄 설정
 */
@Log4j
@Aspect
@Component
public class LogAdvice {

    @Around("execution(* com.hyundai.app.controller.*.*())")
    public Object logInController(ProceedingJoinPoint pjp) {

        log.debug("--------------컨트롤러 시작--------------" + new Date());
        log.debug("컨트롤러 메소드 => " +  pjp.getSignature().toString());
        log.debug("파라미터 => " +  Arrays.toString(pjp.getArgs()));

        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable e) {
            log.error("에러 발생! : " + e);
        }
        log.debug("--------------컨트롤러 종료--------------");
        return result;
    }

    @Around("execution(* com.hyundai.app.service.*.*())")
    public Object logInService(ProceedingJoinPoint pjp) {
        log.debug("--------------서비스 시작--------------" + new Date());
        long startTime = System.currentTimeMillis();
        log.debug("서비스 메소드 => " +  pjp.getSignature().toString());
        log.debug("파라미터 => " +  Arrays.toString(pjp.getArgs()));

        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable e) {
            log.debug("에러 발생! => " + e);
        }

        long endTime = System.currentTimeMillis();
        log.debug("소요 시간 => " + (endTime - startTime));
        log.debug("--------------서비스 종료--------------");
        return result;
    }
}