package com.yze.es.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.time.LocalDateTime;

/**
 * @ClassDescription:
 * @Author: yze
 * @Created: 2023/11/2 9:10
 */
@Document(indexName = "video")
@Setting(shards = 2, replicas = 0)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoDTO {

    @Id
    @Field(type = FieldType.Text, index = false)
    private Long id;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Keyword)
    private String category;

    @Field(type = FieldType.Integer)
    private Integer duration;

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime createTime;

    public VideoDTO(Long id, String title, String description, Integer duration, String category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.duration = duration;
    }
}
