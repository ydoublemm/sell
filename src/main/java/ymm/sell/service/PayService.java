package ymm.sell.service;

import ymm.sell.dto.OrderDTO;

/**
 * @Author: ymm
 * @Date: 2018/8/9 12:46
 * @Description:
 */
public interface PayService {

    void create(OrderDTO orderDTO);
}
