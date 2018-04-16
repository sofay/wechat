package cn.fay.wechat.common.filter;

import cn.fay.wechat.common.util.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String signature = request.getParameter("signature");
        if (signature != null && !"".equals(signature)) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String echostr = request.getParameter("echostr");
            LOGGER.info("wechat auth filter received:timestamp={}, nonce={}, echostr={}", timestamp, nonce, echostr);
            String[] arr = new String[]{timestamp, nonce, echostr};
            Arrays.sort(arr);
            String str = "";
            for (String temp : arr) {
                str += temp;
            }
            LOGGER.info("wechat auth filter str:", str);
            try {
                MessageDigest digest = MessageDigest.getInstance("sha1");
                byte[] bytes = digest.digest(str.getBytes(AppConstants.APP_ENCODING_NAME));
                String encodeStr = new String(bytes, AppConstants.APP_ENCODING_NAME);
                LOGGER.info("wechat auth filter encode str:", encodeStr);
                if (encodeStr.equals(signature)) {
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
}
