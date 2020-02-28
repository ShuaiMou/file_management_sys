package com.practice.file_management_sys.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Saul
 * @Date: 26/2/20 9:49 PM
 * @Description:
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600)//开启redis集中式session管理，把所有的session都存放到了redis中
@EnableCaching
public class RedisConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisConfig.class);

    /***
     * @Author Saul
     * @Description  TODO: 类名+方法名+参数列表
     * @Date 9:53 PM 26/2/20
     * @return {@link {@link org.springframework.cache.interceptor.KeyGenerator}}
     */
    @Bean
    public KeyGenerator simpleKeyGenerator(){
        return (target, method, params) -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(target.getClass().getSimpleName()).append(".");
            stringBuilder.append(method.getName()).append("[");
            for (Object obj : params){
                if(obj != null)
                    stringBuilder.append(obj.toString());
            }
            stringBuilder.append("]");
            return stringBuilder.toString();
        };
    }
    
    /***
     * @Author Saul
     * @Description  TODO: 自定义CacheManager
     * @Date 9:59 PM 26/2/20
     * @param redisConnectionFactory
     * @return {@link {@link org.springframework.cache.CacheManager}}
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory){
        LOGGER.debug("FMS--自定义CacheManager初始化完成");
        return new RedisCacheManager(
                RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
                this.getRedisCacheConfigurationWithTtl(6000), //默认策略，未配置的key会使用这个时间
                this.getRedisCacheConfigurationMap()
        );

    }
    
    /***
     * @Author Saul
     * @Description  TODO: 自定义对应缓存名字的失效时间
     * @Date 9:54 PM 26/2/20
     * @return {@link {@link Map<String, RedisCacheConfiguration>}}
     */
    private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap(){
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
        redisCacheConfigurationMap.put("user", this.getRedisCacheConfigurationWithTtl(3600));
        redisCacheConfigurationMap.put("fileList", this.getRedisCacheConfigurationWithTtl(3600));
        return redisCacheConfigurationMap;
    }
    
    /***
     * @Author Saul
     * @Description  TODO: 自定义序列化方式
     * @Date 9:56 PM 26/2/20
     * @param seconds 秒
     * @return {@link {@link org.springframework.data.redis.cache.RedisCacheConfiguration}}
     */
    private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Integer seconds){
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer)
        ).entryTtl(Duration.ofSeconds(seconds));
        return redisCacheConfiguration;
    }

}
