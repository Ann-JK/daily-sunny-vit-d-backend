package ee.annjakubel.repository;

import ee.annjakubel.entity.UVData;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface UVDataJdbcRepository extends CrudRepository<UVData, Long> {
    UVData save(UVData data);
}
