package ymm.sell.service;

import ymm.sell.dto.OrderDTO;

/**
 * @Author: ymm
 * @Date: 2018/8/6 0:39
 * @Description:
 */
public interface BuyerService {

    //查询一个订单
    OrderDTO findOrderOne(String openid,String orderId);

    //取消订单
    OrderDTO cancalOrder(String openid,String orderId);
}
