package br.com.fiap.localtech.locatech.validators.annotations;

import br.com.fiap.localtech.locatech.validators.DataInicioAntesOuIgualAFimValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DataInicioAntesOuIgualAFimValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataInicioAntesOuIgualAFim {
    String message() default "Data de início deve ser anterior ou igual à data de fim";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String dataInicio();
    String dataFim();
}
