package com.cheer.exam.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtils {
    // 保证系统中有且只有一个factory实例
    private static SqlSessionFactory factory;

    // 静态代码块 在静态变量初始化完之后执行 并且只被执行一次
    static {
        InputStream in = null;
        try {
            // 导入mybatis的配置文件 mybatis-config.xml
            in = Resources.getResourceAsStream( "mybatis-config.xml" );
            // 创建一个SqlSessionFactory对象
            factory = new SqlSessionFactoryBuilder().build(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 打开session并获取
     *
     * @return
     */
    public static SqlSession getSqlSession() {
        return factory.openSession();
    }

    /**
     * 关闭session
     *
     * @param sqlSession
     */
    public static void closeSqlSession(SqlSession sqlSession) {
        // 提交事务
        sqlSession.commit();
        sqlSession.close();
    }
}
