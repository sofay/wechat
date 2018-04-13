package cn.fay.assistant.test;

import cn.fay.wechat.common.util.AppConstants;
import cn.fay.wechat.common.util.CommonUtils;
import org.junit.Test;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/13 下午12:57.
 */
public class JavaTest {

    @Test
    public void test() {
        String xml = "<xml><ToUserName>< ![CDATA[toUser] ]></ToUserName><FromUserName>< ![CDATA[FromUser] ]></FromUserName><CreateTime>123456789</CreateTime><MsgType>< ![CDATA[event] ]></MsgType><Event>< ![CDATA[subscribe] ]></Event></xml>".replaceAll(" ", "");
        System.out.println(CommonUtils.parseXMLValue(xml, AppConstants.XML_FROMUSERNAME_TAG_NAME));
        System.out.println(CommonUtils.parseXMLValue(xml, AppConstants.XML_TOUSERNAME_TAG_NAME));
        System.out.println(CommonUtils.parseXMLValue(xml, AppConstants.XML_CREATETIME_TAG_NAME));
        System.out.println(CommonUtils.parseXMLValue(xml, AppConstants.XML_MSGTYPE_TAG_NAME));
        System.out.println(CommonUtils.parseXMLValue(xml, AppConstants.XML_EVENT_TAG_NAME));
        System.out.println(CommonUtils.escapeFromCDATA("<    ![CDATA[FromUser]]"));
    }

    @Test
    public void te() {
        StringBuilder sb = new StringBuilder();
        System.out.println(sb.toString().equals(""));
    }
}
