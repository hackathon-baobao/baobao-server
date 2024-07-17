package com.baobao.baobaoserver.quiz;

import com.baobao.baobaoserver.common.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @PostMapping
    public Response append(@RequestBody QuizAppendReq req){
        return quizService.append(req);
    }

    @GetMapping
    public Response read(@RequestParam Level level){
        return quizService.read(level);
    }

    @PostMapping("/solve")
    public Response solve(@RequestParam Long id, @RequestParam Boolean answer){
        return quizService.solve(id, answer);
    }
}
