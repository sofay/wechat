package cn.fay.wechat.common.util;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/12 下午8:03.
 */
public class CommonUtils {

    public static String getAccessToken(String appid, String appsecret) {
        String accessTokenUrl = String.format(AppConstants.ACCESS_TOKEN_URL, appid, appsecret);
        try {
            String response = HttpUtils.get(accessTokenUrl);
            JSONObject json = JSONObject.parseObject(response);
            if (json.containsKey(AppConstants.ACCESS_TOKEN_KEY)) {
                return json.getString(AppConstants.ACCESS_TOKEN_KEY);
            }
            throw new RuntimeException("获取access_token时出错:" + response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
