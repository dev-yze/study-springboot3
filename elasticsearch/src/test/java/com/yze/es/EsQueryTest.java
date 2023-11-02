package com.yze.es;

import com.yze.es.dao.dto.VideoDTO;
import com.yze.es.utils.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;

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
public class EsQueryTest {


    @Autowired
    private ElasticsearchTemplate restTemplate;


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


    /**
     * 查询所有
     */
    @Test
    void findAll(){

        SearchHits<VideoDTO> videoHits = restTemplate.search(Query.findAll(), VideoDTO.class);
        /**
         * totalHits,totalHitsRelation,maxScore,scrollId,searchHits,aggregations,suggest,pointInTimeId,empty
         */
        log.info("{}", JacksonUtil.parseJSONString(videoHits));
        videoHits.getSearchHits().forEach(item -> log.info("{}", item));

    }

    /**
     * 匹配查询
     */
    @Test
    void matchQuery() {
        Query query = NativeQuery.builder()
                .withQuery(q -> q.match(m -> m.field("description").query("spring"))).build();
        SearchHits<VideoDTO> result = restTemplate.search(query, VideoDTO.class);
        log.info("{}", JacksonUtil.parseJSONString(result));
        result.getSearchHits().forEach(item -> log.info("{}", item));

    }


    /**
     * 分页查询
     */
    @Test
    void pageQuery() {
        Query query = NativeQuery.builder()
                .withQuery(Query.findAll())
                .withPageable(Pageable.ofSize(4).withPage(0))
                .build();
        SearchHits<VideoDTO> result = restTemplate.search(query, VideoDTO.class);
        log.info("{}", JacksonUtil.parseJSONString(result));
        result.getSearchHits().forEach(item -> log.info("{}", item));
    }


    @Test
    void sortQuery() {
        Query query = NativeQuery.builder()
                .withQuery(Query.findAll())
                .withPageable(Pageable.ofSize(4).withPage(0))
                .withSort(Sort.by("duration").ascending())
                .build();
        SearchHits<VideoDTO> result = restTemplate.search(query, VideoDTO.class);
        log.info("{}", JacksonUtil.parseJSONString(result));
        result.getSearchHits().forEach(item -> log.info("{}", item));
    }











   





}
