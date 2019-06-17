package com.baoshi.wcs.common.http;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.util.List;

public interface HttpMessageConverter<T> {
        //指示此转换器是否可以读取给定的类。
    boolean canRead(Class<?> clazz, @Nullable MediaType mediaType);
 
        //指示此转换器是否可以写给定的类。
    boolean canWrite(Class<?> clazz, @Nullable MediaType mediaType);
 
        //返回List<MediaType>
    List<MediaType> getSupportedMediaTypes();
 
        //读取一个inputMessage
    T read(Class<? extends T> clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException;
 
        //往output message写一个Object
    void write(T t, @Nullable MediaType contentType, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException;
 
}