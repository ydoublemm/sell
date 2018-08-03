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
    PRODUCT_STOCK_ERROR(20,"库存不足"),
    ORDER_NOT_EXIST(30,"订单不存在"),
    ORDER_STATUS_ERROR(40,"订单状态不正确"),
    ORDER_UPDATE_FAIL(50,"订单更新失败"),
    ORDER_DETAIL_EMPTY(60,"订单详情为空"),
    ORDER_PAY_STATUS_ERROR(70,"支付状态不正确")
    ;

    private Integer code;
    private String msg;

    ResultEnum(final Integer code, final String msg) {
        this.code = code;
        this.msg = msg;
    }
}
