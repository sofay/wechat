package cn.fay.wechat.common.core;

import cn.fay.wechat.common.entity.WXMsg;

import javax.servlet.http.HttpServletRequest;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/13 下午4:04.
 */
public class WXDefaultWXContext implements WXContext {
    private HttpServletRequest request;
    private StringBuilder sb = new StringBuilder();
    private WXMsg wxMsg;

    @Override
    public void write(String msg) {
        writeCheck();
        sb.append(msg);
    }

    @Override
    public String getProperty(String key) {
        return request.getParameter(key);
    }

    @Override
    public String getWritedData() {
        return sb.toString();
    }

    private void writeCheck() {
        if (sb.length() > 0) {
            throw new RuntimeException("已经写入过数据:" + sb.toString());
        }
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public WXMsg getWxMsg() {
        return wxMsg;
    }

    public void setWxMsg(WXMsg wxMsg) {
        this.wxMsg = wxMsg;
    }
}
