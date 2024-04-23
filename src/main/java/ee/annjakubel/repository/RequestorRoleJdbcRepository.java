package ee.annjakubel.repository;

import ee.annjakubel.auth.entity.RequestorRole;
import ee.annjakubel.auth.entity.RequestorRoleId;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface RequestorRoleJdbcRepository extends CrudRepository<RequestorRole, RequestorRoleId> {

    @Query("""
    SELECT authority FROM role
    INNER JOIN public.requestor_role ON requestor_role.id_role_id = role.id
    INNER JOIN public.requestor requestor_ ON requestor_role.id_requestor_id = requestor_.id 
    WHERE requestor_.username = :username""")
    List<String> findAllAuthoritiesByUsername(@NotBlank String username);
}
