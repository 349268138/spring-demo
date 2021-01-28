package com.meituan.pay.finsecurity.adapter;

import com.meituan.pay.finsecurity.constant.MccConstant;
import com.sankuai.meituan.config.MtConfigClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.doReturn;

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
    public void initEventDataMapTest() {
        doReturn(null).when(mtConfigClient).getValue(MccConstant.LONG_CONVERT_SET_KEY);
        mccAdapter.init();
        Assert.assertTrue(mccAdapter.getLongConvertSet().isEmpty());

        doReturn("").when(mtConfigClient).getValue(MccConstant.LONG_CONVERT_SET_KEY);
        mccAdapter.init();
        Assert.assertTrue(mccAdapter.getLongConvertSet().isEmpty());

        doReturn("averageMoney,averateCount").when(mtConfigClient).getValue(MccConstant.LONG_CONVERT_SET_KEY);
        mccAdapter.init();
        Assert.assertFalse(mccAdapter.getLongConvertSet().isEmpty());
    }
}
