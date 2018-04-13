package cn.fay.wechat.common.entity;

import cn.fay.wechat.common.enumerate.MsgType;
import cn.fay.wechat.common.enumerate.WechatEventType;


/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/13 下午1:26.
 */
public class EventMsg extends WXMsg {
    private MsgType msgType = MsgType.EVENT;
    private WechatEventType eventType;

    public MsgType getMsgType() {
        return msgType;
    }

    public WechatEventType getEventType() {
        return eventType;
    }

    public void setEventType(WechatEventType eventType) {
        this.eventType = eventType;
    }
}
