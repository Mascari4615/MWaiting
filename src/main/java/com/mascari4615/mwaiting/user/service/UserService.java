package com.mascari4615.mwaiting.user.service;

import com.mascari4615.mwaiting.user.controller.dto.JoinRequest;
import com.mascari4615.mwaiting.user.controller.dto.UserDTO;

import java.util.List;

public interface UserService {
    String join(JoinRequest joinRequest);

    void save(UserDTO userDTO);

    UserDTO login(UserDTO userDTO);

    List<UserDTO> findAll();

    UserDTO findById(Long id);
}
