package com.cnetopro.test;


import com.cnetopro.dao.IUserDao;
import com.cnetopro.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 *
 */
public class MybatisTest {

    public static void main(String[] args) throws Exception {
//1.读取配置文件
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
//2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
//3.使用工厂创建SqlSession
        SqlSession session =factory.openSession();
//4.使用SqlSession创建dao接口的代理对象
        IUserDao userDao =session.getMapper(IUserDao.class);
//5.使用代理对象的方法
        List<User> users = userDao.findAll();
        for (User user:users
        ) {
            System.out.println(user);
        }
//6.释放资源
        session.close();
        inputStream.close();
    }
}

