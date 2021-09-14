package com.example;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import io.micronaut.data.jdbc.annotation.JoinTable;
import lombok.Data;

import java.util.Set;

@Data
@MappedEntity
public class A {

    @Id
    String id;

    String value;

    @JoinTable(name = "ab")
    @Relation(value = Relation.Kind.MANY_TO_MANY, cascade = Relation.Cascade.ALL)
    Set<B> bs;

}
