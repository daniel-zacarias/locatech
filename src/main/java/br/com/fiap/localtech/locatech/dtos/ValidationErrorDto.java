package br.com.fiap.localtech.locatech.dtos;

import java.util.List;

public record ValidationErrorDto(
        List<String> errors,
        int status
) {
}
