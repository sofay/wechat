package cn.fay.assistant.wechat.handler;

import cn.fay.wechat.common.adapt.WechatEventHandlerAdapt;
import cn.fay.wechat.common.annotation.Listener;
import cn.fay.wechat.common.core.WXContext;
import cn.fay.wechat.common.entity.CustomEventMsg;
import cn.fay.wechat.common.entity.EventMsg;
import cn.fay.wechat.common.entity.LocationEventMsg;
import cn.fay.wechat.common.entity.ScanEventMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/13 下午2:21.
 */
@Listener
public class LogWechatEventHandler extends WechatEventHandlerAdapt {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogWechatEventHandler.class);

    @Override
    public void onSubscribe(EventMsg eventMsg, WXContext context) {
        super.onSubscribe(eventMsg, context);
        LOGGER.info("received event: {}, fromUserName: {}", eventMsg.getEventType(), eventMsg.getFromUserName());
    }

    @Override
    public void onUnsubscribe(EventMsg eventMsg, WXContext context) {
        super.onUnsubscribe(eventMsg, context);
        LOGGER.info("received event: {}, fromUserName: {}", eventMsg.getEventType(), eventMsg.getFromUserName());
    }

    @Override
    public void onScan(ScanEventMsg scanEventMsg, WXContext context) {
        super.onScan(scanEventMsg, context);
        LOGGER.info("received event: {}, fromUserName: {}", scanEventMsg.getEventType(), scanEventMsg.getFromUserName());
    }

    @Override
    public void onLocation(LocationEventMsg locationEventMsg, WXContext context) {
        super.onLocation(locationEventMsg, context);
        LOGGER.info("received event: {}, fromUserName: {}", locationEventMsg.getEventType(), locationEventMsg.getFromUserName());
    }

    @Override
    public void onClick(CustomEventMsg customEventMsg, WXContext context) {
        super.onClick(customEventMsg, context);
        LOGGER.info("received event: {}, fromUserName: {}", customEventMsg.getEventType(), customEventMsg.getFromUserName());
    }

    @Override
    public void onView(CustomEventMsg customEventMsg, WXContext context) {
        super.onView(customEventMsg, context);
        LOGGER.info("received event: {}, fromUserName: {}", customEventMsg.getEventType(), customEventMsg.getFromUserName());
    }
}
