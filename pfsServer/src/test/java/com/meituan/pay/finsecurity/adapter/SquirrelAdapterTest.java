package com.meituan.pay.finsecurity.adapter;

import com.dianping.squirrel.client.impl.redis.RedisStoreClient;
import com.meituan.funds.simple.util.JacksonUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/**
 * @author hhhb
 * @date 2020/11/4 2:27 下午
 */
public class SquirrelAdapterTest {
    @InjectMocks
    private SquirrelAdapter squirrelAdapter;

    @Mock
    private RedisStoreClient redisStoreClient;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void initEventDataMapTest() {

    }

    @Test
    public void setSuccessTest() {
        when(redisStoreClient.set(any(), any())).thenReturn(true);
        Assert.assertTrue(squirrelAdapter.set("", "", ""));
    }

    @Test
    public void setErrorTest() {
        RuntimeException runtimeException = new RuntimeException();
        when(redisStoreClient.set(any(), any())).thenThrow(runtimeException);
        try {
            squirrelAdapter.set("", "", "");
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage().contains("squirrel set error"));
        }
    }

    @Test
    public void hGetAllSuccessTest() {
        when(redisStoreClient.hgetAll(any())).thenReturn(Collections.EMPTY_MAP);
        Assert.assertTrue(squirrelAdapter.hGetAll("", "").equals(JacksonUtils.toJson(Collections.EMPTY_MAP)));
    }

    @Test
    public void hGetAllErrorTest() {
        RuntimeException runtimeException = new RuntimeException();
        when(redisStoreClient.hgetAll(any())).thenThrow(runtimeException);
        try {
            squirrelAdapter.hGetAll("", "");
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage().contains("squirrel hGetAll error"));
        }
    }

    @Test
    public void hincrBySuccessTest() {
        when(redisStoreClient.hincrBy(any(), any(), anyInt())).thenReturn(1L);
        Assert.assertTrue(squirrelAdapter.hincrBy("", "", "", 1).equals(1L));
    }

    @Test
    public void hincrByErrorTest() {
        RuntimeException runtimeException = new RuntimeException();
        when(redisStoreClient.hincrBy(any(), any(), anyInt())).thenThrow(runtimeException);
        try {
            squirrelAdapter.hincrBy("", "", "", 1);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage().contains("squirrel hincrBy error"));
        }
    }

    @Test
    public void hSetSuccessTest() {
        when(redisStoreClient.hset(any(), any(), anyObject())).thenReturn(1L);
        Assert.assertTrue(squirrelAdapter.hincrBy("", "", "", 1).equals(1L));
    }

    @Test
    public void hSetErrorTest() {
        RuntimeException runtimeException = new RuntimeException();
        when(redisStoreClient.hset(any(), any(), anyInt())).thenThrow(runtimeException);
        try {
            squirrelAdapter.hSet("", "", "", 1);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage().contains("squirrel hincrBy error"));
        }
    }

    @Test
    public void mGetSuccessTest() {
        when(redisStoreClient.multiGet(any())).thenReturn(Collections.EMPTY_MAP);
        Assert.assertTrue(squirrelAdapter.mGet(any(), anyList()).equals(JacksonUtils.toJson(Collections.EMPTY_MAP)));
    }

    @Test
    public void mGetErrorTest() {
        RuntimeException runtimeException = new RuntimeException();
        when(redisStoreClient.multiGet(any())).thenThrow(runtimeException);
        try {
            List<String> keys = new ArrayList<>();
            keys.add("");
            squirrelAdapter.mGet("", keys);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage().contains("squirrel multiGet error"));
        }
    }

    @Test
    public void incrBySuccessTest() {
        when(redisStoreClient.incrBy(any(), anyLong())).thenReturn(1L);
        Assert.assertTrue(squirrelAdapter.incrBy("", "", 1).equals(1L));
    }

    @Test
    public void incrByErrorTest() {
        RuntimeException runtimeException = new RuntimeException();
        when(redisStoreClient.incrBy(any(), anyLong())).thenThrow(runtimeException);
        try {
            squirrelAdapter.incrBy("", "", 1);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage().contains("squirrel incrBy error"));
        }
    }

    @Test
    public void hGetBySuccessTest() {
        when(redisStoreClient.hget(any(), any())).thenReturn("1");
        Assert.assertTrue(squirrelAdapter.hGet("", "", "").equals("1"));
    }

    @Test
    public void hGetByErrorTest() {
        RuntimeException runtimeException = new RuntimeException();
        when(redisStoreClient.hget(any(), any())).thenThrow(runtimeException);
        try {
            squirrelAdapter.hGet("", "", "");
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage().contains("squirrel hGet error"));
        }
    }
}
