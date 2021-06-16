package com.codemonkeys.backendcoin.service;

import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface FileService {
    void storeXml(MultipartFile file,Long graphId) throws IOException, ParserConfigurationException, SAXException;

    void downLoadXml(Long graphId, HttpServletResponse response);
}
