package com.codemonkeys.backendcoin.serviceImpl;

import com.codemonkeys.backendcoin.service.FileService;
import com.codemonkeys.backendcoin.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
public class FileServiceImpl implements FileService {
    @Autowired
    FileUtil fileUtil;
    @Override
    public void uploadXml(MultipartFile file,String path)  {
        try {
            fileUtil.multipartFileToOutPutStream(file,path);
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

}
