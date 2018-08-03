package ymm.sell.repositoty;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ymm.sell.dataproject.OrderMaster;

/**
 * @Author: ymm
 * @Date: 2018/8/2 15:59
 * @Description:
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {

    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
