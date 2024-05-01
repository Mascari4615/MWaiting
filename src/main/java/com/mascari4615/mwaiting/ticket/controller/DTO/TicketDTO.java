package com.mascari4615.mwaiting.ticket.controller.DTO;

import com.mascari4615.mwaiting.member.repository.entity.MemberEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TicketDTO {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;

    public static com.mascari4615.mwaiting.member.controller.dto.MemberDTO toMemberDTO(MemberEntity memberEntity) {
        com.mascari4615.mwaiting.member.controller.dto.MemberDTO memberDTO = new com.mascari4615.mwaiting.member.controller.dto.MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDTO.setMemberName(memberEntity.getMemberName());
        return memberDTO;
    }
}
