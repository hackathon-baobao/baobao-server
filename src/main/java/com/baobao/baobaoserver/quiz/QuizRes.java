package com.baobao.baobaoserver.quiz;

public record QuizRes(
        String content,
        Level level
) {
    public static QuizRes of(Quiz quiz){
        return new QuizRes(quiz.getContent(), quiz.getLevel());
    }
}
