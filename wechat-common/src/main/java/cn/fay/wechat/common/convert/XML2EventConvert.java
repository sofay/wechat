package cn.fay.wechat.common.convert;

import cn.fay.wechat.common.entity.EventMsg;
import cn.fay.wechat.common.util.CommonUtils;
import org.w3c.dom.Document;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/13 下午2:59.
 */
public class XML2EventConvert implements Convert<String, EventMsg> {
    @Override
    public EventMsg convert(String s) {
        Document document = CommonUtils.extractDocument(s);

        return null;
    }
}
