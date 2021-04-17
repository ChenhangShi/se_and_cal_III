package com.codemonkeys.backendcoin.controller;

import com.codemonkeys.backendcoin.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;


@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileService fileService;

    @PostMapping("/storeXml")
    public void storeXml(@RequestParam(value="file")MultipartFile file){
        try{
            fileService.storeXml(file);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/downloadXmlByGraph/{graphId}")
    public void downloadXmlByGraph(@PathVariable("graphId") Long graphId,HttpServletResponse response){
        fileService.downLoadXml(graphId,response);
    }
}
