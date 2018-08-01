package ymm.sell.emnums;

import lombok.Getter;

/**
 * @Author: ymm
 * @Date: 2018/8/1 15:32
 * @Description:
 */
@Getter
public enum ProductStatusEnum {

    UP(0,"上架"),
    DOWN(1,"下架");

    private Integer code;
    private String message;

    ProductStatusEnum(final Integer code, final String message) {
        this.code = code;
        this.message = message;
    }
}
