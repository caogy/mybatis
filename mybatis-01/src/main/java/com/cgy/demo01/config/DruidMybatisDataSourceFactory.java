package com.cgy.demo01.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 *在 mybatis 中使用 druid 数据库连接池
 */
public class DruidMybatisDataSourceFactory implements DataSourceFactory {

    private Properties properties;

    @Override
    public void setProperties(Properties props) {
        this.properties = props;
    }

    @Override
    public DataSource getDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(properties.getProperty("url"));
        dataSource.setUsername(properties.getProperty("username"));
        dataSource.setPassword(properties.getProperty("password"));
        try {
            dataSource.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataSource;
    }
}
