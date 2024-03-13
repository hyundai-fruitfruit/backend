package com.hyundai.app.event.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @author 엄상은
 * @since 2024/02/27
 * 소켓 컨트롤러
 */
@Log4j
@Controller
public class SocketController {
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String getMessageAndSend(String message) {
        log.debug("소켓으로부터 메세지 받음: " + message);
        return "서버에서 소켓으로 메세지 날림" + message;
    }
}
