package io.githun.shirohoo.files.storage;

import java.io.IOException;

public class StorageException extends RuntimeException {
    public StorageException(String msg) {
        super(msg);
    }

    public StorageException(String msg, IOException e) {
        super(msg, e);
    }
}
