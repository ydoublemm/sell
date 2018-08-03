package ymm.sell.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ymm.sell.dataproject.ProductInfo;
import ymm.sell.dto.CartDTO;
import ymm.sell.emnums.ProductStatusEnum;
import ymm.sell.emnums.ResultEnum;
import ymm.sell.execption.SellException;
import ymm.sell.repositoty.ProductInfoRepository;
import ymm.sell.service.ProductService;

import java.util.List;
import java.util.Optional;

/**
 * @Author: ymm
 * @Date: 2018/8/1 15:30
 * @Description:
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository repository;

    @Override
    public Optional<ProductInfo> findOne(final String productId) {
        return repository.findById(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(final Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(final ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public void increaseStock(final List<CartDTO> cartDTOList) {

    }

    @Override
    @Transactional
    public void decreaseStock(final List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo = repository.findById(cartDTO.getProductId()).get();

            //检查库存是否充足
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if(result<0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);
            repository.save(productInfo);

        }
    }
}
