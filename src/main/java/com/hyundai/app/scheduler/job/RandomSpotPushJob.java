package com.hyundai.app.scheduler.job;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.hyundai.app.exception.AdventureOfHeendyException;
import com.hyundai.app.fcm.FcmPushService;
import com.hyundai.app.fcm.PushType;
import com.hyundai.app.fcm.dto.PushMessageDto;
import lombok.extern.log4j.Log4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;

import static com.hyundai.app.exception.ErrorCode.PUSH_RANDOM_SPOT_UNAVAILABLE;

/**
 * @author 황수영
 * @since 2024/02/20
 * 랜덤 스팟 예약 푸시 알림용 Job 정의
 */
@Log4j
public class RandomSpotPushJob extends QuartzJobBean {
    @Autowired
    private FirebaseApp firebaseApp;
    @Autowired
    private FcmPushService fcmPushService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.debug("RandomSpotPushAlarmJob 시작! " + LocalDateTime.now());
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        log.debug("RandomSpotPushAlarmJob 의존성 주입 확인 =>  fcmPushService is null ? " + (fcmPushService == null));

        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        log.debug("deviceToken : " + dataMap.getString("deviceToken"));

        createRandomSpotPushNotification(dataMap);
        log.debug("RandomSpotPushAlarmJob 종료! " + LocalDateTime.now());
    }

    private void createRandomSpotPushNotification(JobDataMap dataMap) {
        String deviceToken = dataMap.getString("deviceToken");

        Notification notification = PushType.createNotification(PushMessageDto.from(PushType.RANDOM_SPOT));
        Message message = PushType.createMessage(notification, deviceToken);
        try {
            FirebaseMessaging.getInstance(firebaseApp).sendAsync(message).get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("createRandomSpotPushNotification 에러 발생!");
            throw new AdventureOfHeendyException(PUSH_RANDOM_SPOT_UNAVAILABLE);
        }
    }
}