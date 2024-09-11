package com.santaBarbaraFs.contabilizarGols.resource.exceptions;

import com.santaBarbaraFs.contabilizarGols.service.exceptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<Object> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandartError err = new StandartError(System.currentTimeMillis(), status.value(),
                "Não encontrado", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }
}
