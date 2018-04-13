package cn.fay.wechat.common.enumerate;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/13 下午1:27.
 */
public enum MsgType {
    EVENT("事件推送消息");

    private String desc;

    MsgType(String desc) {
        this.desc = desc;
    }

    public static MsgType getMsgType(String type) {
        for (MsgType msgType : values()) {
            if (msgType.desc.equalsIgnoreCase(type)) {
                return msgType;
            }
        }
        return null;
    }
}
