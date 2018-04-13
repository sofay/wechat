package cn.fay.wechat.common.beanfactorypostprocessor;

import cn.fay.wechat.common.annotation.Listener;
import cn.fay.wechat.common.listener.WechatEventHandler;
import cn.fay.wechat.common.listener.WechatEventHandlers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/13 下午1:45.
 */
public class DetectWechatEventListenerBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(DetectWechatEventListenerBeanFactoryPostProcessor.class);
    private String[] basePackages;
    private String wechatEventListenersBeanName = "wechatEventListeners";
    private String rootControllerBeanName = "rootController";

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        if (beanFactory instanceof BeanDefinitionRegistry) {
            ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner((BeanDefinitionRegistry) beanFactory, false) {
                @Override
                protected void registerBeanDefinition(BeanDefinitionHolder definitionHolder, BeanDefinitionRegistry registry) {
                    super.registerBeanDefinition(definitionHolder, registry);
                    if (registry instanceof DefaultListableBeanFactory) {
                        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) registry;
                        BeanDefinition beanDefinition = defaultListableBeanFactory.getBeanDefinition(rootControllerBeanName);
                        Object object = beanDefinition.getPropertyValues().get(wechatEventListenersBeanName);
                        if (object == null) {
                            WechatEventHandlers wechatEventHandlers = new WechatEventHandlers();
                            beanDefinition.getPropertyValues().addPropertyValue(wechatEventListenersBeanName, wechatEventHandlers);
                            object = wechatEventHandlers;
                        }
                        WechatEventHandlers wechatEventHandlers = (WechatEventHandlers) object;
                        wechatEventHandlers.registListener(/*init now*/beanFactory.getBean(definitionHolder.getBeanName(), WechatEventHandler.class));
                    }
                    LOGGER.info("DETECTED WECHAT EVENT LISTENER: {}", definitionHolder.getBeanDefinition().getBeanClassName());
                }
            };
            scanner.addIncludeFilter(new AnnotationTypeFilter(Listener.class));
            scanner.scan(basePackages);
        } else {
            LOGGER.error("由于BeanFactory实例不是BeanDefinitionRegistry, @Listener将无法正常工作");
        }
    }

    /**
     * 逗号分隔多个包路径
     *
     * @param basePackages
     */
    public void setBasePackages(String basePackages) {
        if (basePackages != null) {
            this.basePackages = basePackages.split(",");
        }
    }
}
