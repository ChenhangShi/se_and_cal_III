package com.codemonkeys.backendcoin.util;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional  // 需要再次生成时删掉这行
public class ProcessDataTest {
    @Autowired
    ProcessData processData;

    @Test
    public void testGenerateDirectorToMovie() throws Exception{
        // 运行时间太长
        // processData.generateDirectorToMovie();
    }
}
