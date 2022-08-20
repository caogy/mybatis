import com.alibaba.druid.pool.DruidDataSource;
import com.cgy.demo01.mapper.SysUserMapper;
import com.cgy.demo01.model.SysUser;
import com.mysql.cj.jdbc.Driver;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class TestMybatisBase {

    /**
     * 使用代码构建 mybatis
     */
    @Test
    public void testCodeSetting() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3380/mybatis";
        String userName = "root";
        String password = "root";

        //mybatis 数据库连接
        UnpooledDataSource defaultDataSource = new UnpooledDataSource(driver, url, userName, password);

        //mybatis 数据库连接池
        PooledDataSource pooledDataSource = new PooledDataSource(defaultDataSource);

        //阿里巴巴数据库连接池
        DruidDataSource alibabaDataSource = new DruidDataSource();
        alibabaDataSource.setUrl(url);
        alibabaDataSource.setUsername(userName);
        alibabaDataSource.setPassword(password);

        Environment environment = new Environment("development", new JdbcTransactionFactory(), pooledDataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(SysUserMapper.class);
        //配置启用驼峰命名自动映射
        configuration.setMapUnderscoreToCamelCase(true);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser user = userMapper.selectUserById(1L);
            System.out.println(user);
        }
    }

    /**
     * 使用配置文件实现与 testCodeSetting 同样的功能
     */
    @Test
    public void testFileSetting() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser user = userMapper.selectUserById(1L);
            System.out.println(user);
        }
    }
}
