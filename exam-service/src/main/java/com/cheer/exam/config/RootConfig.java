package com.cheer.exam.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;


@Configuration//标识当前类为配置类
@ComponentScan("com.cheer.exam")//需要扫描的包路径
@EnableTransactionManagement //启用事务
@MapperScan("com.cheer.exam.dao")//mybatis mapper接口所在路径
@PropertySource ( "classpath:jdbc.properties" )//导入外部properties配置文件
public class RootConfig {
    @Value ( "${jdbc.username}" )
    private String username;

    @Value ( "${jdbc.password}" )
    private String password;

    @Value ( "${jdbc.url}" )
    private String url;

    @Value ( "${jdbc.driver}" )
    private String driverClass;

    @Bean //将当前方法返回的对象放入spring容器
    public DataSource dataSource() {
        // 创建数据源 使用alibaba的druid数据库连接池
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driverClass);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        return druidDataSource;
    }

    @Bean // 配置事务，把当前的事务与上面配置好的数据源关联起来 spring使用的是声明式事务
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }

    @Bean // mybatis的SqlSessionFactory
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 关联数据源
        sqlSessionFactoryBean.setDataSource(dataSource);

        // 资源解析器
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver ();
        // 指定mybatis的核心配置文件
        sqlSessionFactoryBean.setConfigLocation(resourcePatternResolver.getResource("classpath:mybatis-config.xml"));
        // 指定mybatis的mapper配置文件
        sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources("classpath:mapper/*Mapper.xml"));
        return sqlSessionFactoryBean;
    }
}
