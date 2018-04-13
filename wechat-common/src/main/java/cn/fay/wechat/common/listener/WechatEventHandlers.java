package cn.fay.wechat.common.listener;

import cn.fay.wechat.common.core.WXContext;
import cn.fay.wechat.common.entity.CustomEventMsg;
import cn.fay.wechat.common.entity.EventMsg;
import cn.fay.wechat.common.entity.LocationEventMsg;
import cn.fay.wechat.common.entity.ScanEventMsg;
import cn.fay.wechat.common.enumerate.WechatEventType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/13 下午1:38.
 * 注意类名不要改 {@link cn.fay.wechat.common.beanfactorypostprocessor.DetectWechatEventListenerBeanFactoryPostProcessor}
 */
public class WechatEventHandlers implements WechatEventHandler {
    private List<WechatEventHandler> listeners;

    @Override
    public void onSubscribe(EventMsg eventMsg, WXContext context) {
        if (listeners == null) {
            return;
        }
        for (WechatEventHandler listener : listeners) {
            listener.onSubscribe(eventMsg, context);
        }
    }

    @Override
    public void onUnsubscribe(EventMsg eventMsg, WXContext context) {
        if (listeners == null) {
            return;
        }
        for (WechatEventHandler listener : listeners) {
            listener.onUnsubscribe(eventMsg, context);
        }
    }

    @Override
    public void onScan(ScanEventMsg scanEventMsg, WXContext context) {
        if (listeners == null) {
            return;
        }
        for (WechatEventHandler listener : listeners) {
            listener.onScan(scanEventMsg, context);
        }
    }

    @Override
    public void onLocation(LocationEventMsg locationEventMsg, WXContext context) {
        if (listeners == null) {
            return;
        }
        for (WechatEventHandler listener : listeners) {
            listener.onLocation(locationEventMsg, context);
        }
    }

    @Override
    public void onClick(CustomEventMsg customEventMsg, WXContext context) {
        if (listeners == null) {
            return;
        }
        for (WechatEventHandler listener : listeners) {
            listener.onClick(customEventMsg, context);
        }
    }

    @Override
    public void onView(CustomEventMsg customEventMsg, WXContext context) {
        if (listeners == null) {
            return;
        }
        for (WechatEventHandler listener : listeners) {
            listener.onView(customEventMsg, context);
        }
    }

    public void doHandler(EventMsg eventMsg, WXContext context) {
        WechatEventType eventType = eventMsg.getEventType();
        if (eventType == null) {
            return;
        }
        switch (eventType) {
            case SUBSCRIBE:
                onSubscribe(eventMsg, context);
                break;
            case UNSUBSCRIBE:
                onUnsubscribe(eventMsg, context);
                break;
            case SCAN:
                onScan((ScanEventMsg) eventMsg, context);
                break;
            case LOCATION:
                onLocation((LocationEventMsg) eventMsg, context);
                break;
            case CLICK:
                onClick((CustomEventMsg) eventMsg, context);
            case VIEW:
                onView((CustomEventMsg) eventMsg, context);
                break;
            default:
        }
    }

    public void setListeners(List<WechatEventHandler> eventListeners) {
        this.listeners = eventListeners;
    }

    public void registListener(WechatEventHandler listener) {
        if (this.listeners == null) {
            this.listeners = new ArrayList<>(10);
        }
        listeners.add(listener);
    }
}
