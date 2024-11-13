package com.boostify.boostify_back.controller.dto;

import com.boostify.boostify_back.enums.Status;
import jakarta.validation.constraints.NotNull;

public record TaskStatusDTO(@NotNull Status status) {
}
