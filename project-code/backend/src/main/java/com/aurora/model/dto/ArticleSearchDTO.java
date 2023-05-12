package com.aurora.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

//简单的说就是下面这几个新的注解都是用于搜索功能的，搜索引擎是elasticseach，需要指定索引、字段等等
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "article")    //Spring Data Elasticsearch 框架提供的注解，用于标识一个 Java 对象对应到 Elasticsearch 中的一个索引
                                        //indexName 属性指定了索引的名称，这里的值为 article，即指定了该对象对应到 Elasticsearch 中名为 article 的索引。
public class ArticleSearchDTO {

    @Id // Spring Data Elasticsearch 框架提供的注解，用于标识 Java 对象中哪个字段对应到 Elasticsearch 中的 _id 字段
    private Integer id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word") // Spring Data Elasticsearch 框架提供的注解，用于标识 Java 对象中的一个字段对应到 Elasticsearch 中的一个字段
                                                            // type 属性指定了字段类型，这里的值为 FieldType.Text，即指定了该字段类型为文本类型
                                                            // analyzer 属性指定了使用的分词器，这里的值为 "ik_max_word"，即使用 ik_max_word 分词器对该字段进行分词
    private String articleTitle;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String articleContent;

    @Field(type = FieldType.Integer)
    private Integer isDelete;

    @Field(type = FieldType.Integer)
    private Integer status;

}
