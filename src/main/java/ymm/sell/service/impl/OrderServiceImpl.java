package ymm.sell.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ymm.sell.dataproject.OrderDetail;
import ymm.sell.dataproject.OrderMaster;
import ymm.sell.dataproject.ProductInfo;
import ymm.sell.dto.CartDTO;
import ymm.sell.dto.OrderDTO;
import ymm.sell.emnums.ResultEnum;
import ymm.sell.execption.SellException;
import ymm.sell.repositoty.OrderDetailRepository;
import ymm.sell.repositoty.OrderMasterRepository;
import ymm.sell.service.OrderService;
import ymm.sell.service.ProductService;
import ymm.sell.utils.KeyUtil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ymm
 * @Date: 2018/8/2 18:38
 * @Description:
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    @Transactional
    public OrderDTO create(final OrderDTO orderDTO) {
        String orderId = KeyUtil.getUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        List<CartDTO> cartDTOList = new ArrayList<>();
        //1. 查询商品（数量，价格）
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            //校验传进来的商品id在数据库中是否存在
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId()).get();
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //把商品id和数量装进list，方便下面扣库存
            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
            cartDTOList.add(cartDTO);

        //2.计算订单总价
            orderAmount=orderDetail.getProductPrice()
                        .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                        .add(orderAmount);
        //3.写入订单数据库
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);
        }

        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMasterRepository.save(orderMaster);

        //4.下单成功，扣库存
        productService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(final String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(final String buyerOpenid, final Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(final OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(final OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(final OrderDTO orderDTO) {
        return null;
    }
}
