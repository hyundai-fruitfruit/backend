package com.hyundai.app.util.redis;


import com.hyundai.app.event.dto.EventDetailResDto;
import com.hyundai.app.event.enumType.EventType;
import com.hyundai.app.event.mapper.EventMapper;
import com.hyundai.app.exception.AdventureOfHeendyException;
import com.hyundai.app.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author 황수영
 * @since 2024/03/13
 * Redis 관련 기능
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisBlackListTemplate;
    private final RedisTemplate<String, Object> redisTemplate;
    private final EventMapper eventMapper;

    /**
     * @author 황수영
     * @since 2024/03/13
     * 로그아웃용 JWT 처리
     */
    public void setBlackList(String key, Object o, long minutes) {
        redisBlackListTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(o.getClass()));
        redisBlackListTemplate.opsForValue().set(key, o, minutes, TimeUnit.MINUTES);
    }

    public boolean isTokenBlackList(String accessToken) {
        return Boolean.TRUE.equals(redisBlackListTemplate.hasKey(accessToken));
    }

    /**
     * @author 황수영
     * @since 2024/03/13
     * 로그아웃용 JWT 처리
     */
    public int getRandomSpotByRandom(String key) {
        Set<Object> set = getRandomSpot(key);
        Integer randomSpot = null;
        if (!set.isEmpty()) {
            Object[] array = set.toArray();
            int randomIndex = new Random().nextInt(array.length);
            randomSpot = (Integer) array[randomIndex];
        } else {
            log.error("랜덤 스팟 조회될 매장이 존재하지 않습니다.");
            throw new AdventureOfHeendyException(ErrorCode.SERVER_UNAVAILABLE);
        }
        return randomSpot;
    }

    /**
     * @author 황수영
     * @since 2024/03/13
     * 로그아웃용 JWT 처리
     */
    private Set<Object> getRandomSpot(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * @author 황수영
     * @since 2024/03/13
     * 매시간 랜덤 스팟 가능한 매장 조회
     */
    @Scheduled
    public void addRandomSpotAll() {
        SetOperations<String, Object> setOperations = redisTemplate.opsForSet();
        for (EventType eventType : EventType.values()) {
            List<EventDetailResDto> events = eventMapper.findEventAllByEventType(eventType);
            for (EventDetailResDto event :  events) {
                log.debug("이벤트 타입 : " + eventType + " => " + " 랜덤 스팟 후보 매장 번호 : " + event.getId());
                setOperations.add(eventType.toString(), event.getId());
            }
        }
    }
}