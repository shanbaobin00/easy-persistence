package com.eric.dao;

import com.eric.io.Resources;
import com.eric.pojo.User;
import com.eric.session.SqlSession;
import com.eric.session.SqlSessionFactory;
import com.eric.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * @Author: shanbb
 * @Date: 2020/8/21 11:07
 * @Description:
 * @Modified By:
 */
public class UserDaoImpl implements IUserDao {
    @Override
    public List<User> findAll() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().bulid(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> users = sqlSession.selectList("user.selectList");
        return users;
    }

    @Override
    public User findByCondition(User user) throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().bulid(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession.selectOne("user.selectOne", user);
    }
}
