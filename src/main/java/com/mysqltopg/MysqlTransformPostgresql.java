package com.mysqltopg;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Shaon
 * @version 1.0
 * @title MysqlTrasformPostgresql
 * @description
 * @create 2024/9/2 9:30
 */
public class MysqlTransformPostgresql {

    private static final String MYSQL_URL = "jdbc:mysql://192.168.103.128:3306/ictprod?autoReconnect=true&failOverReadOnly=false&seUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=true";
    private static final String MYSQL_USER = "icttest";
    private static final String MYSQL_PASSWORD = "Icttest123$56";

    /**
     * 需要迁移的mysql数据库
     */
    private static final List<String> databaseList = Arrays.asList(
            "ictprod"
    );

    private static final Map<String, String> typeMapping = Maps.newHashMap();

    static {
        // 初始化映射表
        typeMapping.put("tinyint", "smallint");
        typeMapping.put("smallint", "smallint");
        typeMapping.put("mediumint", "integer");
        typeMapping.put("int", "integer");
        typeMapping.put("integer", "integer");
        typeMapping.put("bigint", "bigint");
        typeMapping.put("decimal", "numeric");
        typeMapping.put("numeric", "numeric");
        typeMapping.put("float", "real");
        typeMapping.put("double", "double precision");
        typeMapping.put("real", "double precision");
        typeMapping.put("date", "date");
        typeMapping.put("time", "time without time zone");
        typeMapping.put("datetime", "timestamp without time zone");
        typeMapping.put("timestamp", "timestamp without time zone");
        typeMapping.put("year", "smallint");
        typeMapping.put("char", "character");
        typeMapping.put("varchar", "varchar");
        typeMapping.put("tinytext", "text");
        typeMapping.put("text", "text");
        typeMapping.put("mediumtext", "text");
        typeMapping.put("longtext", "text");
        typeMapping.put("blob", "bytea");
        typeMapping.put("enum", "varchar"); // PostgreSQL 没有 ENUM 类型，可以使用 VARCHAR 替代
        typeMapping.put("set", "varchar"); // PostgreSQL 没有 SET 类型，可以使用 VARCHAR 替代
        typeMapping.put("json", "jsonb");
        typeMapping.put("binary", "bytea");
        typeMapping.put("varbinary", "bytea");
        typeMapping.put("bit", "boolean");
        typeMapping.put("bool", "boolean");
        typeMapping.put("boolean", "boolean");
        typeMapping.put("point", "point");
        typeMapping.put("linestring", "line");
        typeMapping.put("polygon", "polygon");
        typeMapping.put("geometry", "geometry");
        typeMapping.put("geography", "geography");
        typeMapping.put("inet", "inet");
        typeMapping.put("cidr", "cidr");
        typeMapping.put("macaddr", "macaddr");
        typeMapping.put("uuid", "uuid");
        typeMapping.put("hstore", "hstore");
    }

    private static final String sql = "select TABLE_SCHEMA,\n" +
            "       TABLE_NAME,\n" +
            "       COLUMN_NAME,\n" +
            "       COLUMN_DEFAULT,\n" +
            "       IS_NULLABLE,\n" +
            "       DATA_TYPE,\n" +
            "       NUMERIC_PRECISION,\n" +
            "       NUMERIC_SCALE,\n" +
            "       CHARACTER_MAXIMUM_LENGTH,\n" +
            "       COLUMN_KEY,\n" +
            "       EXTRA,\n" +
            "       COLUMN_COMMENT\n" +
            "from INFORMATION_SCHEMA.COLUMNS\n" +
            "where TABLE_SCHEMA = ? AND TABLE_NAME IN (\n" +
            "    SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES \n" +
            "    WHERE TABLE_SCHEMA = ? AND TABLE_TYPE = 'BASE TABLE'\n" +
            ")";

    private static final String indexSql = "select NON_UNIQUE,INDEX_NAME,SEQ_IN_INDEX,COLUMN_NAME\n" +
            "from information_schema.STATISTICS\n" +
            "where TABLE_SCHEMA = ? and TABLE_NAME = ? and TABLE_NAME IN (\n" +
            "    SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES \n" +
            "    WHERE TABLE_SCHEMA = ? AND TABLE_TYPE = 'BASE TABLE'\n" +
            ");";

    /**
     * 存储文件的路径前缀
     */
    private static final String filePathPrefix = "G:\\mysql_jiaoben\\";

    public static void mysqlDDL2File() {
        try (Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USER, MYSQL_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (String dbName : databaseList) {
                Path path = Paths.get(filePathPrefix + dbName + ".sql");

                StringJoiner joiner = new StringJoiner("\n");

                List<MysqlTableColEntity> tableColEntities = Lists.newArrayList();
                statement.setString(1, dbName);
                statement.setString(2, dbName);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String tableSchema = resultSet.getString("TABLE_SCHEMA");
                    String tableName = resultSet.getString("TABLE_NAME");
                    String columnName = resultSet.getString("COLUMN_NAME");
                    String columnDefault = resultSet.getString("COLUMN_DEFAULT");
                    String isNullable = resultSet.getString("IS_NULLABLE");
                    String dataType = resultSet.getString("DATA_TYPE");
                    Integer numericPrecision = null;
                    if (resultSet.getObject("NUMERIC_PRECISION")!=null){
                        String numeric_precision = resultSet.getObject("NUMERIC_PRECISION").toString();
                        numericPrecision = Integer.parseInt(numeric_precision);
                    }
                    Integer numericScale = null;
                    if (resultSet.getObject("NUMERIC_SCALE")!=null){
                        String numeric_scale = resultSet.getObject("NUMERIC_SCALE").toString();
                        numericScale = Integer.parseInt(numeric_scale);
                    }
                    String dataLength = resultSet.getString("CHARACTER_MAXIMUM_LENGTH");
                    String columnKey = resultSet.getString("COLUMN_KEY");
                    String extra = resultSet.getString("EXTRA");
                    String columnComment = resultSet.getString("COLUMN_COMMENT");
                    MysqlTableColEntity build = MysqlTableColEntity.builder()
                            .tableSchema(tableSchema)
                            .tableName(tableName)
                            .columnName(columnName)
                            .columnDefault(columnDefault)
                            .isNullable(isNullable)
                            .dataType(dataType)
                            .numericPrecision(numericPrecision)
                            .numericScale(numericScale)
                            .dataLength(dataLength)
                            .columnKey(columnKey)
                            .extra(extra)
                            .columnComment(columnComment)
                            .build();
                    tableColEntities.add(build);
                }

                Map<String, List<MysqlTableColEntity>> groupByTableName = groupByTableName(tableColEntities);

                for (String tableName : groupByTableName.keySet()) {
                    if (tableName.matches("^\\d.*")|| tableName.startsWith("test") || tableName.startsWith("temp")) {
                        System.out.println("Skipping table with numeric prefix: " + tableName);
                        continue;
                    }
                    String tableSql = buildTableSql(connection, dbName, tableName, groupByTableName.get(tableName));
                    joiner.add(tableSql);
                }
                System.out.println(joiner);
                writeSqlFile(path, joiner.toString());
            }

            // 生成建库脚本
            createDatabaseSql(databaseList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createDatabaseSql(List<String> databaseList) throws IOException {
        Path path = Paths.get(filePathPrefix + "pg_create_database.sql");
        StringJoiner joiner = new StringJoiner("\n");
        for (String dbName : databaseList) {
            joiner.add("create database " + dbName + ";");
            joiner.add("\n");
        }
        writeSqlFile(path, joiner.toString());
    }

    /**
     * 将内容写到文件中
     *
     * @param path
     * @param context
     * @throws IOException
     */
    private static void writeSqlFile(Path path, String context) throws IOException {
        Files.write(path, context.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    private static Map<String, List<MysqlTableColEntity>> groupByTableName(List<MysqlTableColEntity> tableColEntities) {
        return tableColEntities.stream()
                .collect(
                        Collectors.groupingBy(MysqlTableColEntity::getTableName)
                );
    }

    private static String buildTableSql(Connection connection, String dbName, String tableName, List<MysqlTableColEntity> tableColEntities) {

        String colComment = "comment on column " + tableName + ".%s is '%s';";

        StringJoiner colJoin = new StringJoiner(",\n");

        StringJoiner commentJoin = new StringJoiner("\n");

        List<MysqlTableIndexEntity> mysqlIndexList = getMysqlIndexList(connection, dbName, tableName);

        for (MysqlTableColEntity tableColEntity : tableColEntities) {
            StringJoiner col = new StringJoiner(" ");

            String columnName = tableColEntity.getColumnName();
            String dataType = tableColEntity.getDataType();
            String isNullable = tableColEntity.getIsNullable();
            String dataLength = tableColEntity.getDataLength();
            Integer numericPrecision = tableColEntity.getNumericPrecision();
            Integer numericScale = tableColEntity.getNumericScale();
            String columnKey = tableColEntity.getColumnKey();
            String columnDefault = tableColEntity.getColumnDefault();
            String columnComment = tableColEntity.getColumnComment();
            String extra = tableColEntity.getExtra();

            String pgType = typeMapping.getOrDefault(dataType.toLowerCase(), dataType);

            // 处理带精度和小数位的数值类型
            if (("decimal".equals(dataType) || "numeric".equals(dataType))
                    && numericPrecision != null && numericScale != null) {
                pgType += "(" + numericPrecision + "," + numericScale + ")";
            }
            // 处理不带精度和小数位的数值类型
            else if (("decimal".equals(dataType) || "numeric".equals(dataType))
                    && numericPrecision != null && numericScale == null) {
                pgType += "(" + numericPrecision + ")";
            }
            // 处理字符类型
            else if (StringUtils.isNotBlank(dataLength)
                    && !dataType.contains("text")
                    && !"blob".equals(dataType)
                    && !"binary".equals(dataType)
                    && !"varbinary".equals(dataType)) {
                pgType += "(" + dataLength + ")";
            }
            // 处理自增主键
            if ("PRI".equals(columnKey)
                    && "auto_increment".equals(extra)) {
                pgType = "bigserial";
            }

            col.add("\t");
            col.add("\"" + columnName + "\"");
            col.add(pgType);

            if (StringUtils.isNotBlank(columnDefault)
                    && !columnDefault.contains("CURRENT_TIMESTAMP")) {
                if ("bit".equals(dataType)) {
                    if (columnDefault.contains("0")) {
                        columnDefault = "false";
                    } else {
                        columnDefault = "true";
                    }
                }
                col.add("default " + "'" + columnDefault + "'");
            }

            if ("NO".equals(isNullable)) {
                col.add("not null");
            }
            colJoin.add(col.toString());

            String format = String.format(colComment, "\"" + columnName + "\"", columnComment);
            commentJoin.add(format);
        }

        if (StringUtils.isNotBlank(buildPkIndex(mysqlIndexList, tableName))) {
            String constraint = "\tconstraint " + tableName + "_pk" + "\n";
            String pk = "\t\tprimary key" + " (" + buildPkIndex(mysqlIndexList, tableName) + ")";
            colJoin.add(constraint + pk);
        }

        StringBuilder builder = new StringBuilder();
        builder.append("create table if not exists " + tableName + "\n")
                .append("(\n")
                .append(colJoin)
                .append("\n")
                .append(");\n")
                .append(commentJoin)
                .append("\n")
                .append("alter table " + tableName + " owner to postgres;")
                .append("\n");
        String indexSql = buildCreateIndexSql(tableName, mysqlIndexList);
        // 处理 非主键索引
        if (StringUtils.isNotBlank(indexSql)) {
            builder.append(indexSql).append("\n");
        }
        return builder.toString();
    }

    private static String buildCreateIndexSql(String tableName, List<MysqlTableIndexEntity> mysqlIndexList) {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.setEmptyValue("");

        // 修改索引名称格式：表名_索引名
        String indexSql = "create %s if not exists %s\n" +
                "\ton " + tableName + " (%s);";

        Map<String, List<MysqlTableIndexEntity>> indexMapping = mysqlIndexList.stream()
                .filter(item -> !"PRIMARY".equals(item.getIndexName())) // 排除主键索引
                .sorted(Comparator.comparingInt(MysqlTableIndexEntity::getSeqInIndex))
                .collect(Collectors.groupingBy(MysqlTableIndexEntity::getIndexName));

        for (String indexName : indexMapping.keySet()) {
            List<MysqlTableIndexEntity> mysqlTableIndexEntities = indexMapping.get(indexName);
            int nonUnique = mysqlTableIndexEntities.get(0).getNonUnique();
            String indexType = "index";
            if (nonUnique == 0) {
                indexType = "unique index";
            }

            // 修改索引名称：表名_原索引名
            String newIndexName = tableName + "_" + indexName;

            List<String> colNameList = mysqlTableIndexEntities.stream()
                    .map(item -> "\"" + item.getColumnName() + "\"")
                    .collect(Collectors.toList());

            if (CollectionUtils.isEmpty(colNameList)) {
                continue;
            }

            String colName = Joiner.on(", ").join(colNameList);
            joiner.add(String.format(indexSql, indexType, newIndexName, colName));
        }
        return joiner.toString();
    }

    private static String buildPkIndex(List<MysqlTableIndexEntity> indexEntityList, String tableName) {
        List<MysqlTableIndexEntity> primary = indexEntityList.stream()
                .filter(item -> "PRIMARY".equals(item.getIndexName()))
                .collect(Collectors.toList());

        List<String> colNameList = primary.stream()
                .map(item -> "\"" + item.getColumnName() + "\"")
                .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(colNameList)) {
            return null;
        }

        // 修改主键约束名称：表名_pk
        return Joiner.on(", ").join(colNameList);
        // 如果要在约束名称中也加上表名，可以改成：
        // return "constraint " + tableName + "_pk primary key (" + Joiner.on(", ").join(colNameList) + ")";
    }

    private static List<MysqlTableIndexEntity> getMysqlIndexList(Connection connection, String dbName, String tableName) {
        List<MysqlTableIndexEntity> list = Lists.newArrayList();
        try (PreparedStatement statement = connection.prepareStatement(indexSql)) {
            statement.setString(1, dbName);
            statement.setString(2, tableName);
            statement.setString(2, dbName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // NON_UNIQUE, INDEX_NAME, SEQ_IN_INDEX, COLUMN_NAME
                int nonUnique = resultSet.getInt("NON_UNIQUE");
                String indexName = resultSet.getString("INDEX_NAME");
                int seqInIndex = resultSet.getInt("SEQ_IN_INDEX");
                String columnName = resultSet.getString("COLUMN_NAME");
                MysqlTableIndexEntity build = MysqlTableIndexEntity.builder()
                        .nonUnique(nonUnique)
                        .indexName(indexName)
                        .seqInIndex(seqInIndex)
                        .columnName(columnName)
                        .build();
                list.add(build);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        mysqlDDL2File();
    }
}