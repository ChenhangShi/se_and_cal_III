package com.codemonkeys.backendcoin.MapperTest;

import com.codemonkeys.backendcoin.mapper.ActorMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ActorMapperTest {
    @Autowired
    ActorMapper actorMapper;

}
