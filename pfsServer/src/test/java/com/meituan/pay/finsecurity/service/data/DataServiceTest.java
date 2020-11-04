package com.meituan.pay.finsecurity.service.data;

import com.meituan.pay.finsecurity.adapter.MccAdapter;
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
public class DataServiceTest {
    @InjectMocks
    private DataService dataService;

    @Mock
    private TradeDataService tradeDataService;

    @Mock
    private MccAdapter mccAdapter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void initEventData(){
//        when(mccAdapter.getString(anyString(),anyString())).thenReturn();
    }

}
