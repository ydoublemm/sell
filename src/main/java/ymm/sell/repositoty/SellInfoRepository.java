package ymm.sell.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import ymm.sell.dataproject.SellerInfo;

/**
 * @Author: ymm
 * @Date: 2018/8/15 21:08
 * @Description:
 */
public interface SellInfoRepository extends JpaRepository<SellerInfo,String> {


    SellerInfo findByOpenid(String openid);


}
