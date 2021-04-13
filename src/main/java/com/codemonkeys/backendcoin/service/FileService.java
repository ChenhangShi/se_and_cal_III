package com.codemonkeys.backendcoin.service;

import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface FileService {
    void uploadXml(MultipartFile file,String path) throws IOException;

    void storeXml(MultipartFile file) throws IOException, ParserConfigurationException, SAXException;
}
