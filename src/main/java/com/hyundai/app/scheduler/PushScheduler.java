package com.hyundai.app.scheduler;

import com.hyundai.app.exception.AdventureOfHeendyException;
import com.hyundai.app.fcm.dto.PushReqDto;
import com.hyundai.app.scheduler.generator.JobDetailGenerator;
import com.hyundai.app.scheduler.generator.TriggerGenerator;
import lombok.extern.log4j.Log4j;
import org.quartz.*;
import org.springframework.stereotype.Component;

import static com.hyundai.app.exception.ErrorCode.PUSH_RANDOM_SPOT_UNAVAILABLE;

/**
 * @author 황수영
 * @since 2024/02/21
 * 푸시 관련 스케쥴러
 */
@Log4j
@Component
public class PushScheduler {
    
    /**
     * @author 황수영
     * @since 2024/02/20
     * 랜덤 스팟 FCM 푸시 알림 - n분 뒤 발송 예약하는 기능
     */
    public void schedulePushForRandomSpot(PushReqDto pushReqDto) {
        log.debug("PushScheduler : 푸시 알림 발송 시작 " + pushReqDto.getDelayedSeconds() + "초 뒤에 실행");
        SchedulerFactory schedulerFactory = new org.quartz.impl.StdSchedulerFactory();
        Scheduler scheduler;
        try {
            scheduler = schedulerFactory.getScheduler();
            scheduler.start();

            JobDetail job = JobDetailGenerator.createPushJobDetail(pushReqDto.getDeviceToken(), pushReqDto.getDelayedSeconds());
            Trigger trigger = TriggerGenerator.createPushTriggerDelayedBySeconds(pushReqDto.getDelayedSeconds());
            log.debug("JobDetail : " + job + " Trigger : " + trigger);

            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            log.error("PushScheduler : 푸시 알림 에러 발생 " + e);
            throw new AdventureOfHeendyException(PUSH_RANDOM_SPOT_UNAVAILABLE);
        }
    }
}