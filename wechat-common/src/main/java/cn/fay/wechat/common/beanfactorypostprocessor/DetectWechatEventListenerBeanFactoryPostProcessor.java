package cn.fay.wechat.common.beanfactorypostprocessor;

import cn.fay.wechat.common.annotation.Listener;
import cn.fay.wechat.common.core.RootController;
import cn.fay.wechat.common.handler.WechatEventHandler;
import cn.fay.wechat.common.handler.WechatEventHandlers;
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

import java.lang.reflect.Field;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/13 下午1:45.
 */
public class DetectWechatEventListenerBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(DetectWechatEventListenerBeanFactoryPostProcessor.class);
    private String[] basePackages;
    private String wechatEventHandlersBeanName = "";
    private String rootControllerBeanName = "";
    private boolean init = false;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        if (!init) {
            String[] rootControllersBeanNames = beanFactory.getBeanNamesForType(RootController.class);
            if (rootControllersBeanNames.length == 1) {
                rootControllerBeanName = rootControllersBeanNames[0];
                for (Field field : RootController.class.getDeclaredFields()) {
                    if (WechatEventHandlers.class.isAssignableFrom((Class)field.getGenericType())) {
                        wechatEventHandlersBeanName = field.getName();
                        init = true;
                        break;
                    }
                }
            }
            if (!init) {
                LOGGER.warn("DetectWechatEventListenerBeanFactoryPostProcessor init error: there is no RootController or more, or not found {} field in {}", WechatEventHandlers.class.getName(), RootController.class);
                LOGGER.error("DetectWechatEventListenerBeanFactoryPostProcessor init error: there is no RootController or more, or not found {} field in {}", WechatEventHandlers.class.getName(), RootController.class);
            }
        }
        if (beanFactory instanceof BeanDefinitionRegistry) {
            ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner((BeanDefinitionRegistry) beanFactory, false) {
                @Override
                protected void registerBeanDefinition(BeanDefinitionHolder definitionHolder, BeanDefinitionRegistry registry) {
                    super.registerBeanDefinition(definitionHolder, registry);
                    if (registry instanceof DefaultListableBeanFactory) {
                        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) registry;
                        BeanDefinition beanDefinition = defaultListableBeanFactory.getBeanDefinition(rootControllerBeanName);
                        Object object = beanDefinition.getPropertyValues().get(wechatEventHandlersBeanName);
                        if (object == null) {
                            WechatEventHandlers wechatEventHandlers = new WechatEventHandlers();
                            beanDefinition.getPropertyValues().addPropertyValue(wechatEventHandlersBeanName, wechatEventHandlers);
                            object = wechatEventHandlers;
                        }
                        WechatEventHandlers wechatEventHandlers = (WechatEventHandlers) object;
                        wechatEventHandlers.registHandler(/*init now*/beanFactory.getBean(definitionHolder.getBeanName(), WechatEventHandler.class));
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
