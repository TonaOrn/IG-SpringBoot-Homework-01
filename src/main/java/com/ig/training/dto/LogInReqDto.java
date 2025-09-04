package com.ig.training.dto;

import jakarta.validation.constraints.NotBlank;

public record LogInReqDto(@NotBlank String username, @NotBlank String password) {
}
