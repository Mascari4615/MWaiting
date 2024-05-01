package com.mascari4615.mwaiting.member.service;

import com.mascari4615.mwaiting.member.controller.dto.JoinRequest;
import com.mascari4615.mwaiting.member.controller.dto.MemberDTO;
import com.mascari4615.mwaiting.member.repository.MemberRepository;
import com.mascari4615.mwaiting.member.repository.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public String join(JoinRequest joinRequest) {
//        Member member = Member.builder()
//                .id(joinRequest.getId())
//                .name(joinRequest.getName())
//                .phoneNumber(joinRequest.getPhoneNumber())
//                .build();
//        memberRepository.save(member);
        return "success";
    }

    @Override
    public void save(MemberDTO memberDTO) {
        // 1. DTO -> Entity 변환 (여러 방식이 있음)
        // 2. Repository의 save 메소드 호출

        // Repository의 save 메소드 호출 (조건. entity 객체를 넘겨줘야 함)
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity); // 이건 이름 save 메소드여야 함
    }

    @Override
    // Entity를 어디까지 쓰냐? 도 구현마다 다름 (여기서는 컨트롤러는 DTO 쓰도록, 그래서 반환 DTO)
    public MemberDTO login(MemberDTO memberDTO) {
        // 1. 회원이 입력한 이메일로 DB에서 조회
        // 2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단

        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if (byMemberEmail.isPresent()) {
            // 조회 결과가 있다 (해당 이메일을 가진 회원 정보가 있다)
            MemberEntity memberEntity = byMemberEmail.get(); // Optional 벗기기
            System.out.println("memberEntity = " + memberEntity);
            if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
                return MemberDTO.toMemberDTO(memberEntity);
                // 비밀번호 일치
            } else {
                // 비밀번호 불일치 (로그인 실패)
                return null;
            }
        } else {
            // 조회 결과가 없다 (해당 이메일을 가진 회원이 없다)
            return null;
        }
    }

    @Override
    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (MemberEntity memberEntity : memberEntityList) {
            memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
        }
        return memberDTOList;
    }

    public MemberDTO findById(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        // return memberEntity.map(MemberDTO::toMemberDTO).orElse(null);

        if (optionalMemberEntity.isPresent()) {
//            MemberEntity memberEntity = optionalMemberEntity.get();
//            MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
//            return memberDTO;
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        } else {
            return null;
        }
    }
}
