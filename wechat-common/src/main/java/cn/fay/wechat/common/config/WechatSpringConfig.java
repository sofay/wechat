package cn.fay.wechat.common.config;

import cn.fay.wechat.common.beanfactorypostprocessor.DetectWechatEventListenerBeanFactoryPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/13 上午10:01.
 */
@Configuration
@ComponentScan(basePackages = "cn.fay.wechat.common.core")
public class WechatSpringConfig implements EnvironmentAware {
    private Environment environment;

    @Bean
    public BeanFactoryPostProcessor detectWechatEventListenerBeanFactoryPostProcessor() {
        BeanFactoryPostProcessor beanFactoryPostProcessor = new DetectWechatEventListenerBeanFactoryPostProcessor();
        ((DetectWechatEventListenerBeanFactoryPostProcessor) beanFactoryPostProcessor).setBasePackages(environment.resolvePlaceholders("${wechat.listener.scan.packages:cn.fay.wechat.common.core}"));
        return beanFactoryPostProcessor;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
