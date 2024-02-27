package com.hyundai.app.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author 최성혁
 * @since 2024/02/27
 * 이벤트에 참여한 회원 목록 응답 객체
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberEventDetailsResDto {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime eventParticipationDate; // 이벤트 참여일
    private String memberEmail;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate memberBirth;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime memberJoinDate; // 멤버의 가입일
}
