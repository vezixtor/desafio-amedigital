package com.herokuapp.desafioamedigital.exception;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandling {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ExceptionDTO> showMessage(ApplicationException exception) {
        List<String> message = exception.getMessages().stream()
                .map(m -> messageSource.getMessage(m, null, LocaleContextHolder.getLocale()))
                .collect(Collectors.toList());
        int code = exception.getDefaultCode().value();

        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setCode(code);
        exceptionDTO.setDescription(message);

        return new ResponseEntity<>(exceptionDTO, exception.getDefaultCode());
    }

    @Data
    public class ExceptionDTO {
        private int code;
        private List<String> description;
    }
}
