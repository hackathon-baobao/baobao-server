package com.baobao.baobaoserver.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, String> {
    Member findByEmail(String email);
    Boolean existsByEmail(String email);
    @Query("SELECT m.school, SUM(m.height) AS totalHeight " +
            "FROM tbl_member m " +
            "GROUP BY m.school " +
            "ORDER BY totalHeight DESC")
    List<SchoolHeight> findSchoolHeights();
    List<Member> findByNameInOrderByPointDesc(List<String> names);
}
