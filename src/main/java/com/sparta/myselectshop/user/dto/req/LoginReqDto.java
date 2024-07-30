package com.sparta.myselectshop.user.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LoginReqDto {
    private String username;
    private String password;
}
