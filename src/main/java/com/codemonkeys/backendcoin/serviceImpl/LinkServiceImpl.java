package com.codemonkeys.backendcoin.serviceImpl;

import com.codemonkeys.backendcoin.PO.LinkPO;
import com.codemonkeys.backendcoin.VO.LinkVO;
import com.codemonkeys.backendcoin.mapper.LinkMapper;
import com.codemonkeys.backendcoin.service.LinkService;
import com.codemonkeys.backendcoin.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LinkServiceImpl implements LinkService {
    LinkMapper linkMapper;
    Map map;

    @Autowired
    public LinkServiceImpl(LinkMapper linkMapper, Map map) {
        this.linkMapper = linkMapper;
        this.map = map;
    }

    @Override
    public List<LinkVO> getAllLinks(Long graphId) {
        List<LinkPO> linkPOList=linkMapper.getAllLink(graphId);
        List<LinkVO> linkVOS=new ArrayList<>();
        for(LinkPO linkPO:linkPOList){
            linkVOS.add(map.from(linkPO));
        }
        return linkVOS;
    }

    @Override
    public void addLink(List<LinkVO> linkVOList) {
        for(LinkVO linkVO:linkVOList){
            linkMapper.insertLink(map.from(linkVO));
        }
    }

    @Override
    public void updateLink(List<LinkVO> linkVOList) {
        for(LinkVO linkVO:linkVOList){
            linkMapper.updateLink(map.from(linkVO));
        }
    }
}
