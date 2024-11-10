package com.boostify.boostify_back.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RestErrorMessage {

    private int httpStatuscode;
    private String message;
}
