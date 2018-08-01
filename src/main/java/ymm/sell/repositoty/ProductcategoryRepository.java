package ymm.sell.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import ymm.sell.dataproject.ProductCategory;

import java.util.List;

/**
 * @Author: ymm
 * @Date: 2018/7/31 20:58
 * @Description:
 */
public interface ProductcategoryRepository extends JpaRepository<ProductCategory,Integer> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> CategoryTypeList);
}
