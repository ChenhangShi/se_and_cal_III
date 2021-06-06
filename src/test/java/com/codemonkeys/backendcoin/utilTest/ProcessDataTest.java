package com.codemonkeys.backendcoin.utilTest;

import com.codemonkeys.backendcoin.util.ProcessData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProcessDataTest {
    @Autowired
    ProcessData processData;

    @Test
    public void testGenerateDirectorToMovie() throws Exception{
        processData.generateDirectorToMovie();
    }
}
