package com.codemonkeys.backendcoin.serviceImpl;

import com.codemonkeys.backendcoin.PO.EntityPO;
import com.codemonkeys.backendcoin.PO.LinkPO;
import com.codemonkeys.backendcoin.VO.EntityVO;
import com.codemonkeys.backendcoin.VO.LinkVO;
import com.codemonkeys.backendcoin.VO.RelationGroupVO;
import com.codemonkeys.backendcoin.VO.RelationVO;
import com.codemonkeys.backendcoin.mapper.EntityMapper;
import com.codemonkeys.backendcoin.mapper.LinkMapper;
import com.codemonkeys.backendcoin.service.FileService;
import com.codemonkeys.backendcoin.util.FileUtil;
import com.codemonkeys.backendcoin.util.Map;
import com.codemonkeys.backendcoin.util.XmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@Transactional
public class FileServiceImpl implements FileService {
    FileUtil fileUtil;
    XmlUtil xmlUtil;
    EntityMapper entityMapper;
    LinkMapper linkMapper;
    Map map;

    @Autowired
    public FileServiceImpl(FileUtil fileUtil, XmlUtil xmlUtil, EntityMapper entityMapper, LinkMapper linkMapper,Map map) {
        this.fileUtil = fileUtil;
        this.xmlUtil = xmlUtil;
        this.entityMapper = entityMapper;
        this.linkMapper = linkMapper;
        this.map=map;
    }

    public void storeXml(MultipartFile file,Long graphId) throws IOException, ParserConfigurationException, SAXException {
        InputStream is=file.getInputStream();
        File f=null;
        f=File.createTempFile("tmp", ".xml");
        System.out.println(f.getAbsolutePath());

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
            entityVO.setGraphId(graphId);
            entityMapper.insertEntity(map.from(entityVO));
        }
    }

    @Override
    public void downLoadXml(Long graphId, HttpServletResponse response)  {
        List<LinkPO> linkPOList=linkMapper.getAllLink(graphId);
        List<EntityPO> entityPOList=entityMapper.getAllEntity(graphId);
        List<RelationGroupVO> relationGroupVOList=new ArrayList<>();

        for(LinkPO linkPO:linkPOList){
            Long sourceId=linkPO.sourceId;
            Long targetId=linkPO.targetId;
            Long relationId=linkPO.id;

            EntityVO sourceEntityVO=map.from(entityMapper.getEntity(graphId,sourceId));
            EntityVO targetEntityVO=map.from(entityMapper.getEntity(graphId,targetId));
            RelationVO relationVO=new RelationVO(relationId,linkPO.type,linkPO.relationName,linkPO.description,linkPO.isFullLine);
            RelationGroupVO relationGroupVO=new RelationGroupVO(sourceEntityVO,targetEntityVO,relationVO);
            relationGroupVOList.add(relationGroupVO);
        }

        File file=null;
        try{
            file=File.createTempFile("tmp", ".xml");
            xmlUtil.beanToXml(relationGroupVOList,file);
            String fileName=file.getAbsolutePath();

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
        } catch (Exception e) {
            System.out.println("Download the File failed!");
        }
        finally {
            file.deleteOnExit();
        }
    }

}
