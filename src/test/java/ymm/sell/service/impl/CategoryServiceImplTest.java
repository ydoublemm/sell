package ymm.sell.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ymm.sell.dataproject.ProductCategory;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @Author: ymm
 * @Date: 2018/8/1 13:42
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void findOne() {
        Optional<ProductCategory> productCategory = categoryService.findOne(1);
        ProductCategory category = productCategory.get();
        Assert.assertEquals(new Integer(1), category.getCategoryId());

    }

    @Test
    public void findAll() {
        List<ProductCategory> productCategoryList = categoryService.findAll();
        Assert.assertNotEquals(0, productCategoryList.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> list =Arrays.asList(2,3,4);
        List<ProductCategory> result = categoryService.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void save() {
        ProductCategory  productCategory= categoryService.save(new ProductCategory("女生最爱", 4));
        categoryService.save(productCategory);
    }
}