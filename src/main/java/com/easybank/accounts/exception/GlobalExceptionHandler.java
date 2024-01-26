package com.easybank.accounts.exception;

import com.easybank.accounts.dto.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
         //Exception Handling for validation with help of  extending class ResponseEntityExceptionHandler
        Map<String, String> validationError=new HashMap<>();
        List<ObjectError> validationErrorList=ex.getBindingResult().getAllErrors();

        validationErrorList.forEach((error)->{
            String fieldName=((FieldError)error).getField();
            String validationMsg=error.getDefaultMessage();
            validationError.put(fieldName,validationMsg);
        });

        return new ResponseEntity<>(validationError,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({Exception.class,})
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception, WebRequest webRequest) {
        ErrorResponseDto response = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ResourceNotFoundException.class,})
    public ResponseEntity<ErrorResponseDto> handleCustomerNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
        ErrorResponseDto response = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({CustomerAlreadyExistsException.class,})
    public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException exception, WebRequest webRequest) {
        ErrorResponseDto response = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
