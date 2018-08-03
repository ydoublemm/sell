package ymm.sell.emnums;

import lombok.Getter;

/**
 * @Author: ymm
 * @Date: 2018/8/2 19:13
 * @Description:
 */
@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(20,"库存不足")
    ;

    private Integer code;
    private String msg;

    ResultEnum(final Integer code, final String msg) {
        this.code = code;
        this.msg = msg;
    }
}
