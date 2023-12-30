package com.projetsynthese.back_citizen_manager.exeption.handler;

import com.projetsynthese.back_citizen_manager.exeption.EntityAlreadyExistsException;
import com.projetsynthese.back_citizen_manager.exeption.EntityNotFoundException;
import com.projetsynthese.back_citizen_manager.exeption.message.Message;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AppExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {EntityNotFoundException.class})
    public @ResponseBody  Message handleException(EntityNotFoundException exception){
        return  Message.builder()
                .code(404)
                .message(exception.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = {EntityAlreadyExistsException.class})
    public @ResponseBody Message handleException(EntityAlreadyExistsException exception){
        return  Message.builder()
                .code(409)
                .message(exception.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(value = {RuntimeException.class})
    public @ResponseBody Message handleException(RuntimeException exception){
        return  Message.builder()
                .code(406)
                .message(exception.getMessage())
                .build();
    }



}
