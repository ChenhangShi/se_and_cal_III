package com.codemonkeys.backendcoin.service;

import com.codemonkeys.backendcoin.VO.LinkVO;

import java.util.List;

public interface LinkService {
    void addLink(List<LinkVO> linkVOList);
    void updateLink(List<LinkVO> linkVOList);
}
