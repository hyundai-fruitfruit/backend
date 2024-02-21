package com.hyundai.app.scheduler.generator;

import org.quartz.*;

/**
 * @author 황수영
 * @since 2024/02/21
 * Trigger 생성
 */
public class TriggerGenerator {

    public static Trigger createPushTriggerDelayedBySeconds(int delayInSeconds) {
        return TriggerBuilder.newTrigger()
                .withIdentity("createPushTriggerDelayedBySeconds")
                .startAt(DateBuilder.futureDate(delayInSeconds, DateBuilder.IntervalUnit.SECOND))
                .build();
    }

    public static Trigger createRandomSpotPushTriggerDelayedByMinutes(int delayInMinutes) {
        return TriggerBuilder.newTrigger()
                .startAt(DateBuilder.futureDate(delayInMinutes, DateBuilder.IntervalUnit.MINUTE))
                .build();
    }
}