package cn.fay.wechat.common.core;

import cn.fay.wechat.common.listener.WechatEventListeners;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/13 上午10:07.
 */
@RestController
public class RootController {
    private WechatEventListeners wechatEventListeners;
    /**
     * 微信推送统一入口
     *
     * @return
     */
    @RequestMapping("/${wechat.root.path:/wechat/root}")
    public String root() {
        return "can not handle now, please call fay";
    }

    public void setWechatEventListeners(WechatEventListeners wechatEventListeners) {
        this.wechatEventListeners = wechatEventListeners;
    }
}
