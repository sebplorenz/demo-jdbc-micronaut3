package com.example;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import lombok.Data;

import java.util.Set;

@Data
@MappedEntity
public class B {

    @Id
    String id;

    String value;

    @Relation(value = Relation.Kind.MANY_TO_MANY, mappedBy = "bs")
    Set<A> as;

}
