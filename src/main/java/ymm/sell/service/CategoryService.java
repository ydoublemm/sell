package ymm.sell.service;

import ymm.sell.dataproject.ProductCategory;

import java.util.List;
import java.util.Optional;

/**
 * @Author: ymm
 * @Date: 2018/8/1 13:34
 * @Description:
 */
public interface CategoryService {

    Optional<ProductCategory> findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
