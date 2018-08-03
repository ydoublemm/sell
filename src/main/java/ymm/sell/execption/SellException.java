package ymm.sell.execption;

import ymm.sell.emnums.ResultEnum;

/**
 * @Author: ymm
 * @Date: 2018/8/2 19:13
 * @Description:
 */
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
