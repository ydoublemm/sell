package ymm.sell.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ymm.sell.dataproject.ProductInfo;
import ymm.sell.dto.CartDTO;

import java.util.List;
import java.util.Optional;

/**
 * @Author: ymm
 * @Date: 2018/8/1 15:27
 * @Description:
 */
public interface ProductService {

    Optional<ProductInfo> findOne(String productId);

    /*
     * @author: ymm
     * @date: 2018/8/1 15:30
     * @param:
     * @return:
     * @Description:查找上架物品
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);


    //上架
    ProductInfo onSale(String productId);


    //下架
    ProductInfo offSale(String productId);
}
