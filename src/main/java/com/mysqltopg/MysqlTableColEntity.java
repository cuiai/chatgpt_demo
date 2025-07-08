package com.mysqltopg;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Shaon
 * @version 1.0
 * @title MysqlTableColEntity
 * @description
 * @create 2024/9/2 10:30
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MysqlTableColEntity implements Serializable {

    /**
     * 表对应的库名
     */
    private String tableSchema;
    /**
     * 表名
     */
    private String tableName;
    /**
     * 列名
     */
    private String columnName;
    /**
     * 列的类型
     */
    private String dataType;
    /**
     * 列的长度
     */
    private String dataLength;
    /**
     * 类是不是主键 PRI；UNI；MUL
     */
    private String columnKey;
    /**
     * 是否可以为空 NO：不为空；YES：可以为空
     */
    private String isNullable;
    /**
     * 列默认值
     */
    private String columnDefault;
    /**
     * 列注释
     */
    private String columnComment;
    /**
     * 类扩展 是否自增（auto_increment）；时间函数（其他数据库不一定可以使用）
     */
    private String extra;

    private Integer numericPrecision;  // 新增字段
    /**
     * 数值类型的小数位数（如 DECIMAL(10,2) 的 2）
     */
    private Integer numericScale;      // 新增字段
}