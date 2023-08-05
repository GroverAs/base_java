package com.urise.webapp.exception;

import java.sql.SQLException;

public class StorageException extends RuntimeException{
private final String uuid;


    public StorageException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public StorageException(String message, String uuid, Exception e) {
        super(message);
        this.uuid = uuid;
    }

    public StorageException(Exception e) {
        this(e.getMessage(), String.valueOf(e));
    }

    public String getUuid() {
        return uuid;
    }
}
