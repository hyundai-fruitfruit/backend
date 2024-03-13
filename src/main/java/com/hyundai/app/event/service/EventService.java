package com.hyundai.app.event.service;

import com.hyundai.app.common.entity.IdWithCriteria;
import com.hyundai.app.coupon.domain.Coupon;
import com.hyundai.app.coupon.domain.MemberCoupon;
import com.hyundai.app.coupon.mapper.CouponMapper;
import com.hyundai.app.coupon.mapper.MemberCouponMapper;
import com.hyundai.app.event.domain.MemberEvent;
import com.hyundai.app.event.dto.*;
import com.hyundai.app.event.enumType.EventType;
import com.hyundai.app.event.enumType.RewardType;
import com.hyundai.app.event.mapper.EventActiveTimeMapper;
import com.hyundai.app.event.mapper.EventMapper;
import com.hyundai.app.event.mapper.MemberEventMapper;
import com.hyundai.app.exception.AdventureOfHeendyException;
import com.hyundai.app.exception.ErrorCode;
import com.hyundai.app.store.domain.Store;
import com.hyundai.app.store.mapper.StoreMapper;
import com.hyundai.app.util.redis.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 엄상은
 * @since 2024/02/18
 * 사용자용 + 어드민용 이벤트 서비스
 */
@Log4j
@RequiredArgsConstructor
@Service
public class EventService {
    private final EventMapper eventMapper;
    private final EventActiveTimeMapper eventActiveTimeMapper;
    private final MemberEventMapper memberEventMapper;
    private final MemberCouponMapper memberCouponMapper;
    private final CouponMapper couponMapper;
    private final RedisService redisService;

    /**
     * @author 엄상은
     * @since 2024/03/04
     * 사용자용 현재 사용 가능한 랜덤스팟 모두 조회
     */
    public List<EventDetailResDto> findCurrentEventByEventType(String memberId) {

        List<EventDetailResDto> eventDetailResDtoList = eventMapper.findCurrentEventByEventType(memberId);
        for (EventDetailResDto eventDetailResDto : eventDetailResDtoList) {
            int eventId = eventDetailResDto.getId();
            List<EventActiveTimeZoneDto> eventActiveTimeZoneDtoList = findEventActiveTime(eventId);
            eventDetailResDto.setActiveTimeList(eventActiveTimeZoneDtoList);
        }
        return eventDetailResDtoList;
    }

    /**
     * @author 엄상은
     * @since 2024/02/21
     * 어드민용 랜덤스팟 모두 조회 (페이지네이션)
     */
    public EventListResDto findEventList(int storeId, int page, int size) {
        IdWithCriteria idWithCriteria = IdWithCriteria.of(storeId, page, size);
        List<EventDetailResDto> eventDetailResDtoList = eventMapper.findEventList(idWithCriteria);

        for (EventDetailResDto eventDetailResDto : eventDetailResDtoList) {
            int eventId = eventDetailResDto.getId();
            List<EventActiveTimeZoneDto> eventActiveTimeZoneDtoList = findEventActiveTime(eventId);
            eventDetailResDto.setActiveTimeList(eventActiveTimeZoneDtoList);
        }

        EventListResDto eventListResDto = EventListResDto.from(eventDetailResDtoList, idWithCriteria);
        return eventListResDto;
    }

    /**
     * @author 엄상은
     * @since 2024/02/19
     * 지점 이벤트 상세 조회
     */
    public EventDetailResDto find(int storeId, int eventId) {
        EventDetailResDto eventDetailResDto = findEventAndValidate(storeId, eventId);
        List<EventActiveTimeZoneDto> eventActiveTimeZoneDto = findEventActiveTime(eventId);
        eventDetailResDto.setActiveTimeList(eventActiveTimeZoneDto);
        return eventDetailResDto;
    }

    /**
     * @author 엄상은
     * @since 2024/02/19
     * 어드민용 이벤트 조회
     */
    public EventDetailResDto findEventAndValidate(int storeId, int eventId) {
        EventDetailResDto eventDetailResDto = eventMapper.findById(eventId);
        if (eventDetailResDto == null) {
            throw new IllegalArgumentException("해당 이벤트가 존재하지 않습니다.");
        }
        if (eventDetailResDto.getStoreId() != storeId) {
            throw new IllegalArgumentException("해당 지점의 이벤트가 아닙니다.");
        }
        List<EventActiveTimeZoneDto> eventActiveTimeZoneDto = findEventActiveTime(eventId);
        eventDetailResDto.setActiveTimeList(eventActiveTimeZoneDto);
        return eventDetailResDto;
    }

    /**
     * @author 엄상은
     * @since 2024/02/23
     * 유저용 가능한 이벤트 한 건 조회
     */
    private EventDetailResDto findAvailableEvent(int eventId) {
        EventDetailResDto eventDetailResDto = eventMapper.findById(eventId);
        if (eventDetailResDto == null) {
            throw new IllegalArgumentException("해당 이벤트가 존재하지 않습니다.");
        }
        if (eventDetailResDto.getVisitedCount() >= eventDetailResDto.getMaxCount()) {
            throw new IllegalArgumentException("이벤트 참여가 마감되었습니다.");
        }
        return eventDetailResDto;
    }

    /**
     * @author 엄상은
     * @since 2024/02/19
     * 이벤트 하나 당 가능한 시간대 조회
     */
    private List<EventActiveTimeZoneDto> findEventActiveTime(int eventId) {
        List<EventActiveTimeZoneDto> eventActiveTimeZoneDto = eventActiveTimeMapper.findByEventId(eventId);
        return eventActiveTimeZoneDto;
    }

    /**
     * @author 엄상은
     * @since 2024/02/19
     * 어드민용 이벤트 저장 후 리턴
     */
    public EventDetailResDto save(int storeId, EventSaveReqDto eventSaveReqDto) {
        int eventId = saveEvent(storeId, eventSaveReqDto);
        saveEventActiveTime(eventId, eventSaveReqDto);
        return find(storeId, eventId);
    }

    /**
     * @author 엄상은
     * @since 2024/02/19
     * 어드민용 이벤트 저장
     */
    private int saveEvent(int storeId, EventSaveReqDto eventSaveReqDto) {
        eventSaveReqDto.setStoreId(storeId);
        eventMapper.save(eventSaveReqDto);
        int eventId = eventSaveReqDto.getId();
        return eventId;
    }

    /**
     * @author 엄상은
     * @since 2024/02/19
     * 이벤트 활성화 시간대만 저장
     */
    private void saveEventActiveTime(int eventId, EventSaveReqDto eventSaveReqDto) {
        eventSaveReqDto.setDefaultActiveTimeIfEmpty();
        eventSaveReqDto.getActiveTimeList().forEach(eventActiveTime -> {
            eventActiveTime.setEventId(eventId);
            eventActiveTimeMapper.save(eventActiveTime);
        });
    }

    /**
     * @author 엄상은
     * @since 2024/02/19
     * 어드민용 이벤트 수정
     */
    public EventSaveReqDto update(int storeId, int eventId, EventSaveReqDto eventSaveReqDto) {
        findEventAndValidate(storeId, eventId);
        eventSaveReqDto.setId(eventId);
        eventMapper.update(eventSaveReqDto);
        return eventSaveReqDto;
    }

    /**
     * @author 엄상은
     * @since 2024/02/19
     * 어드민용 이벤트 삭제
     */
    public Void delete(int storeId, int eventId) {
        findEventAndValidate(storeId, eventId);
        eventMapper.delete(eventId);
        return null;
    }

    /**
     * @author 엄상은
     * @since 2024/02/19
     * 어드민용 유저를 이벤트에 참여시킴
     */
    public EventParticipateResDto participateEvent(String memberId, int eventId) {
        EventDetailResDto eventDetailResDto = findAvailableEvent(eventId);
        EventParticipateResDto eventVisitResDto = EventParticipateResDto.of(eventDetailResDto);
        if (eventDetailResDto.getRewardType() == RewardType.COUPON) {
            int couponId = eventDetailResDto.getCouponId();
            Coupon coupon = findCoupon(couponId);
            eventVisitResDto.updateCoupon(coupon);
            saveMemberCoupon(memberId, couponId, "OFFLINE");
        }
        visitEvent(memberId, eventId);
        return eventVisitResDto;
    }

    /**
     * @author 엄상은
     * @since 2024/02/23
     * 쿠폰 조회
     */
    private Coupon findCoupon(int couponId) {
        Coupon coupon = couponMapper.findById(couponId);
        if (coupon == null) {
            throw new IllegalArgumentException("해당 쿠폰이 존재하지 않습니다.");
        }
        return coupon;
    }

    /**
     * @author 엄상은
     * @since 2024/02/23
     * 멤버 쿠폰 저장 (멤버가 쿠폰을 얻음)
     */
    private void saveMemberCoupon(String memberId, int couponId, String channelType) {
        MemberCoupon memberCoupon = MemberCoupon.of(memberId, couponId, channelType);
        memberCouponMapper.saveMemberCoupon(memberCoupon);
    }

    /**
     * @author 엄상은
     * @since 2024/02/23
     * 멤버 이벤트 참여
     */
    private void visitEvent(String memberId, int eventId) {
        MemberEvent memberEvent = MemberEvent.of(eventId, memberId);
        memberEventMapper.saveMemberEvent(memberEvent);
        eventMapper.increaseVisitedCount(eventId);
    }

    /**
     * @author 황수영
     * @since 2024/02/20
     * 이벤트 종류에 따른 랜덤 스팟 조회 - 시연용
     */
    public EventDetailResDto getRandomSpotDetail(String eventType) {
        EventType randomSpotType = EventType.of(eventType);
        List<EventDetailResDto> events = eventMapper.findEventAllByEventType(randomSpotType);
        log.debug("입력된 이벤트 타입 : " + eventType + " => " + " 랜덤 스팟 타입 : " + randomSpotType);

        if (events.isEmpty()) {
            log.error("해당 타입의 이벤트가 존재하지 않습니다.");
            throw new AdventureOfHeendyException(ErrorCode.EVENT_NOT_EXIST);
        }
        EventDetailResDto eventDetailResDto = events.get(0);
        List<EventActiveTimeZoneDto> activeTimeZoneList = eventActiveTimeMapper.findByEventId(eventDetailResDto.getId());
        eventDetailResDto.setActiveTimeList(activeTimeZoneList);
        log.debug("랜덤 스팟 상세 정보 조회 : " + eventDetailResDto);
        return eventDetailResDto;
    }

    /**
     * @author 황수영
     * @since 2024/02/20
     * 이벤트 종류에 따른 랜덤 스팟 조회 - Redis 사용
     */
    public EventDetailResDto getRandomSpotDetailByCache(String eventType) {
        EventType randomSpotType = EventType.of(eventType);
        int storeId = redisService.getRandomSpotByRandom(String.valueOf(randomSpotType));
        EventDetailResDto eventDetailResDto = eventMapper.findById(storeId);

        List<EventActiveTimeZoneDto> activeTimeZoneList = eventActiveTimeMapper.findByEventId(storeId);
        eventDetailResDto.setActiveTimeList(activeTimeZoneList);
        log.debug("랜덤 스팟 상세 정보 조회 : " + eventDetailResDto);
        return eventDetailResDto;
    }

    /**
     * @author 최성혁
     * @since 2024/02/27
     * 특정 이벤트에 참여중인 회원 목록
     */
    public List<MemberEventDetailsResDto> findEventParticipants(int eventId, int id) {
        List<MemberEventDetailsResDto> participants = memberEventMapper.getMemberEventDetails(eventId);

        log.debug("Event ID " + eventId + "에 대한 참여자 상세 정보: " + participants);

        return memberEventMapper.getMemberEventDetails(eventId);
    }
}
