package com.hyundai.app.scheduler.generator;

import com.hyundai.app.scheduler.job.RandomSpotPushJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;

import static com.hyundai.app.scheduler.SchedulerVariable.DEVICE_TOKEN;
import static com.hyundai.app.scheduler.SchedulerVariable.TIME;

/**
 * @author 황수영
 * @since 2024/02/21
 * JobDetail 생성
 */
public class JobDetailGenerator {

    public static JobDetail createPushJobDetail(String deviceToken, int time) {
        return JobBuilder.newJob()
                .ofType(RandomSpotPushJob.class)
                .withIdentity("RandomSpotPushJob")
                .usingJobData(DEVICE_TOKEN.getValue(), deviceToken)   // 유저별 디바이스 토큰
                // .usingJobData(TIME.getValue(), time)                  // 시간
                .build();
    }
}