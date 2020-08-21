package com.eric.session;

import com.eric.config.XMLConfigBuilder;
import com.eric.pojo.Configuration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * @Author: shanbb
 * @Date: 2020/8/21 9:12
 * @Description:
 * @Modified By:
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory bulid(InputStream in) throws Exception {
        // 第一： 使用dom4j解析配置文件，将解析出来的内容封装到Configuration中
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseConfig(in);

        // 第二：创建SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(configuration);
        return sqlSessionFactory;
    }

}
