package com.hyundai.app.util.redis;

/**
 * @author 황수영
 * @since 2024/03/13
 * Redis 관련 기능
 */
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisBlackListTemplate;

    public void setBlackList(String key, Object o, long minutes) {
        redisBlackListTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(o.getClass()));
        redisBlackListTemplate.opsForValue().set(key, o, minutes, TimeUnit.MINUTES);
    }

    public boolean isTokenBlackList(String accessToken) {
        return Boolean.TRUE.equals(redisBlackListTemplate.hasKey(accessToken));
    }
}