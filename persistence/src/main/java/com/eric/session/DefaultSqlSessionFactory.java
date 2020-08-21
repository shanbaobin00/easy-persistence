package com.eric.session;

import com.eric.pojo.Configuration;

/**
 * @Author: shanbb
 * @Date: 2020/8/21 9:40
 * @Description:
 * @Modified By:
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession(){
        return new DefaultSqlSession(configuration);
    }

}
