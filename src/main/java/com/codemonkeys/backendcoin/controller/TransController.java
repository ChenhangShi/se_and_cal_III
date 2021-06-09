package com.codemonkeys.backendcoin.controller;

import com.codemonkeys.backendcoin.service.TransService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trans")
public class TransController {
    TransService transService;

    /**
     * function:主要是两套表之间的转换功能
     * @param transService
     */
    @Autowired
    public TransController(TransService transService) {
        this.transService = transService;
    }
    @PostMapping("/extract")

    /**
     * @Param int actorId String graphName
     * 实现根据一个演员的actorId来从数据表中提取相关信息映射到操作表中。
     */
    public void extract(@Param("actorId") int actorId, @Param("graphName")String graphName){
        try {
            transService.extract(actorId,graphName);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     * 通过graphId来提交一格操作图到数据表中(考虑插入和更新)
     * @param graphId
     */
    @PostMapping("/submit")
    public void submit(@Param("graphId")int graphId){
        try {
            transService.submit(graphId);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     * 从movie表中提取原始数据到director_to_movie表中的接口（后续可能会增加其他表数据的提取）
     * 没事不要使用这个接口
     */
    @PostMapping("/reload")
    public void reload(){
        transService.loadIntoDirectorToMovie();
    }

}
