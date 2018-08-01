package ymm.sell.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import ymm.sell.dataproject.ProductInfo;
import ymm.sell.emnums.ProductStatusEnum;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @Author: ymm
 * @Date: 2018/8/1 15:37
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findOne() {
        ProductInfo productInfo = productService.findOne("123").get();
        Assert.assertEquals("123", productInfo.getProductId());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfos = productService.findUpAll();
        Assert.assertNotEquals(0, productInfos.size());
    }

    @Test
    public void findAll() {
        PageRequest page = PageRequest.of(0, 2);
        Page<ProductInfo> productInfos = productService.findAll(page);
        Assert.assertNotEquals(0, productInfos.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo p = new ProductInfo();
        p.setCategoryType(2);
        p.setProductDescription("ok");
        p.setProductIcon("http:xxxx.jpg");
        p.setProductId("111");
        p.setProductName("蛋炒饭");
        p.setProductPrice(new BigDecimal(8.8));
        p.setProductStatus(ProductStatusEnum.DOWN.getCode());
        p.setProductStock(111);

        ProductInfo productInfo = productService.save(p);
        Assert.assertNotEquals(null, productInfo);
    }
}