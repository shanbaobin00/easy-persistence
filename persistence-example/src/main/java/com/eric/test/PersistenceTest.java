package com.eric.test;

import com.eric.dao.IUserDao;
import com.eric.io.Resources;
import com.eric.pojo.User;
import com.eric.session.SqlSession;
import com.eric.session.SqlSessionFactory;
import com.eric.session.SqlSessionFactoryBuilder;
import org.dom4j.DocumentException;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author: shanbb
 * @Date: 2020/8/21 9:01
 * @Description:
 * @Modified By:
 */
public class PersistenceTest {

    @Test
    public void test() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().bulid(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId(1);
        user.setUsername("eric");
//        User user2 = sqlSession.selectOne("user.selectOne", user);
//        System.out.println(user2);

//        List<User> users = sqlSession.selectList("user.selectList");
//        for (User u : users) {
//            System.out.println(u);
//        }

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        User user2 = userDao.findByCondition(user);
        System.out.println(user2);
        List<User> users = userDao.findAll();
        for (User user1 : users) {
            System.out.println(user1);
        }
    }

}
