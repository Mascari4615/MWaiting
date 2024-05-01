package com.mascari4615.mwaiting.member.repository;

import com.mascari4615.mwaiting.member.repository.entity.MemberEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void crudTest() {
        MemberEntity member = MemberEntity.builder()
                .id(0L)
                .memberEmail("test@gmail.com")
                .memberName("test")
                .build();

        // create test
        memberRepository.save(member);

        //get test
        MemberEntity foundMember = memberRepository.findById(1L).get();
    }
}
