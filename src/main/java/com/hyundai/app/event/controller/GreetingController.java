package com.hyundai.app.event.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @author 엄상은
 * @since 2024/02/27
 * 소켓 테스트용 컨트롤러
 */
@Log4j
@Controller
public class GreetingController {
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String greeting(String message) throws Exception {
        Thread.sleep(1000);
        log.debug("서버에서 요청 받음");
        return "Hello from server!" + message;
    }
}
