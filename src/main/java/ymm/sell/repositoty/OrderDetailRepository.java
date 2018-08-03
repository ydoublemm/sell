package ymm.sell.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import ymm.sell.dataproject.OrderDetail;
import ymm.sell.dataproject.OrderMaster;

import java.util.List;

/**
 * @Author: ymm
 * @Date: 2018/8/2 16:02
 * @Description:
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

    List<OrderDetail> findByOrOrderId(String orderId);

}
