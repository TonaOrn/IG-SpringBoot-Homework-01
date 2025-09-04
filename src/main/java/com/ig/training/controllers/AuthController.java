package com.ig.training.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ig.training.base.response.ObjectResponse;
import com.ig.training.dto.LogInReqDto;
import com.ig.training.dto.LoginDto;
import com.ig.training.services.UserService;
import com.ig.training.utilities.constants.Constant;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = Constant.MAIN_PATH + "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/log-in")
    public ObjectResponse<LoginDto> login(@Valid @RequestBody LogInReqDto req) throws JsonProcessingException {
        return userService.login(req);
    }
}
