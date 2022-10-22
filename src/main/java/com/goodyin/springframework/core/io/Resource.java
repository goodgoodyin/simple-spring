package com.goodyin.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 提供获取InputStream流的方法
 */
public interface Resource {

    InputStream getInputStream() throws IOException;

}
