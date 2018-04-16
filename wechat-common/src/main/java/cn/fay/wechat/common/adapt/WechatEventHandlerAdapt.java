package cn.fay.wechat.common.adapt;

import cn.fay.wechat.common.core.WXContext;
import cn.fay.wechat.common.entity.CustomEventMsg;
import cn.fay.wechat.common.entity.EventMsg;
import cn.fay.wechat.common.entity.LocationEventMsg;
import cn.fay.wechat.common.entity.ScanEventMsg;
import cn.fay.wechat.common.handler.WechatEventHandler;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/13 下午1:35.
 */
public class WechatEventHandlerAdapt implements WechatEventHandler {
    @Override
    public void onSubscribe(EventMsg eventMsg, WXContext context) {
        // pass
    }

    @Override
    public void onUnsubscribe(EventMsg eventMsg, WXContext context) {
        // pass
    }

    @Override
    public void onScan(ScanEventMsg scanEventMsg, WXContext context) {
        // pass
    }

    @Override
    public void onLocation(LocationEventMsg locationEventMsg, WXContext context) {
        // pass
    }

    @Override
    public void onClick(CustomEventMsg customEventMsg, WXContext context) {
        // pass
    }

    @Override
    public void onView(CustomEventMsg customEventMsg, WXContext context) {
        // pass
    }
}
