package com.mascari4615.mwaiting.member.repository.entity;

import com.mascari4615.mwaiting.member.controller.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Table(name = "member_table")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AutoIncrement
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(unique = true) // unique 제약 조건 추가
    private String memberEmail; // 카멜 케이스로 만들어둬도, DB 들어갈때는 _들어감 (설정으로 변경 가능)

    @Column
    private String memberPassword;

    @Column
    private String memberName;

    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity member = new MemberEntity();
        member.setMemberEmail(memberDTO.getMemberEmail());
        member.setMemberPassword(memberDTO.getMemberPassword());
        member.setMemberName(memberDTO.getMemberName());
        return member;
    }
}
