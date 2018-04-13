package cn.fay.assistant.config;

import cn.fay.wechat.common.config.WechatSpringConfig;
import cn.fay.wechat.common.entity.WechatConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/12 下午8:00.
 */
@Configuration
@PropertySource("classpath:config-${spring.profiles.active}.properties")
@PropertySource("classpath:config.properties")
@Import(WechatSpringConfig.class)
public class SpringConfig {

    @Bean
    @ConfigurationProperties(prefix = "wechat.config")
    public WechatConfig wechatConfig() {
        return new WechatConfig();
    }
}
