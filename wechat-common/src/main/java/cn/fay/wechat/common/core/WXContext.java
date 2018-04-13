package cn.fay.wechat.common.core;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/13 下午4:05.
 */
public interface WXContext {
    /**
     * 返回数据到微信服务器
     * @param msg
     */
    void write(String msg);

    /**
     * 根据key从请求获取参数值 {@link javax.servlet.http.HttpServletRequest#getParameter(String)}
     * @param key
     * @return
     */
    String getProperty(String key);

    /**
     * 获取写入的数据
     * @return
     */
    String getWritedData();
}
