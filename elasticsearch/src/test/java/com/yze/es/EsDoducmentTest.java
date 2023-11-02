package com.yze.es;

import com.yze.es.dao.dto.VideoDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassDescription:
 * @Author: yze
 * @Created: 2023/11/2 9:24
 */
@SpringBootTest
@Slf4j
public class EsDoducmentTest {


    @Autowired
    private ElasticsearchTemplate restTemplate;



    /**
     * 判断索引是否存在索引
     */
    @Test
    void insert(){
        VideoDTO videoDTO = new VideoDTO();
        videoDTO.setId(1L);
        videoDTO.setTitle("小滴课堂架构大课和Spring Cloud");
        videoDTO.setCreateTime(LocalDateTime.now());
        videoDTO.setDuration(100);
        videoDTO.setCategory("后端");
        videoDTO.setDescription("这个是综合大型课程，包括了jvm，redis，新版spring boot3.x，架构，监控，性能优化，算法，高并发等多方面内容");

        VideoDTO saved = restTemplate.save(videoDTO);
        log.info("insert videoDTO: {}", videoDTO);
        log.info("insert result: {}", saved);
    }

    /**
     * 更新
     */
    @Test
    void update(){
        VideoDTO videoDTO = new VideoDTO();
        videoDTO.setId(1L);
        videoDTO.setTitle("小滴课堂架构大课和Spring Cloud V2");
        videoDTO.setCreateTime(LocalDateTime.now());
        videoDTO.setDuration(102);
        videoDTO.setCategory("后端");
        videoDTO.setDescription("这个是综合大型课程，包括了jvm，redis，新版spring boot3.x，架构，监控，性能优化，算法，高并发等多方面内容");

        VideoDTO saved = restTemplate.save(videoDTO);
        log.info("insert result: {}", saved);
    }


    /**
     * 批量插入
     */
    @Test
    void batchInsert() {
        List<VideoDTO> list = new ArrayList<>();
        list.add(new VideoDTO(2L, "老王录制的按摩课程", "主要按摩和会所推荐", 123, "后端"));
        list.add(new VideoDTO(3L, "冰冰的前端性能优化", "前端高手系列", 100042, "前端"));
        list.add(new VideoDTO(4L, "海量数据项目大课", "D哥的后端+大数据综合课程", 5432345, "后端"));
        list.add(new VideoDTO(5L, "小滴课堂永久会员", "可以看海量专题课程，IT技术持续充电平台", 6542, "后端"));
        list.add(new VideoDTO(6L, "大钊-前端低代码平台", "高效开发底层基础平台，效能平台案例", 53422, "前端"));
        list.add(new VideoDTO(7L, "自动化测试平台大课", "微服务架构下的spring cloud架构大课，包括jvm,效能平台", 6542, "后端"));


        Iterable<VideoDTO> result = restTemplate.save(list);
        log.info("insert result: {}", result);
    }


    /**
     * 根据id查询
     */
    @Test
    void  searchById(){
        VideoDTO videoDTO = restTemplate.get("2", VideoDTO.class);
        assert videoDTO != null;
        log.info("searchById: {}", videoDTO);
    }


    /**
     * 根据id删除
     */
    @Test
    void  deleteById() {
        String delete = restTemplate.delete("2", VideoDTO.class);
        log.info("delete result: {}", delete);
    }










}
