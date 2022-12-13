package com.example.technicalservice.core.exceptions;

import com.example.technicalservice.core.results.ErrorDataResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlers {
    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationExceptions(
            MethodArgumentNotValidException argumentNotValidException){
        Map<String,String> validationErrors = new HashMap<>();
        for (FieldError fieldError : argumentNotValidException.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ErrorDataResult<Object> errorDataResult =new ErrorDataResult<>(validationErrors,
                "Validation Errors");
        log.error(errorDataResult.getMessage());
        return errorDataResult;
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException httpMessageNotReadableException){
        ErrorDataResult<Object> errorJsonResult = new ErrorDataResult<>(httpMessageNotReadableException.getMessage(),
                "Json Parse Error");
        log.error(errorJsonResult.getMessage());
        return errorJsonResult;
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleBusinessExceptions(BusinessException businessException) {
        ErrorDataResult<Object> errorDataResult = new ErrorDataResult<Object>(businessException.getMessage(),
                "Business Exception");
        log.error(errorDataResult.getMessage());
        return errorDataResult;
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDeniedException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
                "Access denied. You must be authenticate", new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

}
