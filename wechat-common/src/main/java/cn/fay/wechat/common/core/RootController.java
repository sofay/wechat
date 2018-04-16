package cn.fay.wechat.common.core;

import cn.fay.wechat.common.convert.XML2EventConvert;
import cn.fay.wechat.common.entity.EventMsg;
import cn.fay.wechat.common.enumerate.MsgType;
import cn.fay.wechat.common.handler.WechatEventHandlers;
import cn.fay.wechat.common.util.AppConstants;
import cn.fay.wechat.common.util.CommonUtils;
import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/13 上午10:07.
 */
@RestController
public class RootController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RootController.class);
    private WechatEventHandlers wechatEventHandlers;
    private XML2EventConvert convert = new XML2EventConvert();

    /**
     * 微信推送统一入口
     *
     * @return
     */
    @RequestMapping("/${wechat.root.path:/wechat/root}")
    public String root(HttpServletRequest request) throws IOException {
        String xmlSource = IOUtils.toString(request.getInputStream(), AppConstants.APP_ENCODING_NAME);
        LOGGER.info("ROOT CONTROLLER received xmlSource:{}", xmlSource);
        if (xmlSource != null && !"".equals(xmlSource)) {
            MsgType msgType = MsgType.getMsgType(CommonUtils.parseXMLValue(xmlSource, AppConstants.XML_MSGTYPE_TAG_NAME));
            WXDefaultWXContext context = new WXDefaultWXContext();
            if (msgType != null) {
                switch (msgType) {
                /*
                推送事件处理
                 */
                    case EVENT:
                        if (wechatEventHandlers != null) {
                            EventMsg eventMsg = convert.convert(xmlSource);
                            context.setRequest(request);
                            context.setWxMsg(eventMsg);
                            wechatEventHandlers.doHandler(eventMsg, context);
                            return context.getWritedData();
                        }
                        break;
                    default:
                }
            }
        }
        return "can not handle now, please call fay";
    }

    public void setWechatEventHandlers(WechatEventHandlers wechatEventHandlers) {
        this.wechatEventHandlers = wechatEventHandlers;
    }
}
