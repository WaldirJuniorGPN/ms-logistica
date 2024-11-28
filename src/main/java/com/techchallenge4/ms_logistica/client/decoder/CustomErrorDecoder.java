package com.techchallenge4.ms_logistica.client.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techchallenge4.ms_logistica.exception.FeignClientException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;

import static java.util.Objects.nonNull;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        ExceptionMessage exception;
        try (InputStream bodyIs = response.body().asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            exception = mapper.readValue(bodyIs, ExceptionMessage.class);
        } catch (IOException e) {
            return new Exception(e.getMessage());
        }
        return switch (response.status()) {
            case 400 -> buildFeignClientException(exception, "Bad Request", 400);
            case 401 -> buildFeignClientException(exception, "Unauthorized", 401);
            case 403 -> buildFeignClientException(exception, "Forbidden", 403);
            case 404 -> buildFeignClientException(exception, "Not Found", 404);
            default -> buildFeignClientException(exception, "Internal Server Error", 500);
        };
    }

    private static @NotNull FeignClientException buildFeignClientException(ExceptionMessage exception, String defaultMessage, int status) {
        var message = nonNull(exception.getMessage()) ? exception.getMessage() : defaultMessage;
        return new FeignClientException(message, status);
    }

}