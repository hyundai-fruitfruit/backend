package com.hyundai.app.event.mapper;

import com.hyundai.app.event.domain.EventActiveTime;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 엄상은
 * @since 2024/02/19
 * 이벤트 활성화 시간 MAPPER
 */
@Mapper
public interface EventActiveTimeMapper {
    int save(EventActiveTime eventActiveTime);

}
