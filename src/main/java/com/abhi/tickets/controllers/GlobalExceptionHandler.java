package com.abhi.tickets.controllers;

import com.abhi.tickets.domain.dtos.ErrorDto;
import com.abhi.tickets.exceptions.EventNotFoundException;
import com.abhi.tickets.exceptions.EventUpdateException;
import com.abhi.tickets.exceptions.TicketTypeNotFoundException;
import com.abhi.tickets.exceptions.UserNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(EventUpdateException.class)
    public ResponseEntity<ErrorDto> EventUpdateException(EventUpdateException ex) {
        log.error("Caught Event not update Error", ex);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError("Event not update");
        return  new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(TicketTypeNotFoundException.class)
    public ResponseEntity<ErrorDto> TicketTypeNotFoundException(TicketTypeNotFoundException ex) {
        log.error("Caught TicketType not found Error", ex);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError("Ticket not found");
        return  new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<ErrorDto> EventNotFoundException(EventNotFoundException ex) {
        log.error("Caught event not found Error", ex);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError("Event not found");
        return  new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDto> handleUserNotFoundException(UserNotFoundException ex) {
        log.error("Caught user not found Error", ex);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError("User not found");
        return  new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        log.error("Caught Error", ex);
        ErrorDto errorDto = new ErrorDto();

        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        String errorMessage = fieldErrors.stream()
                .findFirst()
                .map(fieldError ->fieldError.getField() + ": " +  fieldError.getDefaultMessage())
                .orElse("Violation error occured");

        errorDto.setError(errorMessage);
        return  new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDto> handleConstraintViolation(ConstraintViolationException ex) {
        log.error("Caught Error", ex);
        ErrorDto errorDto = new ErrorDto();
        String errorMessage = ex.getConstraintViolations().stream().findFirst().map(violation -> violation.getPropertyPath() + ": " + violation.getMessage()).orElse("Constraint violation occured");
        errorDto.setError(errorMessage);
        return  new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException(Exception exception) {
        log.error("Caught Error", exception);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError("An unknown error occurred");
        return  new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
