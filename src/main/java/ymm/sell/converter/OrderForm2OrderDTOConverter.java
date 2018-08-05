package ymm.sell.converter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import ymm.sell.dataproject.OrderDetail;
import ymm.sell.dto.OrderDTO;
import ymm.sell.emnums.ResultEnum;
import ymm.sell.execption.SellException;
import ymm.sell.form.OrderForm;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ymm
 * @Date: 2018/8/4 23:30
 * @Description:
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();
        List<OrderDetail> orderDetailList = new ArrayList<>();
        //json转list
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (JsonSyntaxException e) {
            log.error("【对象转换】错误， String={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }


        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
