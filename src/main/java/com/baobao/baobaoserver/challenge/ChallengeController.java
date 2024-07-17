package com.baobao.baobaoserver.challenge;

import com.baobao.baobaoserver.common.Response;
import com.baobao.baobaoserver.common.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenge")
@RequiredArgsConstructor
public class ChallengeController {
    private final ChallengeService challengeService;

    @PostMapping
    public Response append(ChallengeAppendReq req){
        return challengeService.append(req);
    }

    @GetMapping
    public ResponseData<List<ChallengeRes>> read(){
        return challengeService.read();
    }

    @GetMapping("/{id}")
    public ResponseData<ChallengeDescriptionRes> readById(@PathVariable Long id){
        return challengeService.readById(id);
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id){
        return challengeService.delete(id);
    }
}