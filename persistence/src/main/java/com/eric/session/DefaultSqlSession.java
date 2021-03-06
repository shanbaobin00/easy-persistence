package com.eric.session;

import com.eric.pojo.Configuration;

import java.lang.reflect.*;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author: shanbb
 * @Date: 2020/8/21 9:43
 * @Description:
 * @Modified By:
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object... params) throws Exception {
        //将要去完成对simpleExecutor里的query方法的调用
        Executor executor = new SimpleExecutor();
        return executor.query(configuration, configuration.getMappedStatementMap().get(statementId), params);
    }

    @Override
    public <T> T selectOne(String statementId, Object... params) throws Exception {
        List<Object> objects = selectList(statementId, params);
        if (objects.size() == 1){
            return (T) objects.get(0);
        }else {
            throw new RuntimeException("查询结果为空或者返回结果过多");
        }
    }

    @Override
    public <T> T getMapper(Class<?> mapperClass) {
        // 使用JDK动态代理来为Dao接口生成代理对象，并返回
        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, (proxy, method, args) -> {
            // 底层都还是去执行JDBC代码 //根据不同情况，来调用selectList或者selectOne
            // 准备参数 1：statementid： sql语句的唯一标识：namespace.id
            // 方法名：findAll
            String methodName = method.getName();
            String className = method.getDeclaringClass().getName();

            String statementId = className + "." + methodName;

            // 准备参数2：params: args
            // 获取被调用方法的返回值类型
            Type genericReturnType = method.getGenericReturnType();
            // 判断是否进行了 泛型类型参数化，说白了就是判断是否是泛型
            if (genericReturnType instanceof ParameterizedType){
                return selectList(statementId, args);
            }
            return selectOne(statementId, args);
        });
        return (T) proxyInstance;
    }

}
