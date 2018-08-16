package ymm.sell.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ymm.sell.dataproject.SellerInfo;

import static org.junit.Assert.*;

/**
 * @Author: ymm
 * @Date: 2018/8/15 21:37
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerServiceImplTest {

    @Autowired
    private SellerServiceImpl sellerService;

    @Test
    public void findSellerByOpenid() {
        SellerInfo sellerInfo = sellerService.findSellerByOpenid("123");
        Assert.assertNotNull(sellerInfo);
    }
}