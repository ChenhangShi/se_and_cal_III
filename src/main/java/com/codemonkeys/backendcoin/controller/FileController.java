package com.codemonkeys.backendcoin.controller;

import com.codemonkeys.backendcoin.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileService fileService;

    public static void setFilepath(String filepath) {
        FileController.filepath = filepath;
    }

    private static String filepath;

    @RequestMapping("/downloadXml")
    public String downloadXml(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        File file = new File(filepath);
        String fileName = "output.xml";
        if (file.exists()) {
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                System.out.println("Download the File successfully!");
            } catch (Exception e) {
                System.out.println("Download the File failed!");
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return null;

    }

    @RequestMapping("/uploadXml")
    public void uploadXml(@RequestParam(value = "file") MultipartFile file)  {
        try {
            fileService.uploadXml(file,filepath);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
