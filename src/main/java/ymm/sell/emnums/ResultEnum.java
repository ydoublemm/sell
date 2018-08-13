package ymm.sell.emnums;

import lombok.Getter;

/**
 * @Author: ymm
 * @Date: 2018/8/2 19:13
 * @Description:
 */
@Getter
public enum ResultEnum {

    SUCCESS(0,"成功"),
    PARAM_ERROR(1,"参数不正确"),


    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(20,"库存不足"),
    ORDER_NOT_EXIST(30,"订单不存在"),
    ORDER_STATUS_ERROR(40,"订单状态不正确"),
    ORDER_UPDATE_FAIL(50,"订单更新失败"),
    ORDER_DETAIL_EMPTY(60,"订单详情为空"),
    ORDER_PAY_STATUS_ERROR(70,"支付状态不正确"),
    ORDER_OWNER_ERROR(80,"该订单不属于当前用户"),


    cart_empty(1000,"购物车不能为空"),


    WX_MP_ERROR(2000,"微信公众账号错误"),
    ;

    private Integer code;
    private String msg;

    ResultEnum(final Integer code, final String msg) {
        this.code = code;
        this.msg = msg;
    }
}
