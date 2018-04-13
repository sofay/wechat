package cn.fay.wechat.common.entity;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/13 下午1:30.
 */
public class ScanEventMsg extends EventMsg {
    private String eventKey;
    private String ticket;

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
