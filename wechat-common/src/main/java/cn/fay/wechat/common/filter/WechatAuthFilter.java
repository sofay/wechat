package cn.fay.wechat.common.filter;

import cn.fay.wechat.common.util.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/16 下午12:19.
 */
public class WechatAuthFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(WechatAuthFilter.class);
    private final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    @Value("${wechat.config.token}")
    private String token;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String signature = request.getParameter("signature");
        String echostr = request.getParameter("echostr");
        if (signature != null && echostr != null) {
            LOGGER.info("wechat auth filter signature:{}", signature);
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            LOGGER.info("wechat auth filter received:timestamp={}, nonce={}, echostr={}", timestamp, nonce, echostr);
            String[] arr = new String[]{timestamp, nonce, token};
            Arrays.sort(arr);
            StringBuilder str = new StringBuilder();
            for (String temp : arr) {
                str.append(temp);
            }
            LOGGER.info("wechat auth filter str:{}", str.toString());
            try {
                MessageDigest digest = MessageDigest.getInstance("sha1");
                byte[] bytes = digest.digest(str.toString().getBytes(AppConstants.APP_ENCODING_NAME));
                String encodeStr = getFormattedText(bytes);
                LOGGER.info("wechat auth filter encode str:{}", encodeStr);
                if (encodeStr.equals(signature)) {
                    HttpServletResponse response = (HttpServletResponse) servletResponse;
                    response.getWriter().write(echostr);
                    return;
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }


    private String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }
}
