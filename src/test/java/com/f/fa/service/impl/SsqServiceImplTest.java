package com.f.fa.service.impl;

import com.f.fa.service.SsqService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class SsqServiceImplTest {

    @Autowired
    private SsqService ssqService;

    @Test
    public void testMonthInit() throws IOException {
        ssqService.init500();
    }

}