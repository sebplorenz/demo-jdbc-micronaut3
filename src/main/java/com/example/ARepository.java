package com.example;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface ARepository extends CrudRepository<A, String> {

    @Override
    @Join(value = "bs", type = Join.Type.LEFT_FETCH)
    List<A> findAll();

    @Override
    @Join(value = "bs", type = Join.Type.LEFT_FETCH)
    Optional<A> findById(@NotNull String s);
}
