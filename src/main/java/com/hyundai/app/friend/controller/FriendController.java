package com.hyundai.app.friend.controller;

import com.hyundai.app.common.AdventureOfHeendyResponse;
import com.hyundai.app.friend.dto.FriendDetailResponse;
import com.hyundai.app.friend.dto.MbtiSaveRequest;
import com.hyundai.app.friend.dto.FriendListResponse;
import com.hyundai.app.friend.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author : 엄상은
 * @since : 2024/02/09
 * 친구 관련 API
 */
@RequiredArgsConstructor
@RequestMapping("/friends")
@RestController
public class FriendController {

    private final FriendService friendService;

    @GetMapping
    public AdventureOfHeendyResponse<FriendListResponse> findFriendList() {
        // TODO: 로그인 구현 후 사용자 ID 가져오기
        int memberId = 4;
        return AdventureOfHeendyResponse.success("친구 목록을 가져왔습니다.", friendService.findFriendList(memberId));
    }

    @GetMapping("/{friendId}")
    public AdventureOfHeendyResponse<FriendDetailResponse> find(@PathVariable final int friendId) {
        // TODO: 로그인 구현 후 사용자 ID 가져오기
        int memberId = 4;
        return AdventureOfHeendyResponse.success("친구 흰디에 방문했습니다.", friendService.findFriend(memberId, friendId));
    }

    @PostMapping("/{friendId}/mbti")
    public AdventureOfHeendyResponse<String> saveMbti(@PathVariable final int friendId,
                                                      @Valid @RequestBody final MbtiSaveRequest mbtiSaveRequest) {
        // TODO: 로그인 구현 후 사용자 ID 가져오기
        int memberId = 4;
        return AdventureOfHeendyResponse.success("친구 MBTI를 저장했습니다.", friendService.updateMbti(memberId, friendId, mbtiSaveRequest));
    }
}
