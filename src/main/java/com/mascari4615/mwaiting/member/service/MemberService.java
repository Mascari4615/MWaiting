package com.mascari4615.mwaiting.member.service;

import com.mascari4615.mwaiting.member.controller.dto.JoinRequest;
import com.mascari4615.mwaiting.member.controller.dto.MemberDTO;

import java.util.List;

public interface MemberService {
    String join(JoinRequest joinRequest);

    void save(MemberDTO memberDTO);

    MemberDTO login(MemberDTO memberDTO);

    List<MemberDTO> findAll();

    MemberDTO findById(Long id);
}
