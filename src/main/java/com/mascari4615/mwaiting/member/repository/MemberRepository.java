package com.mascari4615.mwaiting.member.repository;

import com.mascari4615.mwaiting.member.repository.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // 이메일로 회원 정보 조회 (select * from member_table where member_email=?)
    // Optional null방지
    Optional<Member> findByEmail(String email);
}
