package ymm.sell.repositoty;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import ymm.sell.dataproject.OrderMaster;
import ymm.sell.emnums.OrderStatusEnum;
import ymm.sell.emnums.PayStatusEnum;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @Author: ymm
 * @Date: 2018/8/2 16:29
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;


    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerAddress("浙江宁波");
        orderMaster.setBuyerName("王一凡");
        orderMaster.setBuyerOpenid("qqq");
        orderMaster.setBuyerPhone("17857025069");
        orderMaster.setOrderAmount(new BigDecimal(11.11));
        orderMaster.setOrderId("987651");

        OrderMaster save = repository.save(orderMaster);
        Assert.assertNotNull(save);

    }


    @Test
    public void findByBuyerOpenid() {
        Page<OrderMaster> orderMasters = repository.findByBuyerOpenid("aaa", PageRequest.of(0, 2));
        Assert.assertNotEquals(0, orderMasters.getTotalElements());
    }
}