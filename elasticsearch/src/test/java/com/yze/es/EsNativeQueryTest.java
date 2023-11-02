package com.yze.es;

import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.aggregations.Buckets;
import co.elastic.clients.elasticsearch._types.aggregations.StringTermsBucket;
import com.yze.es.dao.dto.VideoDTO;
import com.yze.es.utils.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchAggregation;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchAggregations;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.AggregationsContainer;
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
public class EsNativeQueryTest {


    @Autowired
    private ElasticsearchTemplate restTemplate;


    /**
     * 根据id查询
     */
    @Test
    void  nativeQuery(){
        Query query = NativeQuery.builder()
                .withAggregation("category_group",
                        Aggregation.of(agg -> agg.terms(ta -> ta.field("category").size(2)))
                ).build();
        SearchHits<VideoDTO> result = restTemplate.search(query, VideoDTO.class);
        ElasticsearchAggregations aggregations = (ElasticsearchAggregations) result.getAggregations();

        ElasticsearchAggregation category_group = aggregations.get("category_group");
        Buckets<StringTermsBucket> buckets = category_group.aggregation().getAggregate().sterms().buckets();
        buckets.array().forEach(item -> {
            log.info("{}", item);
        });

//        log.info(JacksonUtil.parseJSONString(result));
//        result.getSearchHits().forEach(item -> log.info("{}", item));

    }










   





}
