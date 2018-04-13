package cn.fay.wechat.common.adapt;

import cn.fay.wechat.common.entity.CustomEventMsg;
import cn.fay.wechat.common.entity.EventMsg;
import cn.fay.wechat.common.entity.LocationEventMsg;
import cn.fay.wechat.common.entity.ScanEventMsg;
import cn.fay.wechat.common.listener.WechatEventListener;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/13 下午1:35.
 */
public class WechatEventListenerAdapt implements WechatEventListener {
    @Override
    public void onSubscribe(EventMsg eventMsg) {
        // pass
    }

    @Override
    public void onUnsubscribe(EventMsg eventMsg) {
        // pass
    }

    @Override
    public void onScan(ScanEventMsg scanEventMsg) {
        // pass
    }

    @Override
    public void onLocation(LocationEventMsg locationEventMsg) {
        // pass
    }

    @Override
    public void onClick(CustomEventMsg customEventMsg) {
        // pass
    }

    @Override
    public void onView(CustomEventMsg customEventMsg) {
        // pass
    }
}
