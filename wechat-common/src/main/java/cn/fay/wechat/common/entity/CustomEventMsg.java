package cn.fay.wechat.common.entity;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/13 下午1:32.
 */
public class CustomEventMsg extends EventMsg {
    /**
     * 事件KEY值
     */
    private String eventKey;

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }
}
