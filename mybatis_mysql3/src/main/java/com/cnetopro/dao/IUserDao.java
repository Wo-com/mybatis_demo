package com.cnetopro.dao;

import com.cnetopro.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *
 * 用户持久层接口
 *
 * */
public interface IUserDao {
    /**
     * 查询所有
     * */
    @Select("select * from user")
    List<User>findAll();
}
