package ymm.sell.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ymm.sell.constant.CookieConstant;
import ymm.sell.constant.RedisConstant;
import ymm.sell.execption.SellerAuthorizeException;
import ymm.sell.utils.CookieUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: ymm
 * @Date: 2018/8/16 15:37
 * @Description:
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * ymm.sell.controller.Seller*.*(..))" +
            "&& !execution(public * ymm.sell.controller.SellerUserController.*(..))")
    public void verify() {
    }

    @Before("verify()")
    public void doVerify() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes attributes= (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        //查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if(cookie==null){
            log.warn("【登陆校验】 Cookie中查不到token");
            throw new SellerAuthorizeException();
        }

        //去redis里查
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
        if(StringUtils.isEmpty(tokenValue)){
            log.error("【登陆校验】Redis中查不到token");
            throw new SellerAuthorizeException();
        }
    }
}
