package io.githun.shirohoo.files.storage;

import java.net.MalformedURLException;

public class StorageFileNotFoundException extends RuntimeException {
    public StorageFileNotFoundException(String msg) {
        super(msg);
    }

    public StorageFileNotFoundException(String msg, MalformedURLException e) {
        super(msg, e);
    }
}
