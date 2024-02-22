package com.hyundai.app.friend.controller;

import com.hyundai.app.common.AdventureOfHeendyResponse;
import com.hyundai.app.friend.dto.FriendDetailResDto;
import com.hyundai.app.friend.dto.MbtiSaveReqDto;
import com.hyundai.app.friend.dto.FriendListResDto;
import com.hyundai.app.friend.service.FriendService;
import com.hyundai.app.security.methodparam.MemberId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

/**
 * @author 엄상은
 * @since 2024/02/09
 * 친구 관련 API
 */
@Api("친구 관련 API")
@RequiredArgsConstructor
@RequestMapping("/api/v1/friends")
@RestController
public class FriendController {

    private final FriendService friendService;

    @GetMapping
    @ApiOperation("전체 친구 리스트 조회 API")
    public AdventureOfHeendyResponse<FriendListResDto> findFriendList(@ApiIgnore @MemberId Integer memberId) {
        return AdventureOfHeendyResponse.success("친구 목록을 가져왔습니다.", friendService.findFriendList(memberId));
    }

    @GetMapping("/{friendId}")
    @ApiOperation("특정 친구 조회 API")
    public AdventureOfHeendyResponse<FriendDetailResDto> find(@ApiIgnore @MemberId Integer memberId,
                                                              @PathVariable final int friendId) {
        return AdventureOfHeendyResponse.success("친구 흰디에 방문했습니다.", friendService.findFriend(memberId, friendId));
    }

    @PostMapping("/{friendId}/mbti")
    @ApiOperation("친구 MBTI 작성 API")
    public AdventureOfHeendyResponse<String> saveMbti(@ApiIgnore @MemberId Integer memberId,
                                                      @PathVariable final int friendId,
                                                      @Valid @RequestBody final MbtiSaveReqDto mbtiSaveReqDto) {
        return AdventureOfHeendyResponse.success("친구 MBTI를 저장했습니다.", friendService.updateMbti(memberId, friendId, mbtiSaveReqDto));
    }
}
