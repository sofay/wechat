package cn.fay.wechat.common.core;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/13 上午10:07.
 */
@RestController
@RequestMapping("/wechat/")
public class RootController {
    /**
     * 微信推送统一入口
     *
     * @return
     */
    @RequestMapping("/root/")
    public String root() {
        return "can not handle now, please call fay";
    }
}
