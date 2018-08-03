package ymm.sell.emnums;

import lombok.Getter;

/**
 * @Author: ymm
 * @Date: 2018/8/2 15:42
 * @Description:
 */
@Getter
public enum PayStatusEnum {

    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功");


    private Integer code;
    private String msg;

    PayStatusEnum(final Integer code, final String msg) {
        this.code = code;
        this.msg = msg;
    }
}
