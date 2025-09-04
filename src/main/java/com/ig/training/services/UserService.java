package com.ig.training.services;

import com.ig.training.base.response.MessageResponse;
import com.ig.training.base.response.ObjectResponse;
import com.ig.training.dto.LogInReqDto;
import com.ig.training.dto.LoginDto;
import com.ig.training.dto.UserDto;
import com.ig.training.model.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    ObjectResponse<LoginDto> login(LogInReqDto loginDto);

    MessageResponse registerUser(Users req);

    MessageResponse updateUser(Long id, UserDto req);
}
