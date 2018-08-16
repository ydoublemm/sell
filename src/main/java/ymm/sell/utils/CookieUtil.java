package ymm.sell.utils;

import ymm.sell.constant.RedisConstant;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: ymm
 * @Date: 2018/8/16 14:41
 * @Description: cookie 工具类
 */
public class CookieUtil {

    /*
     * @author: ymm
     * @date: 2018/8/16 14:44
     * @param: [response, name, value, maxAge] -> []
     * @return: void
     * @Description:   设置cookie
     */
    public static void set(HttpServletResponse response,
                           String name,
                           String value,
                           int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /*
     * @author: ymm
     * @date: 2018/8/16 15:21
     * @param: [request, response] -> []
     * @return: void
     * @Description:   获取cookie
     */
    public static Cookie get(HttpServletRequest request,
                             String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }
}