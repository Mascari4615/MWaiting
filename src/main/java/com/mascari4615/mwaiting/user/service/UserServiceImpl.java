package com.mascari4615.mwaiting.user.service;

import com.mascari4615.mwaiting.user.controller.dto.JoinRequest;
import com.mascari4615.mwaiting.user.controller.dto.UserDTO;
import com.mascari4615.mwaiting.user.repository.UserRepository;
import com.mascari4615.mwaiting.user.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public String join(JoinRequest joinRequest) {
//        User user = User.builder()
//                .id(joinRequest.getId())
//                .name(joinRequest.getName())
//                .phoneNumber(joinRequest.getPhoneNumber())
//                .build();
//        userRepository.save(user);
        return "success";
    }

    @Override
    public void save(UserDTO userDTO) {
        // 1. DTO -> Entity 변환 (여러 방식이 있음)
        // 2. Repository의 save 메소드 호출

        // Repository의 save 메소드 호출 (조건. entity 객체를 넘겨줘야 함)
        User user = User.toUser(userDTO);
        userRepository.save(user); // 이건 이름 save 메소드여야 함
    }

    @Override
    // Entity를 어디까지 쓰냐? 도 구현마다 다름 (여기서는 컨트롤러는 DTO 쓰도록, 그래서 반환 DTO)
    public UserDTO login(UserDTO userDTO) {
        // 1. 회원이 입력한 이메일로 DB에서 조회
        // 2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단

        Optional<User> byUserEmail = userRepository.findByEmail(userDTO.getEmail());
        if (byUserEmail.isPresent()) {
            // 조회 결과가 있다 (해당 이메일을 가진 회원 정보가 있다)
            User user = byUserEmail.get(); // Optional 벗기기
            System.out.println("user = " + user);
            if (user.getPassword().equals(userDTO.getPassword())) {
                return UserDTO.toUserDTO(user);
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
    public List<UserDTO> findAll() {
        List<User> userList = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userList) {
            userDTOList.add(UserDTO.toUserDTO(user));
        }
        return userDTOList;
    }

    public UserDTO findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        // return user.map(UserDTO::toUserDTO).orElse(null);

        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            UserDTO userDTO = UserDTO.toUserDTO(user);
//            return userDTO;
            return UserDTO.toUserDTO(optionalUser.get());
        } else {
            return null;
        }
    }
}
