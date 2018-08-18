package ymm.sell.execption;

import lombok.Getter;
import ymm.sell.emnums.ResultEnum;

/**
 * @Author: ymm
 * @Date: 2018/8/2 19:13
 * @Description:
 */
@Getter
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer  code,String message) {
        super(code+"  "+message);
        this.code = code;
    }
}
