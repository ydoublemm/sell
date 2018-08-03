package ymm.sell.emnums;

import lombok.Data;
import lombok.Getter;

/**
 * @Author: ymm
 * @Date: 2018/8/2 15:42
 * @Description:
 */
@Getter
public enum OrderStatusEnum {
    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"已取消");


    private Integer code;
    private String msg;

    OrderStatusEnum(final Integer code, final String msg) {
        this.code = code;
        this.msg = msg;
    }
}
