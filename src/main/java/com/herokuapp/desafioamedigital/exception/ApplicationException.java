package com.herokuapp.desafioamedigital.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class ApplicationException extends RuntimeException {
    private HttpStatus defaultCode;
    private List<String> messages = new ArrayList<>();

    public ApplicationException() {
        this.defaultCode = HttpStatus.BAD_REQUEST;
    }

    public ApplicationException(HttpStatus httpStatus, String... messages) {
        super(String.join("\n", messages));
        this.defaultCode = httpStatus;
        this.messages = Arrays.asList(messages);
    }
}
