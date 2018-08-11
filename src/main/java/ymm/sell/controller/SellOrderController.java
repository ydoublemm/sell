package ymm.sell.controller;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sun.awt.SunHints;
import ymm.sell.dataproject.OrderMaster;
import ymm.sell.dto.OrderDTO;
import ymm.sell.service.OrderService;

import java.util.Map;

/**
 * @Author: ymm
 * @Date: 2018/8/9 21:17
 * @Description:卖家端订单
 */
@Controller
@RequestMapping("/seller/order")
public class SellOrderController {

    @Autowired
    private OrderService orderService;

    /*
     * @author: ymm
     * @date: 2018/8/9 21:26
     * @param: [page, size] -> [第几页第一页开始,每页长度]
     * @return: org.springframework.web.servlet.ModelAndView
     * @Description:
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {

        Page<OrderDTO> orderDTOPage = orderService.findList(PageRequest.of(page - 1, size));
        map.put("orderDTOPage",orderDTOPage );
        map.put("currentPage", page);
        return new ModelAndView("order/list", map);
    }
}
