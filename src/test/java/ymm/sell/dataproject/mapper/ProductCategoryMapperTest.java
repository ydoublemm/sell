package ymm.sell.dataproject.mapper;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Proc;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ymm.sell.dataproject.ProductCategory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @Author: ymm
 * @Date: 2018/8/17 14:45
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMap() {
        Map<String,Object> map = new HashMap<>();
        map.put("category_name","女生最爱1" );
        map.put("category_type", 5);
        int i = mapper.insertByMap(map);
        Assert.assertEquals(1L,  i);
    }


    @Test
    public void insertByObject() {
        ProductCategory productCategory = new ProductCategory("女生最爱2", 6);
        mapper.insertByObject(productCategory);
    }

    @Test
    public void findByCategoryType() {
        ProductCategory productCategory = mapper.findByCategoryType(333);
        Assert.assertNotNull(productCategory);
    }


    @Test
    public void findByCategoryName() {
        List<ProductCategory> categoryList = mapper.findByCategoryName("女生最爱");
        Assert.assertEquals(2L, categoryList.size());
    }


    @Test
    public void updateCategory(){
        mapper.updateCategory(444, "你好");
    }

    @Test
    public void updateCategory2(){
        ProductCategory productCategory = new ProductCategory("我最爱", 444);
        mapper.updateCategory2(productCategory);
    }

    @Test
    public void selectByCategoryType(){
        ProductCategory productCategory = mapper.selectByCategoryType(444);
        Assert.assertNotNull(productCategory);

    }
}