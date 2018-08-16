package ymm.sell.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ymm.sell.dataproject.SellerInfo;
import ymm.sell.repositoty.SellInfoRepository;
import ymm.sell.service.SellerService;

/**
 * @Author: ymm
 * @Date: 2018/8/15 21:36
 * @Description:
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellInfoRepository repository;


    @Override
    public SellerInfo findSellerByOpenid(final String openid) {
        return repository.findByOpenid(openid);
    }
}
