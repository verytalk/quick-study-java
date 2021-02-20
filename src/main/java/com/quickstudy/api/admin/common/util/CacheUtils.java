package com.quickstudy.api.admin.common.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 缓存操作类
 */
@Component
@Slf4j
public class CacheUtils {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    // 维护一个本类的静态变量
    private static CacheUtils cacheUtils;

    private static Boolean openRedis;

    @Value("${site.setting.open-redis}")
    private void setOpenRedis(Boolean openRedis){
        CacheUtils.openRedis = openRedis;
    }


    @PostConstruct
    public void init() {
        cacheUtils = this;
        cacheUtils.redisTemplate = this.redisTemplate;
    }

    /**
     * 将参数中的字符串值设置为键的值，不设置过期时间
     * @param key
     * @param value 必须要实现 Serializable 接口
     */
    public static void set(String key, String value) {

        if(CacheUtils.openRedis){
            cacheUtils.redisTemplate.opsForValue().set(key, value);
        }

    }

    /**
     * 将参数中的字符串值设置为键的值，设置过期时间
     * @param key
     * @param value 必须要实现 Serializable 接口
     * @param timeout
     */
    public static void set(String key, String value, Long timeout) {
        if(CacheUtils.openRedis){
            cacheUtils.redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
        }
    }

    /**
     * 获取与指定键相关的值
     * @param key
     * @return
     */
    public static Object get(String key) {

        if(!CacheUtils.openRedis){
            return new HashSet<String>();
        }

        return cacheUtils.redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置某个键的过期时间
     * @param key 键值
     * @param ttl 过期秒数
     */
    public static boolean expire(String key, Long ttl) {
        if(!CacheUtils.openRedis){
            return false;
        }

        return cacheUtils.redisTemplate.expire(key, ttl, TimeUnit.SECONDS);

    }

    /**
     * 判断某个键是否存在
     * @param key 键值
     */
    public static boolean hasKey(String key) {
        log.info(key);
        log.info("openRedis : {}",CacheUtils.openRedis);
        if(!CacheUtils.openRedis){
            return false;
        }
        return cacheUtils.redisTemplate.hasKey(key);
    }

    /**
     * 向集合添加元素
     * @param key
     * @param value
     * @return 返回值为设置成功的value数
     */
    public static Long sAdd(String key, String... value) {
        if(!CacheUtils.openRedis){
            return 0L;
        }

        return cacheUtils.redisTemplate.opsForSet().add(key, value);
    }

    /**
     * 获取集合中的某个元素
     * @param key
     * @return 返回值为redis中键值为key的value的Set集合
     */
    public static Set<String> sGetMembers(String key) {

        if(!CacheUtils.openRedis){
            return new HashSet<String>();
        }

        return cacheUtils.redisTemplate.opsForSet().members(key);
    }

    /**
     * 将给定分数的指定成员添加到键中存储的排序集合中
     * @param key
     * @param value
     * @param score
     * @return
     */
    public static Boolean zAdd(String key, String value, double score) {
        return cacheUtils.redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * 返回指定排序集中给定成员的分数
     * @param key
     * @param value
     * @return
     */
    public static Double zScore(String key, String value) {
        return cacheUtils.redisTemplate.opsForZSet().score(key, value);
    }

    /**
     * 删除指定的键
     * @param key
     * @return
     */
    public static Boolean delete(String key) {

        if(CacheUtils.openRedis==false){
            return false;
        }
        return cacheUtils.redisTemplate.delete(key);
    }

    /**
     * 删除多个键
     * @param keys
     * @return
     */
    public static Long delete(Collection<String> keys) {
        if(CacheUtils.openRedis==false){
            return 0L;
        }
        return cacheUtils.redisTemplate.delete(keys);
    }
}
