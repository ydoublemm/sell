package ymm.sell.repositoty;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ymm.sell.dataproject.ProductInfo;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: ymm
 * @Date: 2018/8/1 15:18
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;


    @Test
    public void saveTest(){
        ProductInfo p = new ProductInfo();
        p.setCategoryType(2);
        p.setProductDescription("ok");
        p.setProductIcon("http:xxxx.jpg");
        p.setProductId("123");
        p.setProductName("皮蛋粥");
        p.setProductPrice(new BigDecimal(3.2));
        p.setProductStatus(0);
        p.setProductStock(123);

        repository.save(p);
    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> productInfoList = repository.findByProductStatus(0);
        Assert.assertNotEquals(0, productInfoList.size());

    }
}