package com.mascari4615.mwaiting.member.controller.dto;

import com.mascari4615.mwaiting.member.repository.entity.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {
    private Long id;
    private String email;
    private String password;
    private String name;

    public static MemberDTO toMemberDTO(Member member) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(member.getId());
        memberDTO.setEmail(member.getEmail());
        memberDTO.setPassword(member.getPassword());
        memberDTO.setName(member.getName());
        return memberDTO;
    }
}
