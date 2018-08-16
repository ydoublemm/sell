package ymm.sell.dataproject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Author: ymm
 * @Date: 2018/8/15 21:07
 * @Description:
 */
@Entity
@Data
@DynamicUpdate
public class SellerInfo {
    @Id
    private String sellerId;

    private String username;

    private String password;

    private String openid;
}
