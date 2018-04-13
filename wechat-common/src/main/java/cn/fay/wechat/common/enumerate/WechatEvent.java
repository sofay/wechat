package cn.fay.wechat.common.enumerate;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/13 下午1:13.
 */
public enum WechatEvent {
    SUBSCRIBE("关注"), UNSUBSCRIBE("取消关注"), SCAN("扫描带参数二维码"), LOCATION("上报地理位置"), CLICK("点击菜单拉取消息"), VIEW("点击菜单跳转链接");

    private String desc;

    WechatEvent(String desc) {
        this.desc = desc;
    }

    public static WechatEvent getWechatEvent(String data) {
        for (WechatEvent event : values()) {
            if (event.desc.equalsIgnoreCase(data)) {
                return event;
            }
        }
        return null;
    }
}
