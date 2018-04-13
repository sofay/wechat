package cn.fay.wechat.common.annotation;

import java.lang.annotation.*;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/13 下午1:46.
 * 标识需要注册的微信监听器 {@link cn.fay.wechat.common.listener.WechatEventListener}
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Listener {
}
