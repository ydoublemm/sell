package ymm.sell.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ymm.sell.dataproject.SellerInfo;

/**
 * @Author: ymm
 * @Date: 2018/8/15 21:34
 * @Description:
 */

public interface SellerService {
    SellerInfo findSellerByOpenid(String openid);
}
