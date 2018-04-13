package cn.fay.wechat.common.util;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/12 下午7:45.
 * 常量类
 */
public class AppConstants {
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    public static final String ACCESS_TOKEN_KEY = "access_token";
    public static final String XML_TOUSERNAME_TAG_NAME = "ToUserName";
    public static final String XML_FROMUSERNAME_TAG_NAME = "FromUserName";
    public static final String XML_CREATETIME_TAG_NAME = "CreateTime";
    public static final String XML_MSGTYPE_TAG_NAME = "MsgType";
    public static final String XML_EVENT_TAG_NAME = "Event";
}
