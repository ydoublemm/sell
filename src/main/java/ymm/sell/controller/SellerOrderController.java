package ymm.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ymm.sell.dto.OrderDTO;
import ymm.sell.emnums.ResultEnum;
import ymm.sell.execption.SellException;
import ymm.sell.service.OrderService;

import java.util.Map;

/**
 * @Author: ymm
 * @Date: 2018/8/9 21:17
 * @Description:卖家端订单
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    /*
     * @author: ymm
     * @date: 2018/8/9 21:26
     * @param: [page, size] -> [第几页第一页开始,每页长度]
     * @return: org.springframework.web.servlet.ModelAndView
     * @Description:订单列表
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {

        Page<OrderDTO> orderDTOPage = orderService.findList(PageRequest.of(page - 1, size));
        map.put("orderDTOPage", orderDTOPage);
        map.put("currentPage", page);
        return new ModelAndView("order/list", map);
    }

    /*
     * @author: ymm
     * @date: 2018/8/12 1:20
     * @param: [orderId, map] -> []
     * @return: org.springframework.web.servlet.ModelAndView
     * @Description:   取消订单
     */
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId, Map<String, Object> map) {
        OrderDTO orderDTO = null;
        try {
            orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        } catch (SellException e) {
            log.error("【卖家端取消订单】{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        map.put("msg", ResultEnum.SUCCESS.getMsg());
        map.put("url", "/sell/seller/order/list");

        return new ModelAndView("common/success",map);
    }

    /*
     * @author: ymm
     * @date: 2018/8/12 1:20
     * @param: [orderId, map] -> []
     * @return: org.springframework.web.servlet.ModelAndView
     * @Description:   订单详情
     */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId, Map<String, Object> map){
        OrderDTO orderDTO = null;
        try {
            orderDTO = orderService.findOne(orderId);
        } catch (SellException e) {
            log.error("【卖家端订单详情查询】{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        map.put("orderDTO", orderDTO);
        return new ModelAndView("order/detail",map);
    }

    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId, Map<String, Object> map){
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        } catch (SellException e) {
            log.error("【卖家端完结订单】{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        map.put("msg", ResultEnum.SUCCESS.getMsg());
        map.put("url", "/sell/seller/order/list");

        return new ModelAndView("common/success",map);
    }
}
