package cn.fay.wechat.common.convert;

import cn.fay.wechat.common.entity.CustomEventMsg;
import cn.fay.wechat.common.entity.EventMsg;
import cn.fay.wechat.common.entity.LocationEventMsg;
import cn.fay.wechat.common.entity.ScanEventMsg;
import cn.fay.wechat.common.enumerate.WechatEventType;
import cn.fay.wechat.common.util.AppConstants;
import cn.fay.wechat.common.util.CommonUtils;
import org.w3c.dom.Document;

import java.util.Date;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/13 下午2:59.
 */
@SuppressWarnings("all")
public class XML2EventConvert implements Convert<String, EventMsg> {
    @Override
    public EventMsg convert(String s) {
        Document document = CommonUtils.extractDocument(s);
        String event = CommonUtils.parseXMLValue(document, AppConstants.XML_EVENT_TAG_NAME);
        WechatEventType wechatEventType = WechatEventType.getWechatEvent(event);
        if (wechatEventType == null) {
            return null;
        }
        EventMsg eventMsg;
        switch (wechatEventType) {
            case SUBSCRIBE:
            case UNSUBSCRIBE:
                eventMsg = new EventMsg();
                break;
            case SCAN:
                ScanEventMsg scanEventMsg = new ScanEventMsg();
                scanEventMsg.setEventKey(CommonUtils.parseXMLValue(document, AppConstants.XML_EVENT_TAG_NAME));
                scanEventMsg.setTicket(CommonUtils.parseXMLValue(document, AppConstants.XML_TICKET_TAG_NAME));
                eventMsg = scanEventMsg;
                break;
            case LOCATION:
                LocationEventMsg locationEventMsg = new LocationEventMsg();
                locationEventMsg.setLatitude(Double.parseDouble(CommonUtils.parseXMLValue(document, AppConstants.XML_LATITUDE_TAG_NAME)));
                locationEventMsg.setLongitude(Double.parseDouble(CommonUtils.parseXMLValue(document, AppConstants.XML_LONGITUDE_TAG_NAME)));
                locationEventMsg.setPrecision(Double.parseDouble(CommonUtils.parseXMLValue(document, AppConstants.XML_PRECISION_TAG_NAME)));
                eventMsg = locationEventMsg;
                break;
            case CLICK:
            case VIEW:
                CustomEventMsg customEventMsg = new CustomEventMsg();
                customEventMsg.setEventKey(CommonUtils.parseXMLValue(document, AppConstants.XML_EVENT_KEY_TAG_NAME));
                eventMsg = customEventMsg;
                break;
            default:
                eventMsg = null;
        }
        /*
        配置公众的属性
         */
        eventMsg.setCreateTime(new Date(Long.parseLong(CommonUtils.parseXMLValue(document, AppConstants.XML_CREATETIME_TAG_NAME))));
        eventMsg.setToUserName(CommonUtils.parseXMLValue(document, AppConstants.XML_TOUSERNAME_TAG_NAME));
        eventMsg.setFromUserName(CommonUtils.parseXMLValue(document, AppConstants.XML_FROMUSERNAME_TAG_NAME));
        eventMsg.setEventType(wechatEventType);
        return eventMsg;
    }
}
