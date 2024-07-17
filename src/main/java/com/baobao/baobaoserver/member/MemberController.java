package com.baobao.baobaoserver.member;

import com.baobao.baobaoserver.common.Response;
import com.baobao.baobaoserver.common.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PatchMapping("/school")
    public Response updateSchool(@RequestParam String school){
        return memberService.appendSchool(school);
    }

    @GetMapping("/ranks-school")
    public ResponseData<List<SchoolHeight>> getSchoolHeightRanks() {
        return memberService.getSchoolHeightRanks();
    }

    @GetMapping("/ranks-kakao")
    public ResponseData<List<KakaoHeight>> getKakaoHeightRanks() {
        return memberService.getKakaoHeightRanks();
    }

    @GetMapping("/point/my")
    public ResponseData<Long> getPoint(){
        return memberService.getPoint();
    }
}
