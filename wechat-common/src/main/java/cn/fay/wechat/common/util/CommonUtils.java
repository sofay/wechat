package cn.fay.wechat.common.util;

import com.alibaba.fastjson.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/12 下午8:03.
 */
public class CommonUtils {
    private static final Pattern CDATA_PATTERN = Pattern.compile("<[ ]*!\\[CDATA\\[([\\w]+)\\][ ]*\\]");

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

    /**
     * 根据key从xml里面取出对应node的值
     * @param xmlSource
     * @param key
     * @return
     */
    public static String parseXMLValue(String xmlSource, String key) {
        Document document = extractDocument(xmlSource);
        return parseXMLValue(document, key);
    }

    public static String parseXMLValue(Document document, String key) {
        NodeList nodeList = document.getElementsByTagName(key);
        int length = nodeList.getLength();
        if (length > 0) {
            Node node = nodeList.item(0);
            return escapeFromCDATA(node.getTextContent());
        }
        return null;
    }


    public static Document extractDocument(String xmlSource) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            return builder.parse(new ByteArrayInputStream(xmlSource.getBytes("utf-8")));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException("解析xml时出错:", e);
        }
    }

    public static String escapeFromCDATA(String data) {
        if (data == null) {
            return null;
        }
        Matcher matcher = CDATA_PATTERN.matcher(data);
        return matcher.find() ? matcher.group(1) : data;
    }
}
