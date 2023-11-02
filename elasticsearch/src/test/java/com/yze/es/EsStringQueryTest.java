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
import org.springframework.data.elasticsearch.core.query.StringQuery;

/**
 * @ClassDescription:
 * @Author: yze
 * @Created: 2023/11/2 9:24
 */
@SpringBootTest
@Slf4j
public class EsStringQueryTest {


    @Autowired
    private ElasticsearchTemplate restTemplate;


    /**
     * 根据id查询
     */
    @Test
    void  stirngQuery(){
        String condition = """
                    {"bool":{"must":[{"match":{"title":"架构"}},{"match":{"description":"spring"}}]}}
                """;
        Query query = new StringQuery(condition);
        SearchHits<VideoDTO> result = restTemplate.search(query, VideoDTO.class);
        log.info(JacksonUtil.parseJSONString(result));
        result.getSearchHits().forEach(item -> log.info("{}", item));

    }










   





}
