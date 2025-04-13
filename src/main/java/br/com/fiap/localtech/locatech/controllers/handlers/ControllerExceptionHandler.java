package br.com.fiap.localtech.locatech.controllers.handlers;

import br.com.fiap.localtech.locatech.controllers.AluguelController;
import br.com.fiap.localtech.locatech.controllers.AluguelControllerV2;
import br.com.fiap.localtech.locatech.controllers.PessoaControllerV2;
import br.com.fiap.localtech.locatech.controllers.VeiculoControllerV2;
import br.com.fiap.localtech.locatech.dtos.ResourceNotFoundDTO;
import br.com.fiap.localtech.locatech.dtos.RuntimeExceptionDTO;
import br.com.fiap.localtech.locatech.dtos.ValidationErrorDto;
import br.com.fiap.localtech.locatech.services.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice(assignableTypes = {
        AluguelController.class,
        AluguelControllerV2.class,
        PessoaControllerV2.class,
        VeiculoControllerV2.class
})
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResourceNotFoundDTO> handlerResourceNotFound(ResourceNotFoundException e) {
        var status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status.value()).body(new ResourceNotFoundDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDto> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var status = HttpStatus.BAD_REQUEST;
        List<String> errors = new ArrayList<>();
        for (var error : e.getBindingResult().getFieldErrors()) {
            errors.add(error.getDefaultMessage());
        }
        for (var objectError : e.getBindingResult().getGlobalErrors()) {
            errors.add(objectError.getDefaultMessage());
        }
        return ResponseEntity.status(status.value()).body(new ValidationErrorDto(errors, status.value()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<RuntimeExceptionDTO> handlerRuntimeException(RuntimeException e) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status.value()).body(new RuntimeExceptionDTO(e.getMessage(), status.value()));
    }

}
