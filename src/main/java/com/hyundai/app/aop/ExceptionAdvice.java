package com.hyundai.app.aop;

import lombok.extern.log4j.Log4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author 황수영
 * @since 2024/02/10
 * 예외 로그 설정
 */
@Log4j
@Aspect
@Component
public class ExceptionAdvice {

    @AfterThrowing(pointcut = "execution(* com.livarter.app.service.*.*()) ", throwing = "e")
    public void throwExceptionInService(Exception e) {
        log.error("Exception 발생 : " + e);
    }
}