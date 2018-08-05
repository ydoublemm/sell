package ymm.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ymm.sell.VO.ResultVO;
import ymm.sell.converter.OrderForm2OrderDTOConverter;
import ymm.sell.dto.OrderDTO;
import ymm.sell.emnums.ResultEnum;
import ymm.sell.execption.SellException;
import ymm.sell.form.OrderForm;
import ymm.sell.service.BuyerService;
import ymm.sell.service.OrderService;
import ymm.sell.utils.ResultVOUtil;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ymm
 * @Date: 2018/8/4 0:31
 * @Description:
 */
@RestController
@RequestMapping(value = "/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping(value = "/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】 参数不正确，orderForm", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.cart_empty);
        }
        OrderDTO createResult = orderService.create(orderDTO);

        //创建map
        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultVOUtil.success(map);


    }


    //订单列表
    @GetMapping(value = "/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam(value = "openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (openid == null) {
            log.error("【查询订单列表】 openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, PageRequest.of(page, size));

        return ResultVOUtil.success(orderDTOPage.getContent());

    }


    //订单详情
    @GetMapping(value = "/detail")
    public ResultVO<OrderDTO> detail(@RequestParam(value = "openid") String openid,
                                     @RequestParam(value = "orderId") String orderId) {

        if (StringUtils.isEmpty(openid) || StringUtils.isEmpty(orderId)) {
            log.error("【查询订单列表】 openid,orderId", openid, orderId);
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        return ResultVOUtil.success(orderDTO);
    }


    //取消订单
    @PostMapping(value = "/cancel")
    public ResultVO cancel(@RequestParam(value = "openid") String openid,
                           @RequestParam(value = "orderId") String orderId) {

        if (StringUtils.isEmpty(openid) || StringUtils.isEmpty(orderId)) {
            log.error("【查询订单列表】 openid,orderId", openid, orderId);
            throw new SellException(ResultEnum.PARAM_ERROR);
        }


        OrderDTO orderDTO = buyerService.cancalOrder(openid, orderId);
        return ResultVOUtil.success();
    }
}
