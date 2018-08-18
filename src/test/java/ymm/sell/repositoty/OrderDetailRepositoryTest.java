package ymm.sell.repositoty;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ymm.sell.dataproject.OrderDetail;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: ymm
 * @Date: 2018/8/2 18:15
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("112");
        orderDetail.setOrderId("98765");
        orderDetail.setProductIcon("http://xxxx.jpg");
        orderDetail.setProductId("222");
        orderDetail.setProductName("宫保鸡丁");
        orderDetail.setProductPrice(new BigDecimal(5.6));
        orderDetail.setProductQuantity(2);


        OrderDetail save = repository.save(orderDetail);
        Assert.assertNotNull(save);
    }

    @Test
    public void findByOrOrderId() {
        List<OrderDetail> orderDetailList = repository.findByOrOrderId("1533972016794529805");
        Assert.assertNotEquals(0, orderDetailList.size());
    }
}