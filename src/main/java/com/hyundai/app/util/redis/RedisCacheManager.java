package com.hyundai.app.util.redis;

import redis.clients.jedis.Jedis;

/**
 * @author 황수영
 * @since 2024/03/13
 * (설명)
 */
public class RedisCacheManager {
    private Jedis jedis;

    public RedisCacheManager() {
        // Redis 연결 초기화
        jedis = new Jedis("localhost", 6379); // Redis 서버 호스트와 포트에 맞게 설정
    }

    public String getFromCache(String key) {
        // 캐시에서 데이터 가져오기
        return jedis.get(key);
    }

    public void addToCache(String key, String value, int expiryInSeconds) {
        // 캐시에 데이터 추가
        jedis.setex(key, expiryInSeconds, value);
    }

}
