package org.yxs.design;


import org.junit.jupiter.api.Test;
import org.yxs.design.adapter.EGMCacheAdapter;
import org.yxs.design.adapter.IIRCacheAdapter;
import org.yxs.design.impl.CacheServiceImpl;
import org.yxs.design.proxy.JDKProxy;

public class ApiTest {

    @Test
    public void test_CacheService() throws Exception {

        CacheService proxy_EGM = JDKProxy.getProxy(CacheServiceImpl.class, new EGMCacheAdapter());
        proxy_EGM.set("user_name_01", "yxs01");
        String val01 = proxy_EGM.get("user_name_01");
        System.out.println("测试结果：" + val01);

        CacheService proxy_IIR = JDKProxy.getProxy(CacheServiceImpl.class, new IIRCacheAdapter());
        proxy_IIR.set("user_name_02", "yxs02");
        String val02 = proxy_IIR.get("user_name_02");
        System.out.println("测试结果：" + val02);

    }

}
