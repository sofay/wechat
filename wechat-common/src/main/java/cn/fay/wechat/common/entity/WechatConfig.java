package cn.fay.wechat.common.entity;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/12 下午8:05.
 */
public class WechatConfig {
    private String appid;
    private String appsecret;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    @Override
    public String toString() {
        return "WechatConfig{" +
                "appid='" + appid + '\'' +
                ", appsecret='" + appsecret + '\'' +
                '}';
    }
}
