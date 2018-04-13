package cn.fay.wechat.common.entity;

import java.util.Date;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/13 下午4:56.
 */
public abstract class WXMsg {
    private String toUserName;
    private String fromUserName;
    private Date createTime;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
