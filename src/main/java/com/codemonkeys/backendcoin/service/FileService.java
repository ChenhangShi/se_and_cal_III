package com.codemonkeys.backendcoin.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    void uploadXml(MultipartFile file,String path) throws IOException;
}
