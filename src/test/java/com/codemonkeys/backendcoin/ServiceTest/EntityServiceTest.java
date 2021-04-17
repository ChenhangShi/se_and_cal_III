package com.codemonkeys.backendcoin.ServiceTest;

import com.codemonkeys.backendcoin.BackendCoinApplication;
import com.codemonkeys.backendcoin.mapper.EntityMapper;
import com.codemonkeys.backendcoin.service.EntityService;
import com.codemonkeys.backendcoin.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BackendCoinApplication.class})
@Transactional
public class EntityServiceTest {
    @Autowired
    EntityService entityService;
    @Autowired
    EntityMapper entityMapper;
    @Autowired
    Map map;


}
