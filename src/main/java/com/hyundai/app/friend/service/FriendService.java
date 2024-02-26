package com.hyundai.app.friend.service;

import com.hyundai.app.member.domain.Member;
import com.hyundai.app.friend.domain.MemberConnection;
import com.hyundai.app.friend.dto.FriendDetailResDto;
import com.hyundai.app.friend.dto.FriendDto;
import com.hyundai.app.friend.dto.MbtiSaveReqDto;
import com.hyundai.app.friend.dto.FriendListResDto;
import com.hyundai.app.friend.enumType.GameStatus;
import com.hyundai.app.friend.mapper.FriendMapper;
import com.hyundai.app.friend.mapper.GameMapper;
import com.hyundai.app.friend.mapper.MbtiMapper;
import com.hyundai.app.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 엄상은
 * @since 2024/02/09
 * 친구 관련 서비스
 */
@Log4j
@RequiredArgsConstructor
@Service
public class FriendService {
    private final FriendMapper friendMapper;
    private final MemberMapper memberMapper;
    private final MbtiMapper mbtiMapper;
    private final GameMapper gameMapper;

    public FriendListResDto findFriendList(String memberId) {
        List<FriendDetailResDto> friendDetailResDto = friendMapper.findFriendList(memberId);
        FriendListResDto friendListResDto = new FriendListResDto(friendDetailResDto);
        return friendListResDto;
    }

    public FriendDetailResDto findFriend(String memberId, String friendId) {
        FriendDto friendDto = new FriendDto(memberId, friendId);
        if (!isFriend(friendDto)) {
            saveConnection(friendDto);
        }
        Member friend = memberMapper.findById(friendId);
        String mbtiContent = findMbtiByFriend(friendDto);
        GameStatus gameStatus = findGameStatus(friendDto);
        return FriendDetailResDto.builder()
                .id(friend.getId())
                .nickname(friend.getNickname())
                .imgUrl(friend.getImgUrl())
                .mbti(mbtiContent)
                .gameStatus(gameStatus)
                .build();
    }

    private MemberConnection findConnection(FriendDto friendDto) {
        return friendMapper.findConnection(friendDto);
    }

    private void saveConnection(FriendDto friendDto) {
        MemberConnection originMemberConnection = new MemberConnection(
                friendDto.getMemberId(),
                friendDto.getFriendId()
        );
        MemberConnection respondingMemberConnection = new MemberConnection(
                friendDto.getFriendId(),
                friendDto.getMemberId()
        );
        if (friendMapper.save(originMemberConnection) != 1) {
            throw new IllegalArgumentException("친구 추가에 실패했습니다.");
        }
        if (friendMapper.save(respondingMemberConnection) != 1) {
            throw new IllegalArgumentException("친구 추가에 실패했습니다.");
        }
    }

    private Boolean isFriend(FriendDto friendDto) {
        if (memberMapper.findById(friendDto.getFriendId()) == null) {
            throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
        }
        if (findConnection(friendDto) == null) {
            return false;
        }
        return true;
    }

    private String findMbtiByFriend(FriendDto friendDto) {
        return mbtiMapper.findMbtiByFriend(friendDto);
    }

    public String updateMbti(String memberId, String friendId, MbtiSaveReqDto mbtiSaveReqDto) {
        String mbtiId = mbtiMapper.findIdByMbtiScore(mbtiSaveReqDto);
        MemberConnection savedMemberConnection = new MemberConnection(
                memberId,
                friendId,
                mbtiId
        );
        if (friendMapper.updateMbti(savedMemberConnection) != 1) {
            throw new IllegalArgumentException("MBTI 저장에 실패했습니다.");
        }
        return findMbtiByFriend(new FriendDto(memberId, friendId));
    }

    private GameStatus findGameStatus(FriendDto friendDto){
        GameStatus gameStatus = gameMapper.findGameStatus(friendDto);
        return gameStatus;
    }
}
