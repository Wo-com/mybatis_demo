package com.cnetopro.test;


import com.cnetopro.dao.IUserDao;
import com.cnetopro.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 *
 */
public class MybatisTest {

    InputStream in;
    SqlSession sqlSession;
    IUserDao userDao;

    @Before//初始化
    public void init()throws Exception{
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂创建SqlSession
        sqlSession =factory.openSession();
        //4.使用SqlSession创建dao接口的代理对象
        userDao =sqlSession.getMapper(IUserDao.class);
    }
    @After//测试结束之后执行
    public void destory() throws Exception{
        //6.释放资源
        sqlSession.close();
        in.close();
    }

    @Test//查询所有
    public void testfindAll() throws Exception {
        //5.使用代理对象的方法
        List<User> users = userDao.findAll();
        for (User user:users) {
            System.out.println(user);
        }
    }

    @Test//增加
    public void testsaveUser() throws Exception {
        User user=new User();
        user.setId(117);
        user.setName("哈哈");
        user.setAge(18);

        userDao.saveUser(user);
    }

    @Test//更新
    public void testupdateUser() throws Exception {
        User user=new User();
        user.setId(116);
        user.setName("wa哈哈");
        user.setAge(1888);

        userDao.updateUser(user);
    }
    @Test//删除
    public void testdelectUser(){
        userDao.delectUser(116);
    }

}

