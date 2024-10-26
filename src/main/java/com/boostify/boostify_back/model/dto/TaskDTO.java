package com.boostify.boostify_back.model.dto;

import com.boostify.boostify_back.enums.Priority;
import com.boostify.boostify_back.enums.Status;

import java.time.LocalDate;

public record TaskDTO(Integer idUser, String title, String description, Status status, LocalDate creationDate, Priority priority) {
}
