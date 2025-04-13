package br.com.fiap.localtech.locatech.validators;

import br.com.fiap.localtech.locatech.validators.annotations.DataInicioAntesOuIgualAFim;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

import java.time.LocalDate;

public class DataInicioAntesOuIgualAFimValidator implements ConstraintValidator<DataInicioAntesOuIgualAFim, Object> {

    private String dataInicioField;
    private String dataFimField;

    @Override
    public void initialize(DataInicioAntesOuIgualAFim constraintAnnotation) {
        this.dataInicioField = constraintAnnotation.dataInicio();
        this.dataFimField = constraintAnnotation.dataFim();
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        try {
            var clazz = obj.getClass();
            var dataInicio = (LocalDate) new BeanWrapperImpl(obj).getPropertyValue(dataInicioField);
            var dataFim = (LocalDate) new BeanWrapperImpl(obj).getPropertyValue(dataFimField);

            if (dataInicio == null || dataFim == null) return true;

            return !dataInicio.isAfter(dataFim);
        } catch (Exception e) {
            return false;
        }
    }
}

