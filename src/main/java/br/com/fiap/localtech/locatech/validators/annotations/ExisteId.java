package br.com.fiap.localtech.locatech.validators.annotations;

import br.com.fiap.localtech.locatech.validators.ExisteIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExisteIdValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExisteId {
    String message() default "ID não encontrado";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String tabela();
    String coluna() default "id";
}
