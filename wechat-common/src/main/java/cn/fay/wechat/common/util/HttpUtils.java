package cn.fay.wechat.common.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/12 下午9:16.
 */
public class HttpUtils {
    public static String get(String url) throws IOException {
        HttpClient client = new HttpClient();
        GetMethod get = new GetMethod(url);
        client.executeMethod(get);
        return get.getResponseBodyAsString();
    }
}
