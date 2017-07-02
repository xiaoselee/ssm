package cn.test.web.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.mchange.v2.c3p0.PoolConfig;


//@Configuration
//@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {
	
	public PoolConfig poolConfig() {
		PoolConfig pc = new PoolConfig();
		return pc;
	}
	
    //@Bean  
    public JedisConnectionFactory redisConnectionFactory() {  
        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();  
  
        // Defaults  
        redisConnectionFactory.setHostName("192.168.56.101");  
        redisConnectionFactory.setPort(6379);  
        redisConnectionFactory.setPassword("111111");
        return redisConnectionFactory;  
    }  
  
   // @Bean  
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory cf) {  
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();  
        redisTemplate.setConnectionFactory(cf);  
        return redisTemplate;  
    }  
  
  //  @Bean  
    public CacheManager cacheManager(RedisTemplate redisTemplate) {  
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);  
        // Number of seconds before expiration. Defaults to unlimited (0)  
        cacheManager.setDefaultExpiration(3000); // Sets the default expire time (in seconds)  
        return cacheManager;  
    }

}
