package ymm.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ymm.sell.dto.OrderDTO;
import ymm.sell.service.OrderService;

/**
 * @Author: ymm
 * @Date: 2018/8/9 12:41
 * @Description:
 */
@Controller
@RequestMapping(value = "/pay")
@Slf4j
public class PayController {
    @Autowired
    private OrderService orderService;


    @GetMapping("/create")
    public void create(@RequestParam("orderId") String orderId,
                       @RequestParam("returnUrl") String retuenUrl) {
        //1.查看订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO==null){
            log.error("【支付订单】,订单不存在，orderId",orderId);
        }

        //发起支付

    }
}
