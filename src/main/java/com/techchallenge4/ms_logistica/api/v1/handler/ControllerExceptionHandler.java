package com.techchallenge4.ms_logistica.api.v1.handler;

import com.techchallenge4.ms_logistica.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(HttpServletRequest request, ResourceNotFoundException ex) {
        return handleException(request, HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(HttpServletRequest request, Exception ex) {
        return handleException(request, HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentNotValidException.class,
            MethodArgumentTypeMismatchException.class, MissingPathVariableException.class,
            MissingServletRequestPartException.class, IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handleBadRequestExceptions(HttpServletRequest request, Exception ex) {
        return handleException(request, HttpStatus.BAD_REQUEST, ex);
    }

    private ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, HttpStatus status, Exception ex) {
        return handleException(request, status, null, ex);
    }

    private ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, HttpStatus status, String errorCode, Exception ex) {
        var errorResponse = buildErrorResponse(status, status.getReasonPhrase(), ex.getMessage(), request.getRequestURI(), errorCode);
        log.error(errorResponse.toString(), ex);
        return new ResponseEntity<>(errorResponse, status);
    }

    private ErrorResponse buildErrorResponse(HttpStatus status, String error, String message, String path, String errorCode) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                .status(status.value())
                .error(error)
                .message(message)
                .path(path)
                .errorCode(errorCode)
                .build();
    }
}
