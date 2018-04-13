package cn.fay.wechat.common.core;

import cn.fay.wechat.common.config.RootConfig;
import cn.fay.wechat.common.config.WechatSpringConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/13 上午10:02.
 */
@Deprecated
public class Dispatcher extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WechatSpringConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
