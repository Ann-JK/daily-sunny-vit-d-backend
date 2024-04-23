package ee.annjakubel.repository;

import ee.annjakubel.auth.entity.Requestor;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import jakarta.validation.constraints.NotBlank;

import java.util.Optional;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface RequestorJdbcRepository extends CrudRepository<Requestor, Long> {
    Optional<Requestor> findByUsername(@NonNull @NotBlank String username);
}
