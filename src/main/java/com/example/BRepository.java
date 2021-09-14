package com.example;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface BRepository extends CrudRepository<B, String> {

    @Override
    List<B> findAll();

    @Override
    @Join(value = "as", type = Join.Type.LEFT_FETCH)
    Optional<B> findById(@NotNull String s);
}
