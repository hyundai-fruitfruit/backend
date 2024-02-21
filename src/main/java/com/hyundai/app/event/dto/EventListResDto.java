package com.hyundai.app.event.dto;

import com.hyundai.app.common.entity.IdWithCriteria;
import com.hyundai.app.common.entity.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 엄상은
 * @since 2024/02/18
 * 이벤트 전체 정보 조회 응답 객체
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventListResDto {
    private List<EventDetailResDto> events;
    private PageInfo pageInfo;

    public static EventListResDto from(List<EventDetailResDto> eventDetailResDtoList, IdWithCriteria criteria) {
        PageInfo pageInfo = new PageInfo(criteria, eventDetailResDtoList.size());
        return new EventListResDto(eventDetailResDtoList, pageInfo);
    }
}
