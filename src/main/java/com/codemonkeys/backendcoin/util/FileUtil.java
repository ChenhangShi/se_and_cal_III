package com.codemonkeys.backendcoin.util;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Component
public class FileUtil {
    /**
     * 将
     * @param file
     * @param f
     * @throws IOException
     */
    public void multipartFileToOutputStream(MultipartFile file,File f) throws IOException {
        if(file==null||file.getSize()<=0){
            return;
        }
        else{
            InputStream is=null;
            is=file.getInputStream();
            File localFile=f;
            FileOutputStream fos=new FileOutputStream(localFile);
            InputStreamToOutputStream(is,fos);
            is.close();
            fos.close();
    }
    }

    public void InputStreamToOutputStream(InputStream is,FileOutputStream fos){
        try {
            int byteRead=0;
            byte[] buffer=new byte[8192];
            while((byteRead=is.read(buffer,0,8192))!=-1){
                fos.write(buffer,0,byteRead);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
