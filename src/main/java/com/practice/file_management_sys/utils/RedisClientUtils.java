package com.practice.file_management_sys.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类
**/
@Component("redisUtils")
public class RedisClientUtils {

	@Resource(name = "stringRedisTemplate")
	private StringRedisTemplate redisTemplate; //jdbcTemplate

	
	
	/**
	 * 功能描述：设置key-value到redis中
	 * @param key 键
	 * @param value 值
	 * @return 成功返回true
	 */
	public boolean set(String key ,String value){
		try{
			redisTemplate.opsForValue().set(key, value);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}

	 /**
     * 通过字符串key获取值
     * @param key 键
     * @return 值
     */
    public String get(String key){
        return key==null?null:redisTemplate.opsForValue().get(key);
    }


	/**
	 * 功能描述：设置某个key过期时间
	 * @param key 键
	 * @param time 设置时间
	 * @return 成功返回真值
	 */
	  public boolean expire(String key,long time){
	        try {
	            if(time>0){
	                redisTemplate.expire(key, time, TimeUnit.SECONDS);
	            }
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }




	  /**
	   * 功能描述：根据key 获取过期时间
	   * @param key 键
	   * @return 该键持续时间
	   */
	  public long getExpire(String key){
	        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
	    }


	  	/**
	     * 递增
	     * @param key 键
	     * @return 递增后值
	     */
	    public long incr(String key, long delta){
	        return redisTemplate.opsForValue().increment(key, delta);
	    }


	    /**
	     * 递减
	     * @param key 键
	     * @param delta 要减少几
	     * @return 减少后的值
	     */
	    public long decr(String key, long delta){
	        return redisTemplate.opsForValue().increment(key, -delta);
	    }

	    //==============Map结构=====================


	    //==============List结构=====================



	    
}
