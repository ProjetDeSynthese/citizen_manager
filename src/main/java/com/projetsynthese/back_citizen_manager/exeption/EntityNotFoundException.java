package com.projetsynthese.back_citizen_manager.exeption;

public class EntityNotFoundException extends  RuntimeException{
    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
