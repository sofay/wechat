package cn.fay.wechat.common.entity;

import cn.fay.wechat.common.enumerate.MsgType;
import cn.fay.wechat.common.enumerate.WechatEvent;

import java.util.Date;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/13 下午1:26.
 */
public class EventMsg {
    private String toUserName;
    private String fromUserName;
    private Date createTime;
    private MsgType msgType;
    private WechatEvent event;
}
