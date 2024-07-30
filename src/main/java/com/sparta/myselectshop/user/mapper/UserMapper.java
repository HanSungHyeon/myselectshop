package com.sparta.myselectshop.user.mapper;

import com.sparta.myselectshop.user.dto.req.SignupReqDto;
import com.sparta.myselectshop.user.dto.res.UserInfo;
import com.sparta.myselectshop.user.entity.User;
import com.sparta.myselectshop.user.entity.UserRoleEnum;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
	User toUser(SignupReqDto dto, UserRoleEnum role);

	UserInfo toUserInfo(String username, boolean admin);
}
