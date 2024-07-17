package com.baobao.baobaoserver.quiz;

import com.baobao.baobaoserver.common.Response;
import com.baobao.baobaoserver.common.ResponseData;
import com.baobao.baobaoserver.member.Member;
import com.baobao.baobaoserver.member.MemberRepository;
import com.baobao.baobaoserver.member.MemberSessionHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final MemberSessionHolder sessionHolder;
    private final MemberRepository memberRepository;

    public Response append(QuizAppendReq req){
        quizRepository.save(req.toEntity());
        return Response.ok("퀴즈 생성 성공");
    }

    public ResponseData<QuizRes> read(Level level){
        Quiz quiz = quizRepository.findRandomQuiz(level);
        return ResponseData.ok("퀴즈 조회 성공", QuizRes.of(quiz));
    }

    public ResponseData<Boolean> solve(Long quizId, Boolean answer){
        Quiz quiz = quizRepository.findById(quizId).get();
        if(quiz.getAnswer().equals(answer)){
            Member member = sessionHolder.current();
            member.addPoint(1L);
            memberRepository.save(member);
        }
        return ResponseData.ok("정답 확인 성공", quiz.getAnswer().equals(answer));
    }
}
