package com.meituan.pay.finsecurity.adapter;

import com.dianping.squirrel.client.StoreKey;
import com.dianping.squirrel.client.impl.redis.RedisStoreClient;
import com.meituan.funds.simple.util.JacksonUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author wangjinping
 * @date 11/26/2020 13:56 PM
 */
@Service
public class SquirrelAdapter {

    private static final Logger logger = LoggerFactory.getLogger(SquirrelAdapter.class);

    @Autowired
    private RedisStoreClient redisStoreClient;

    @Autowired
    private MccAdapter mccAdapter;

    /**
     * 设置String类型的值
     *
     * @param category Squirrel分类名
     * @param key      key
     * @param value    value
     */
    public boolean set(String category, String key, Object value) {
        StoreKey storeKey = buildStoreKey(category, key);
        try {
            return redisStoreClient.set(storeKey, value);
        } catch (Exception e) {
            throw new RuntimeException(String.format("squirrel set error. category: %s, key: %s, value: %s", category, key, value), e);
        }
    }

    public String hGetAll(String category, String key) {
        try {
            Map<String, Object> result = redisStoreClient.hgetAll(buildStoreKey(category, key));
            return handleResult(result);
        } catch (Exception e) {
            throw new RuntimeException(String.format("squirrel hGetAll error. category: %s, key: %s", category, key), e);
        }
    }

    public Long hincrBy(String category, String key, String field, int amount) {
        try {
            return redisStoreClient.hincrBy(buildStoreKey(category, key), field, amount);
        } catch (Exception e) {
            throw new RuntimeException(String.format("squirrel hincrBy error. category: %s, key: %s", category, key), e);
        }
    }

    /**
     * 设置Hash类型的值
     *
     * @param category
     * @param key
     * @param field
     * @param value
     */
    public long hSet(String category, String key, String field, Object value) {
        StoreKey storeKey = buildStoreKey(category, key);
        try {
            return redisStoreClient.hset(storeKey, field, value);
        } catch (Exception e) {
            throw new RuntimeException(String.format("squirrel hSet error. category: %s, key: %s, field: %s, value: %s", category, key, field, value), e);
        }
    }

    /**
     * 批量获取String类型数据
     *
     * @param category Squirrel分类名
     * @param keys     keys
     * @return
     */
    public String mGet(String category, List<String> keys) {
        if (keys.isEmpty()) {
            return JacksonUtils.toJson(Collections.emptyMap());
        }
        List<StoreKey> storeKeys = new ArrayList<StoreKey>();
        for (String key : keys) {
            storeKeys.add(buildStoreKey(category, key));
        }

        Map<StoreKey, Object> squirrelResult = MapUtils.EMPTY_MAP;
        try {
            squirrelResult = redisStoreClient.multiGet(storeKeys);
        } catch (Exception e) {
            throw new RuntimeException(String.format("squirrel multiGet error. category: %s, keys: %s", category, keys), e);
        }
        if (MapUtils.isEmpty(squirrelResult)) {
            return JacksonUtils.toJson(Collections.emptyMap());
        }

        Map<String, Object> offlineData = new HashMap<>();
        for (Map.Entry<StoreKey, Object> entry : squirrelResult.entrySet()) {
            String key = String.valueOf(entry.getKey().getParams()[0]);
            offlineData.put(key, convertValue(key, entry.getValue()));
        }

        return JacksonUtils.toJson(offlineData);
    }

    public Long incrBy(String category, String key, long value) {
        StoreKey storeKey = buildStoreKey(category, key);
        try {
            return redisStoreClient.incrBy(storeKey, value);
        } catch (Exception e) {
            throw new RuntimeException(String.format("squirrel incrBy error. category: %s, key: %s, value: %s", category, key, value), e);
        }
    }

    public Object hGet(String category, String key, String field) {
        StoreKey storeKey = buildStoreKey(category, key);
        try {
            return redisStoreClient.hget(storeKey, field).toString();
        } catch (Exception e) {
            throw new RuntimeException(String.format("squirrel hGet error. category: %s, key: %s, field: %s", category, key, field), e);
        }
    }

    /**
     * 构建StoreKey
     *
     * @param category Squirrel分类名
     * @param key      key
     * @return
     */
    private StoreKey buildStoreKey(String category, String key) {
        return new StoreKey(category, key);
    }

    private String handleResult(Map<String, Object> dataMap) {
        Map<String, Object> result = new HashMap<>();
        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            result.put(entry.getKey(), convertValue(entry.getKey(), entry.getValue()));
        }
        return JacksonUtils.toJson(result);
    }

    private Object convertValue(String key, Object value) {
        if (mccAdapter.getLongConvertSet().contains(key)) {
            return Long.parseLong(String.valueOf(value));
        }
        return value;
    }
}