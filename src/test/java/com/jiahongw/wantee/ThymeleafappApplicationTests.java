package com.jiahongw.wantee;

import com.jiahongw.wantee.entity.Country;
import com.jiahongw.wantee.entity.CountryDO;
import com.jiahongw.wantee.mapper.CountryDOMapper;
import com.jiahongw.wantee.mapper.CountryMapper;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.junit.Test;
import org.springframework.context.annotation.Profile;

/**
 * @author VictorHong
 * @date 2023/2/25
 */

public class ThymeleafappApplicationTests extends BaseTest {

    /**
     * Spring Boot 默认已经配置好了数据源，程序员可以直接 DI 注入然后使用即可 数据源获取正常，能正常拿到数据库连接，则说明数据库连接成功
     */
    @Resource
    DataSource dataSource;


    @Resource
    private CountryMapper countryMapper;

    @Resource
    private CountryDOMapper countryDOMapper;

    @Test
    public void contextLoads() throws SQLException {
        System.out.println("数据源>>>>>>" + dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println("连接>>>>>>>>>" + connection);
        System.out.println("连接地址>>>>>" + connection.getMetaData().getURL());
        connection.close();
    }



    @Test
    public void testInsert() throws Exception {
        List<Country> r = countryMapper.getAll();
        System.out.println(r);
    }


    @Test
    public void testSelectByPrimaryKey() throws Exception {
        CountryDO r = countryDOMapper.selectByPrimaryKey(1);
        System.out.println(r);
    }

}