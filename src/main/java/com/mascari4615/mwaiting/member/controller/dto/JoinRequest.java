package com.mascari4615.mwaiting.member.controller.dto;

import lombok.Data;

@Data
public class JoinRequest {
    private String id;

    private String name;

    private String phoneNumber;

    // Alt + Insert
    // Getter Setter, ToString 메소드 등 자동 생성 가능
}
