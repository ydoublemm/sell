package ymm.sell.dto;

import lombok.Data;

/**
 * @Author: ymm
 * @Date: 2018/8/2 23:23
 * @Description:
 */
@Data
public class CartDTO {
    /*商品id*/
    private String productId;
    /*数量*/
    private Integer ProductQuantity;

    public CartDTO() {
    }

    public CartDTO(final String productId, final Integer productQuantity) {
        this.productId = productId;
        ProductQuantity = productQuantity;
    }
}
