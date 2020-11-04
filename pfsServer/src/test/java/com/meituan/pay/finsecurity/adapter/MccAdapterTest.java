package com.meituan.pay.finsecurity.adapter;

import com.meituan.pay.finsecurity.constant.MccConstant;
import com.sankuai.meituan.config.MtConfigClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @author hhhb
 * @date 2020/11/4 2:27 下午
 */
public class MccAdapterTest {
    @InjectMocks
    private MccAdapter mccAdapter;

    @Mock
    private MtConfigClient mtConfigClient;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getStringTest(){

        String value = null;
        value = mccAdapter.getString("unexist_key", "hb");
        Assert.assertEquals(null, value);

        when(mtConfigClient.getValue(MccConstant.EVENTDATAMAP_KEY)).thenReturn("test");
        value = mccAdapter.getString(MccConstant.EVENTDATAMAP_KEY, "hb");
        Assert.assertEquals("test", value);

        when(mtConfigClient.getValue(anyString())).thenThrow(new RuntimeException());
        value = mccAdapter.getString(MccConstant.EVENTDATAMAP_KEY, "hb");
        Assert.assertEquals("hb", value);
    }

}
