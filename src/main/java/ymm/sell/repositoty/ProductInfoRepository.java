package ymm.sell.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import ymm.sell.dataproject.ProductInfo;

import java.util.List;

/**
 * @Author: ymm
 * @Date: 2018/8/1 15:15
 * @Description:
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);
}
