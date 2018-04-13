package cn.fay.wechat.common.listener;

import cn.fay.wechat.common.entity.CustomEventMsg;
import cn.fay.wechat.common.entity.EventMsg;
import cn.fay.wechat.common.entity.LocationEventMsg;
import cn.fay.wechat.common.entity.ScanEventMsg;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/13 下午1:24.
 */
public interface WechatEventListener {
    void onSubscribe(EventMsg eventMsg);

    void onUnsubscribe(EventMsg eventMsg);

    void onScan(ScanEventMsg scanEventMsg);

    void onLocation(LocationEventMsg locationEventMsg);

    void onClick(CustomEventMsg customEventMsg);

    void onView(CustomEventMsg customEventMsg);
}
