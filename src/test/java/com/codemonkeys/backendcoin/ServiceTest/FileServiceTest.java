package com.codemonkeys.backendcoin.ServiceTest;


import com.codemonkeys.backendcoin.BackendCoinApplication;
import com.codemonkeys.backendcoin.service.FileService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BackendCoinApplication.class})
@Transactional
public class FileServiceTest {
    @Autowired
    FileService fileService;

    @Test
    public void testStoreXml() throws IOException, ParserConfigurationException, SAXException {
        System.out.print(fileService==null);
        String localFilePath="output.xml";
        File file=new File(localFilePath);
        FileInputStream fileInputStream;
        fileInputStream=new FileInputStream(file);

        MultipartFile multipartFile=new MockMultipartFile(file.getName(),file.getName(),"application/octet-stream",fileInputStream);
        fileService.storeXml(multipartFile,Long.MAX_VALUE);

    }
}
