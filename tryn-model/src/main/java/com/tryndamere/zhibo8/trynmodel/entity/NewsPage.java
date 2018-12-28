package com.tryndamere.zhibo8.trynmodel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * Created by Dave on 2018/12/26
 * Describes
 */
@Data
@Document(indexName = "zhibo8-news",type ="news",replicas = 0)
public class NewsPage {
    @Id
    String id;
    @Field
    private String url;
    @Field
    private String title;
    @Field( type = FieldType.Date,
            format = DateFormat.custom,pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date releaseTime;
}
