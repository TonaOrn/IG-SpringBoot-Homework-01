package com.ig.training.controllers;

import com.ig.training.base.response.MessageResponse;
import com.ig.training.dto.UserDto;
import com.ig.training.services.UserService;
import com.ig.training.utilities.constants.Constant;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = Constant.MAIN_PATH + "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public MessageResponse registerUser(@RequestBody UserDto user) {
        return userService.registerUser(user.toUser());
    }

    @PutMapping("/update/{id}")
    public MessageResponse updateUser(@PathVariable Long id, @Valid @RequestBody UserDto user) {
        return userService.updateUser(id, user);
    }
}
