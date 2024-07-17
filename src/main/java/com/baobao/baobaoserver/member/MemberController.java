package com.baobao.baobaoserver.member;

import com.baobao.baobaoserver.common.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PatchMapping("/school")
    public Response updateSchool(@RequestParam String school){
        return memberService.appendSchool(school);
    }

}
