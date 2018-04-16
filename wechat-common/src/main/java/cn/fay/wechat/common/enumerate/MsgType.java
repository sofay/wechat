package cn.fay.wechat.common.enumerate;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/13 下午1:27.
 */
public enum MsgType {
    EVENT("事件推送消息"), TEXT("普通消息"), IMAGE("图片消息"), VOICE("语音消息"), VIDEO("视频消息"), SHORTVIDEO("小视频消息"), LOCATION("位置消息"), LINK("链接消息");

    private String desc;

    MsgType(String desc) {
        this.desc = desc;
    }

    public static MsgType getMsgType(String type) {
        for (MsgType msgType : values()) {
            if (msgType.name().equalsIgnoreCase(type)) {
                return msgType;
            }
        }
        return null;
    }
}
