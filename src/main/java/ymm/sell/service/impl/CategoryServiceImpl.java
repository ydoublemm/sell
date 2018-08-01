package ymm.sell.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ymm.sell.dataproject.ProductCategory;
import ymm.sell.repositoty.ProductcategoryRepository;
import ymm.sell.service.CategoryService;

import java.util.List;
import java.util.Optional;

/**
 * @Author: ymm
 * @Date: 2018/8/1 13:39
 * @Description:
 */
@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private ProductcategoryRepository repository;

    @Override
    public Optional<ProductCategory> findOne(final Integer categoryId) {
        return repository.findById(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(final List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(final ProductCategory productCategory) {
        return repository.save(productCategory);
    }
}
