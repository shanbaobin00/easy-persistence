package com.eric.io;

import java.io.InputStream;

/**
 * @Author: shanbb
 * @Date: 2020/8/21 8:59
 * @Description:
 * @Modified By:
 */
public class Resources {

    /**
     * 根据配置文件路径，将配置文件加载成字节输入流，存储在内存中
     * @param path
     * @return
     */
    public static InputStream getResourceAsStream(String path){
        return Resources.class.getClassLoader().getResourceAsStream(path);
    }

}
