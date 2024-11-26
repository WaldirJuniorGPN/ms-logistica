package com.techchallenge4.ms_logistica.api.v1.handler;

import lombok.Builder;

@Builder
public record ErrorResponse(
        String timestamp,
        int status,
        String error,
        String message,
        String path,
        String errorCode
) {
}
