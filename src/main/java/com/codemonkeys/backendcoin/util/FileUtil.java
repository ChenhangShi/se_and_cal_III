package com.codemonkeys.backendcoin.util;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class FileUtil {
    public void multipartFileToOutPutStream(MultipartFile file,String path) throws IOException {
        if(file.equals("")||file.getSize()<=0){
            return;
        }
        else{
            InputStream is=null;
            is=file.getInputStream();
            File localFile=new File(path);

            FileOutputStream fos=new FileOutputStream(localFile);
//            OutputStreamWriter osw=new OutputStreamWriter(fos,"UTF-8");
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
