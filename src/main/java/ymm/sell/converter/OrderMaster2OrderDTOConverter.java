package ymm.sell.converter;

import org.springframework.beans.BeanUtils;
import ymm.sell.dataproject.OrderMaster;
import ymm.sell.dto.OrderDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ymm
 * @Date: 2018/8/3 10:55
 * @Description:
 */
public class OrderMaster2OrderDTOConverter {

    public static OrderDTO convert(OrderMaster orderMaster) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (OrderMaster orderMaster : orderMasterList) {
            orderDTOList.add(convert(orderMaster));
        }
        return orderDTOList;
    }
}
