package com.projetsynthese.back_citizen_manager.exeption;

public class EntityAlreadyExistsException  extends  RuntimeException{
    public EntityAlreadyExistsException() {
    }

    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
