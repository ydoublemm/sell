package ymm.sell.controller;

import org.hibernate.type.StringRepresentableType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ymm.sell.constant.CookieConstant;
import ymm.sell.constant.RedisConstant;
import ymm.sell.dataproject.SellerInfo;
import ymm.sell.emnums.ResultEnum;
import ymm.sell.service.SellerService;
import ymm.sell.utils.CookieUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author: ymm
 * @Date: 2018/8/16 13:37
 * @Description: 卖家用户相关的操作
 */
@Controller
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;



    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                              HttpServletResponse response,
                              Map<String,Object> map) {
        //1.openid去和数据库里的数据匹配
        SellerInfo sellerInfo = sellerService.findSellerByOpenid(openid);
        if(sellerInfo==null){
            map.put("msg", ResultEnum.LOGIN_FAIL.getMsg());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        //2设置token到redis
        String token=UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token), openid,expire,TimeUnit.SECONDS);

        //3.设置token到cokie
        CookieUtil.set(response, CookieConstant.TOKEN, token, expire);
        return new ModelAndView("redirect:/seller/product/list");

    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Map<String,Object> map) {
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if(cookie!=null){
            //在redis中删除
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX,cookie));
            //清除cookie
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }
        map.put("msg",ResultEnum.LOGOUT_SUCCESS.getMsg());
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }
}
