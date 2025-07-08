package com.mysqltopg;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Shaon
 * @version 1.0
 * @title MysqlTableIndexEntity
 * @description NON_UNIQUE, INDEX_NAME, SEQ_IN_INDEX, COLUMN_NAME
 * @create 2024/9/2 17:11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MysqlTableIndexEntity implements Serializable {

    /**
     * 是否不是唯一索引  0：唯一索引；1：不是唯一索引
     */
    private int nonUnique;
    /**
     * 索引的名称
     */
    private String indexName;
    /**
     * 索引的序号，从 1 开始，如果存在多个递增
     */
    private int seqInIndex;
    /**
     * 组成索引的列名
     */
    private String columnName;
}