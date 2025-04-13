package br.com.fiap.localtech.locatech.validators;

import br.com.fiap.localtech.locatech.validators.annotations.ExisteId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ExisteIdValidator implements ConstraintValidator<ExisteId, Object> {

    private final JdbcTemplate jdbcTemplate;

    public ExisteIdValidator(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private String tabela;
    private String coluna;

    @Override
    public void initialize(ExisteId constraintAnnotation) {
        this.tabela = constraintAnnotation.tabela();
        this.coluna = constraintAnnotation.coluna();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) return false;

        String sql = "SELECT COUNT(*) FROM " + tabela + " WHERE " + coluna + " = ?";
        try {
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class, value);
            return count != null && count > 0;
        } catch (Exception e) {
            e.printStackTrace(); // ou log
            return false;
        }
    }
}
