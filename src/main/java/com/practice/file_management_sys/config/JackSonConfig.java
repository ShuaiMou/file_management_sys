package com.practice.file_management_sys.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;

/**
 * @Auther: Saul
 * @Date: 2/3/20 8:35 下午
 * @Description:
 */
@Configuration
public class JackSonConfig {
    
    /***
     * @Author Saul
     * @Description  TODO: Spring Boot 中默认使用的 json 解析框架是 jackson,这里配置使空值转换为空字符串（""），
     * @Date 8:48 下午 2/3/20
     * @param builder
     * @return {@link {@link com.fasterxml.jackson.databind.ObjectMapper}}
     */
    @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder){
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {

            @Override
            public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeString("");
            }
        });
        return objectMapper;
    }
}
