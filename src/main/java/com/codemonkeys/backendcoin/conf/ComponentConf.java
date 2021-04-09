package com.codemonkeys.backendcoin.conf;


import com.codemonkeys.backendcoin.service.FileService;
import com.codemonkeys.backendcoin.serviceImpl.FileServiceImpl;
import com.codemonkeys.backendcoin.util.XmlUtil;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {XmlUtil.class, FileService.class, FileServiceImpl.class})
public class ComponentConf {
}
