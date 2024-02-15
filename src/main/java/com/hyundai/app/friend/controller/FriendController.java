package com.hyundai.app.friend.controller;

import com.hyundai.app.common.AdventureOfHeendyResponse;
import com.hyundai.app.friend.dto.FriendDetailResponse;
import com.hyundai.app.friend.dto.MbtiSaveRequest;
import com.hyundai.app.friend.dto.FriendListResponse;
import com.hyundai.app.friend.service.FriendService;
import com.hyundai.app.security.methodparam.MemberId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author : 엄상은
 * @since : 2024/02/09
 * 친구 관련 API
 */
@RequiredArgsConstructor
@RequestMapping("/api/v1/friends")
@RestController
public class FriendController {

    private final FriendService friendService;

    @GetMapping
    public AdventureOfHeendyResponse<FriendListResponse> findFriendList(@MemberId Integer memberId) {
        return AdventureOfHeendyResponse.success("친구 목록을 가져왔습니다.", friendService.findFriendList(memberId));
    }

    @GetMapping("/{friendId}")
    public AdventureOfHeendyResponse<FriendDetailResponse> find(@MemberId Integer memberId,
                                                                @PathVariable final int friendId) {
        return AdventureOfHeendyResponse.success("친구 흰디에 방문했습니다.", friendService.findFriend(memberId, friendId));
    }

    @PostMapping("/{friendId}/mbti")
    public AdventureOfHeendyResponse<String> saveMbti(@MemberId Integer memberId,
                                                      @PathVariable final int friendId,
                                                      @Valid @RequestBody final MbtiSaveRequest mbtiSaveRequest) {
        return AdventureOfHeendyResponse.success("친구 MBTI를 저장했습니다.", friendService.updateMbti(memberId, friendId, mbtiSaveRequest));
    }
}
