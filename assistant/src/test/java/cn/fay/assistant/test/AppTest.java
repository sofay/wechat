package cn.fay.assistant.test;

import cn.fay.assistant.Application;
import cn.fay.wechat.common.entity.WechatConfig;
import cn.fay.wechat.common.util.CommonUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/12 下午8:37.
 */
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = Application.class)
@WebAppConfiguration // 由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
public class AppTest {
    @Autowired
    WechatConfig wechatConfig;
    @Value("${spring.profiles.active}")
    String profile;

    @Before
    public void before() {
        System.err.println("当前使用profile:" + profile);
    }

    @Test
    public void test() {
//        System.err.println(wechatConfig);
        System.err.println(CommonUtils.getAccessToken(wechatConfig.getAppid(), wechatConfig.getAppsecret()));

    }
}
