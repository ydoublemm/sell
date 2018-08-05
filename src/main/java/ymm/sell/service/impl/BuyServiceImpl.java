package ymm.sell.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ymm.sell.dto.OrderDTO;
import ymm.sell.emnums.ResultEnum;
import ymm.sell.execption.SellException;
import ymm.sell.service.BuyerService;
import ymm.sell.service.OrderService;

/**
 * @Author: ymm
 * @Date: 2018/8/6 0:39
 * @Description:
 */
@Service
@Slf4j
public class BuyServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(final String openid, final String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if(orderDTO==null){
            log.error("【查询订单】查不到该订单，orderId={}",orderId);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }


    @Override
    public OrderDTO cancalOrder(final String openid, final String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if(orderDTO==null){
            log.error("【取消订单】查不到该订单，orderId={}",orderId);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        orderService.cancel(orderDTO);
        return orderDTO;
    }

    private OrderDTO checkOrderOwner(final String openid, final String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO==null){
            return null;
        }
        //判断订单是不是自己的
        if(!orderDTO.getBuyerOpenid().equals(openid)){
            log.error("【查询订单】订单的openid不一致，opendid={},orderId={}",openid,orderId);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
