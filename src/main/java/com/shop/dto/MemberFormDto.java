package com.shop.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 회원 가입 정보를 담는 dto 생성
 * **/

@Getter
@Setter
public class MemberFormDto {
    private String name;
    private String email;
    private String password;
    private String address;
}
