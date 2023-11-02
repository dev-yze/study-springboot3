package com.yze.es;

import com.yze.es.dao.dto.VideoDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.annotations.Setting;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassDescription:
 * @Author: yze
 * @Created: 2023/11/2 9:24
 */
@SpringBootTest
@Slf4j
public class ElasticsearchTest {


    @Autowired
    private ElasticsearchTemplate restTemplate;



    /**
     * 判断索引是否存在索引
     */
    @Test
    void existsIndex() {
        IndexOperations indexOperations = restTemplate.indexOps(VideoDTO.class);
        boolean exists = indexOperations.exists();
        log.info("索引是否存在: {}", exists);
    }


    /**
     * 创建索引
     */
    @Test
    void createIndex() {
        IndexOperations indexOperations = restTemplate.indexOps(VideoDTO.class);
        boolean exists = indexOperations.exists();
        if (!exists) {
            indexOperations.create();
            // 设置映射
            restTemplate.indexOps(VideoDTO.class).putMapping();

            log.info("创建索引成功");
        } else {
            log.info("索引已经存在");
        }

    }


    /**
     * 删除索引
     */
    @Test
    void delIndex() {
        IndexOperations indexOperations = restTemplate.indexOps(VideoDTO.class);
        boolean exists = indexOperations.exists();
        if (exists) {
            boolean res = indexOperations.delete();
            log.info("索引删除结果: {}", res);
        }
    }


    /**
     * 新增文档
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



}
