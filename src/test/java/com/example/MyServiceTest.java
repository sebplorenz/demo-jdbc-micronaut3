package com.example;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class MyServiceTest {

    @Inject
    MyService myService;

    @Inject
    ARepository aRepository;

    @Inject
    BRepository bRepository;

    @Test
    void test() {
        myService.setup();

        var as = aRepository.findAll();
        assertEquals(1, as.size());

        var bs = bRepository.findAll();
        assertEquals(1, bs.size());

        var a = as.get(0);
        assertEquals(1, a.getBs().size());
        assertEquals("b", a.getBs().iterator().next().getId());

        myService.deleteBFromA();

        as = aRepository.findAll();
        assertEquals(1, as.size());
        a = as.get(0);
        assertEquals(0, a.getBs().size());
    }

}
