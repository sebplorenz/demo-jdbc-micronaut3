package com.example;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Singleton
@Slf4j
public class MyService {

    @Inject
    ARepository aRepository;

    @Inject
    BRepository bRepository;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    void setup() {
        log.info("Create a");
        var a = new A().setId("a").setValue("test");
        a = aRepository.save(a);

        log.info("Create b");
        var b = new B().setId("b").setValue("test");
        b = bRepository.save(b);

        log.info("Update a with b");
        Set<B> bs = new HashSet<>();
        bs.add(b);
        a.setBs(bs);
        aRepository.update(a);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    void deleteBFromA() {
        log.info("Remove b from a");
        var a = aRepository.findById("a").orElseThrow();
        a.getBs().clear();
        aRepository.update(a);

        log.info("Get B");
        var b = bRepository.findById("b").orElseThrow();
        if (b.getAs() != null && !b.getAs().isEmpty()) {
            log.warn("a's in B not emtpy!!! Remove a from b.");
            b.getAs().clear();
            bRepository.update(b);
        }
    }
}
