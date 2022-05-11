package com.boning.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class AccountMapperTest {
    @Autowired
    private AccountMapper accountMapper;

    @Test
    void Test() {
    accountMapper.selectList(null).forEach(System.out::println);
    }
}