package com.hyundai.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author 황수영
 * @since 2024/02/10
 * AOP 설정
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.hyundai.app")
public class AopConfig {
}