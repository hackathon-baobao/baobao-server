package com.baobao.baobaoserver.challenge;

import com.baobao.baobaoserver.common.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/challenge-applicant")
@RequiredArgsConstructor
public class ChallengeApplicantController {
    private final ChallengeApplicantService challengeApplicantService;

    @PostMapping
    public Response append(@RequestParam Long id){
        return challengeApplicantService.append(id);
    }

    @DeleteMapping
    public Response delete(@RequestParam Long id){
        return challengeApplicantService.delete(id);
    }

    @GetMapping
    public Response read(@RequestParam Long id){
        return challengeApplicantService.read(id);
    }

    @PatchMapping("/complete")
    public Response complete(@RequestParam Long id){
        return challengeApplicantService.compete(id);
    }
}
