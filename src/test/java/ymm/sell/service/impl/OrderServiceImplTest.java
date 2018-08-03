package ymm.sell.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import ymm.sell.dataproject.OrderDetail;
import ymm.sell.dto.OrderDTO;
import ymm.sell.service.OrderService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: ymm
 * @Date: 2018/8/3 0:23
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPENID="1101110";
    private final String ORDERID="1533311321971549750";

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress("万里学院");
        orderDTO.setBuyerName("张三");
        orderDTO.setBuyerPhone("17857025069");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        List<OrderDetail> orderDetailList = new ArrayList<>();


        OrderDetail o1 = new OrderDetail();
        o1.setProductId("111");
        o1.setProductQuantity(10);
        orderDetailList.add(o1);
        orderDTO.setOrderDetailList(orderDetailList);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("123");
        o2.setProductQuantity(11);
        orderDetailList.add(o2);
        orderDTO.setOrderDetailList(orderDetailList);

        orderService.create(orderDTO);

    }

    @Test
    public void findOne() {
        OrderDTO orderDTO = orderService.findOne(ORDERID);
        Assert.assertNotNull(orderDTO);
    }

    @Test
    public void findList() {
        Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPENID, PageRequest.of(0, 2));
        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findOne(ORDERID);
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertNotNull(result);
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.findOne(ORDERID);
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertNotNull(result);
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderService.findOne(ORDERID);
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertNotNull(result);
    }
}