package com.baobao.baobaoserver.quiz;

public record QuizAppendReq(
        String description,
        Boolean answer,
        Level level
) {
    public Quiz toEntity(){
        return Quiz.builder()
                .answer(answer)
                .content(description)
                .level(level)
                .build();
    }
}
