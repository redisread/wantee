package com.jiahongw.wantee;

import com.google.common.collect.Sets;
import com.jiahongw.wantee.entity.Note;
import com.jiahongw.wantee.mapper.NoteMapperExt;
import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.mybatisflex.codegen.dialect.JdbcTypeMapping;
import com.mybatisflex.core.MybatisFlexBootstrap;
import com.zaxxer.hikari.HikariDataSource;
import java.math.BigInteger;

/**
 * @author VictorHong
 * @date 2023/5/14
 */
public class DataSourceTest {


    /**
     * 生成代码的函数，在测试开发的时候使用，需要执行
     *
     *
     * @param args
     */
    public static void main(String[] args) {

        //配置数据源
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(
            "jdbc:mysql://114.116.242.58:3306/wattee_test?characterEncoding=UTF-8&serverTimezone=UTC");
        dataSource.setUsername("victor");
        dataSource.setPassword("19981022");
        //创建配置内容
        GlobalConfig globalConfig = new GlobalConfig();
        //是否生成 mapper 类，默认为 false
        globalConfig.setMapperGenerateEnable(false);
        globalConfig.addGenerateTable("user","tag","note");
//        globalConfig.setUnGenerateTables(Sets.newHashSet("databasechangelog","databasechangeloglock"));
        globalConfig.setEntityPackage("com.jiahongw.wantee.entity");
        globalConfig.setMapperPackage("com.jiahongw.wantee.mapper");
        JdbcTypeMapping.registerMapping(BigInteger.class, Long.class);
        Generator generator = new Generator(dataSource, globalConfig);
        //生成代码
        generator.generate();

    }
}
