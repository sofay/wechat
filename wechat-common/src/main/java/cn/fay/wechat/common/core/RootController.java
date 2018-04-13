package cn.fay.wechat.common.core;

import cn.fay.wechat.common.convert.XML2EventConvert;
import cn.fay.wechat.common.entity.EventMsg;
import cn.fay.wechat.common.enumerate.MsgType;
import cn.fay.wechat.common.listener.WechatEventHandlers;
import cn.fay.wechat.common.util.AppConstants;
import cn.fay.wechat.common.util.CommonUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/13 上午10:07.
 */
@RestController
public class RootController {
    private WechatEventHandlers wechatEventHandlers;
    private XML2EventConvert convert = new XML2EventConvert();

    /**
     * 微信推送统一入口
     *
     * @return
     */
    @RequestMapping("/${wechat.root.path:/wechat/root}")
    public String root(HttpServletRequest request) {
        String body = request.getParameter("body");
        MsgType msgType = MsgType.getMsgType(CommonUtils.parseXMLValue(body, AppConstants.XML_MSGTYPE_TAG_NAME));
        WXDefaultWXContext context = new WXDefaultWXContext();
        if (msgType != null) {
            switch (msgType) {
                /*
                推送事件处理
                 */
                case EVENT:
                    if (wechatEventHandlers != null) {
                        EventMsg eventMsg = convert.convert(body);
                        context.setRequest(request);
                        context.setWxMsg(eventMsg);
                        wechatEventHandlers.doHandler(eventMsg, context);
                        return context.getWritedData();
                    }
                    break;
                default:
            }
        }
        return "can not handle now, please call fay";
    }

    public void setWechatEventHandlers(WechatEventHandlers wechatEventHandlers) {
        this.wechatEventHandlers = wechatEventHandlers;
    }
}
