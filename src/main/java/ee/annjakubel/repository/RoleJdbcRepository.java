package ee.annjakubel.repository;


import ee.annjakubel.auth.entity.Role;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface RoleJdbcRepository extends CrudRepository<Role, Long> {
    Role save(String authority);
    Optional<Role> findByAuthority(String authority);
}
