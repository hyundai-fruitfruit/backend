package com.hyundai.app.fcm;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.hyundai.app.fcm.dto.PushReqDto;
import com.hyundai.app.scheduler.PushScheduler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import java.util.concurrent.ExecutionException;

/**
 * @author 황수영
 * @since 2024/02/20
 * FCM 푸시 알림 관련 서비스단
 */
@Log4j
@Service
@RequiredArgsConstructor
public class FcmPushService {
    @Autowired
    private FirebaseApp firebaseApp;
    @Autowired
    private PushScheduler pushScheduler;

    /**
     * @author 황수영
     * @since 2024/02/20
     * FCM 테스트용 알림
     */
    public void testPush(String deviceToken) throws ExecutionException, InterruptedException {
        log.debug("알림 테스트 시작");
        Notification notification = PushType.createNotification(PushType.WELCOME);
        Message message = PushType.createMessage(notification, deviceToken);
        FirebaseMessaging.getInstance(firebaseApp).sendAsync(message).get();
    }

    /**
     * @author 황수영
     * @since 2024/02/20
     * 랜덤 스팟 FCM 푸시 알림 스케쥴러 호출
     */
    public void createRandomSpotPushSchedule(PushReqDto pushReqDto) {
        log.debug("createRandomSpotPushSchedule => 랜덤 스팟 푸시 알림");
        pushScheduler.schedulePushForRandomSpot(pushReqDto);
    }
}