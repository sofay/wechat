package cn.fay.wechat.common.config;

import cn.fay.wechat.common.beanfactorypostprocessor.DetectWechatEventListenerBeanFactoryPostProcessor;
import cn.fay.wechat.common.filter.WechatAuthFilter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
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

    /**
     * 这个地方用实现{@code EnvironmentAware}的方式来完成从配置中读取扫描路径wechat.handler.scan.packages
     * 不能使用@Autowire 和@Value是因为该方法定义了一个{@link BeanFactoryPostProcessor}，调用地方在{@link AbstractApplicationContext#refresh()}中的{@link AbstractApplicationContext#invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory)}
     * 在调用时还没有实例化{@link org.springframework.beans.factory.config.BeanPostProcessor} 所以{@link org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor}没有实例化，故在实例本类{@code WechatSpringConfig}
     * 时不能使用 @Autowire 和 @Value
     * @return
     */
    @Bean
    public BeanFactoryPostProcessor detectWechatEventListenerBeanFactoryPostProcessor() {
        BeanFactoryPostProcessor beanFactoryPostProcessor = new DetectWechatEventListenerBeanFactoryPostProcessor();
        ((DetectWechatEventListenerBeanFactoryPostProcessor) beanFactoryPostProcessor).setBasePackages(environment.resolvePlaceholders("${wechat.handler.scan.packages:cn.fay.wechat.common.core}"));
        return beanFactoryPostProcessor;
    }

    @Bean
    public FilterRegistrationBean wechatAuthFilter() {
        FilterRegistrationBean<WechatAuthFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new WechatAuthFilter());
        return filterRegistrationBean;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
