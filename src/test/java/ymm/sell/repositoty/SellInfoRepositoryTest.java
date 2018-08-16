package ymm.sell.repositoty;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ymm.sell.dataproject.SellerInfo;
import ymm.sell.utils.KeyUtil;

/**
 * @Author: ymm
 * @Date: 2018/8/15 21:10
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellInfoRepositoryTest {

    @Autowired
    private SellInfoRepository repository;

    @Test
    public void findByOpenid() {
        SellerInfo sellerInfo = repository.findByOpenid("123");
        Assert.assertNotNull(sellerInfo);
    }

    @Test
    public  void save(){
        SellerInfo sellInfo = new SellerInfo();
        sellInfo.setOpenid("123");
        sellInfo.setPassword("123");
        sellInfo.setUsername("123");
        sellInfo.setSellerId(KeyUtil.getUniqueKey());
        repository.save(sellInfo);
    }
}