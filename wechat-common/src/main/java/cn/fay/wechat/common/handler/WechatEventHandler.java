package cn.fay.wechat.common.handler;

import cn.fay.wechat.common.core.WXContext;
import cn.fay.wechat.common.entity.CustomEventMsg;
import cn.fay.wechat.common.entity.EventMsg;
import cn.fay.wechat.common.entity.LocationEventMsg;
import cn.fay.wechat.common.entity.ScanEventMsg;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/13 下午1:24.
 */
public interface WechatEventHandler {
    void onSubscribe(EventMsg eventMsg, WXContext context);

    void onUnsubscribe(EventMsg eventMsg, WXContext context);

    void onScan(ScanEventMsg scanEventMsg, WXContext context);

    void onLocation(LocationEventMsg locationEventMsg, WXContext context);

    void onClick(CustomEventMsg customEventMsg, WXContext context);

    void onView(CustomEventMsg customEventMsg, WXContext context);
}
