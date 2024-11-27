package com.techchallenge4.ms_logistica.exception;

import lombok.Getter;

@Getter
public class FeignClientException extends RuntimeException {

    private final Integer status;

    public FeignClientException(String message, Integer status) {
        super(message);
        this.status = status;
    }

}
