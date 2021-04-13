package com.codemonkeys.backendcoin.serviceImpl;

import com.codemonkeys.backendcoin.VO.EntityVO;
import com.codemonkeys.backendcoin.VO.LinkVO;
import com.codemonkeys.backendcoin.VO.RelationGroupVO;
import com.codemonkeys.backendcoin.mapper.EntityMapper;
import com.codemonkeys.backendcoin.mapper.LinkMapper;
import com.codemonkeys.backendcoin.service.FileService;
import com.codemonkeys.backendcoin.util.FileUtil;
import com.codemonkeys.backendcoin.util.Map;
import com.codemonkeys.backendcoin.util.XmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class FileServiceImpl implements FileService {
    FileUtil fileUtil;
    XmlUtil xmlUtil;
    EntityMapper entityMapper;
    LinkMapper linkMapper;
    Map map;

    public FileServiceImpl(){

    }

    @Autowired
    public FileServiceImpl(FileUtil fileUtil, XmlUtil xmlUtil, EntityMapper entityMapper, LinkMapper linkMapper,Map map) {
        this.fileUtil = fileUtil;
        this.xmlUtil = xmlUtil;
        this.entityMapper = entityMapper;
        this.linkMapper = linkMapper;
        this.map=map;
    }


    @Override
    public void uploadXml(MultipartFile file,String path)  {
        try {
            fileUtil.multipartFileToOutPutStream(file,path);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void storeXml(MultipartFile file) throws IOException, ParserConfigurationException, SAXException {
        InputStream is=file.getInputStream();
        File f=null;
        f=File.createTempFile("tmp", ".xml", new File("D:/"));

        fileUtil.multipartFileToOutputStream(file,f);
        List<RelationGroupVO> relationGroupVOList=xmlUtil.resolveXml(f.getAbsolutePath());

        f.deleteOnExit();

        Set<LinkVO> linkVOSet=new HashSet<>();
        Set<EntityVO> entityVOSet=new HashSet<>();

        for(RelationGroupVO r:relationGroupVOList){
            linkVOSet.add(new LinkVO(r));
            entityVOSet.add(r.getTarget());
            entityVOSet.add(r.getSource());
        }

        for(LinkVO linkVO:linkVOSet){
            linkMapper.insertLink(map.from(linkVO));
        }

        for(EntityVO entityVO:entityVOSet){
            entityMapper.insertEntity(map.from(entityVO));
        }



    }

}
