package com.simek.offero.application.controlers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ErrorResponse {
    private final String code;
    private final String type;
    private final String message;
}
