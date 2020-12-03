package com.meituan.pay.finsecurity.adapter;

import com.dianping.squirrel.client.impl.redis.RedisStoreClient;
import com.meituan.funds.simple.util.JacksonUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.AssertTrue;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyObject;
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
}
