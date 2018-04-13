package cn.fay.wechat.common.listener;

import cn.fay.wechat.common.entity.CustomEventMsg;
import cn.fay.wechat.common.entity.EventMsg;
import cn.fay.wechat.common.entity.LocationEventMsg;
import cn.fay.wechat.common.entity.ScanEventMsg;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/13 下午1:38.
 */
public class WechatEventListeners implements WechatEventListener {
    private List<WechatEventListener> listeners;

    @Override
    public void onSubscribe(EventMsg eventMsg) {
        if (listeners == null) {
            return;
        }
        for (WechatEventListener listener : listeners) {
            listener.onSubscribe(eventMsg);
        }
    }

    @Override
    public void onUnsubscribe(EventMsg eventMsg) {
        if (listeners == null) {
            return;
        }
        for (WechatEventListener listener : listeners) {
            listener.onUnsubscribe(eventMsg);
        }
    }

    @Override
    public void onScan(ScanEventMsg scanEventMsg) {
        if (listeners == null) {
            return;
        }
        for (WechatEventListener listener : listeners) {
            listener.onScan(scanEventMsg);
        }
    }

    @Override
    public void onLocation(LocationEventMsg locationEventMsg) {
        if (listeners == null) {
            return;
        }
        for (WechatEventListener listener : listeners) {
            listener.onLocation(locationEventMsg);
        }
    }

    @Override
    public void onClick(CustomEventMsg customEventMsg) {
        if (listeners == null) {
            return;
        }
        for (WechatEventListener listener : listeners) {
            listener.onClick(customEventMsg);
        }
    }

    @Override
    public void onView(CustomEventMsg customEventMsg) {
        if (listeners == null) {
            return;
        }
        for (WechatEventListener listener : listeners) {
            listener.onView(customEventMsg);
        }
    }

    public void setListeners(List<WechatEventListener> eventListeners) {
        this.listeners = eventListeners;
    }

    public void registListener(WechatEventListener listener) {
        if (this.listeners == null) {
            this.listeners = new ArrayList<>(10);
        }
        listeners.add(listener);
    }
}
