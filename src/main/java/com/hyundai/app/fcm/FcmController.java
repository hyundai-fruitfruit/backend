package com.hyundai.app.fcm;

import com.hyundai.app.fcm.dto.PushReqDto;
import com.hyundai.app.security.methodparam.MemberId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;

/**
 * @author 황수영
 * @since 2024/02/20
 * FCM 테스트용 컨트롤러
 */
@Log4j
@Api("FCM 푸시 알림 API")
@RestController
@RequestMapping("/api/v1/fcm-push")
@RequiredArgsConstructor
public class FcmController {

    private final FcmPushService fcmPushService;

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

    /**
     * @author 황수영
     * @since 2024/02/20
     * FCM 푸시 알림 테스트용 - 디바이스 토큰만
     */
    @ApiOperation("FCM 테스트용 API : 디바이스 토큰으로 푸시알림 발송")
    @PostMapping("/random-spot/device-token")
    public ResponseEntity<Void> pushByDeviceToken(
            @RequestBody PushReqDto pushReqDto) throws ExecutionException, InterruptedException
    {
        log.debug("랜덤 스팟 FCM 푸시 알림 바로 전송 => device token : " + pushReqDto.getDeviceToken());
        fcmPushService.testPush(pushReqDto.getDeviceToken());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * @author 황수영
     * @since 2024/02/20
     * FCM 푸시 알림 시연용 - 로그인한 유저에게 바로 FCM 알림 발송
     */
    @ApiOperation("FCM 시연용 API : 호출 시, 로그인한 유저에게 푸시알림 발송")
    @GetMapping
    public ResponseEntity<Void> pushByMemberId(
            @ApiIgnore @MemberId String memberId
    ) throws ExecutionException, InterruptedException {
        log.debug("랜덤 스팟 FCM 푸시 알림 바로 전송 => memberId : " + memberId);
        fcmPushService.createRandomSpotPushByMemberId(memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}