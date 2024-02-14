package com.hyundai.app.security.methodparam;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 황수영
 * @since 2024/02/14
 * JWT에서 파싱된 멤버 id용 어노테이션
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface MemberId {
}