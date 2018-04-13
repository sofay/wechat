package cn.fay.wechat.common.enumerate;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/13 下午1:13.
 */
public enum WechatEventType {
    SUBSCRIBE("关注"), UNSUBSCRIBE("取消关注"), SCAN("扫描带参数二维码"), LOCATION("上报地理位置"), CLICK("点击菜单拉取消息"), VIEW("点击菜单跳转链接");

    private String desc;

    WechatEventType(String desc) {
        this.desc = desc;
    }

    public static WechatEventType getWechatEvent(String type) {
        for (WechatEventType event : values()) {
            if (event.name().equalsIgnoreCase(type)) {
                return event;
            }
        }
        return null;
    }
}
