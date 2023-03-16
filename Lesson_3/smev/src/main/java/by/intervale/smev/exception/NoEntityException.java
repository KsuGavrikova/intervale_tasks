package by.intervale.smev.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NoEntityException extends RuntimeException {

    private String entity;

    public NoEntityException(String message) {
        log.warn(this.entity = entity + " not found");
    }

    @Override
    public String toString() {
        this.entity = entity + " not found";
        log.warn(entity);
        return entity;
    }
}