package com.hyundai.app.fcm;

import com.hyundai.app.fcm.dto.PushReqDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;

/**
 * @author 황수영
 * @since 2024/02/20
 * FCM 테스트용 컨트롤러
 */
@Log4j
@Api("FCM 테스트용 API")
@RestController
@RequestMapping("/api/v1/fcm-push")
@RequiredArgsConstructor
public class FcmController {

    private final FcmPushService fcmPushService;

    /**
     * @author 황수영
     * @since 2024/02/20
     * FCM 푸시 알림 테스트용 - 바로 FCM 알림 발송
     */
    @ApiOperation("FCM 테스트용 API")
    @PostMapping("/test/{deviceToken}")
    public ResponseEntity<Void> testPush(@PathVariable String deviceToken) throws ExecutionException, InterruptedException {
        fcmPushService.testPush(deviceToken);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * @author 황수영
     * @since 2024/02/21
     * 랜덤 스팟 알림용 : GPS 인증 후 30분 뒤에 푸시 알림
     */
    @ApiOperation("랜덤 스팟 알림용 API - GPS 인증 시 해당 api 요청")
    @PostMapping("/random-spot")
    public ResponseEntity<Void> pushRandomSpot(@RequestBody PushReqDto pushReqDto) {
        log.debug("랜덤 스팟 푸시 알림 예약 => device token : " + pushReqDto.getDeviceToken());
        log.debug("현재 시각 : " + LocalDateTime.now() + " -> " + pushReqDto.getDelayedSeconds() + "초 뒤에 실행");
        fcmPushService.createRandomSpotPushSchedule(pushReqDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}