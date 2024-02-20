package com.hyundai.app.fcm;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * @author 황수영
 * @since 2024/02/20
 * FCM 테스트용 컨트롤러
 */
@Api("FCM 테스트용 API")
@RestController
@RequestMapping("/api/v1/fcm")
@RequiredArgsConstructor
public class FcmController {

    private final FcmPushService fcmPushService;

    /**
     * @author 황수영
     * @since 2024/02/20
     * FCM 푸시 알림 테스트용
     */
    @ApiOperation("FCM 테스트용 API")
    @PostMapping("/test/{deviceToken}")
    public ResponseEntity<Void> testPush(@PathVariable String deviceToken) throws ExecutionException, InterruptedException {
        fcmPushService.pushAlarmTest(deviceToken);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}