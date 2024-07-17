package com.baobao.baobaoserver.member;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/school")
@RequiredArgsConstructor
public class SchoolController {
    private final SchoolRepository schoolRepository;

    @PostMapping
    @Transactional
    public void insert(@RequestBody SchoolReq school){
        System.out.println(school.school());
        List<School> schools = school.school().stream()
                .map(s->School.builder().name(s).build()).toList();
        System.out.println(schools);
        schoolRepository.saveAll(schools);
    }
}
