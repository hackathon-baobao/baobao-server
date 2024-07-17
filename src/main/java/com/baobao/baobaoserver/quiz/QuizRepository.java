package com.baobao.baobaoserver.quiz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    @Query("SELECT q FROM tbl_quiz q WHERE q.level = :level ORDER BY function('RAND')")
    Quiz findRandomQuiz(@Param("level") Level level);
}
