package ymm.sell.VO;

import lombok.Data;

/**
 * @Author: ymm
 * @Date: 2018/8/1 21:17
 * @Description:
 */
@Data
public class ResultVO<T> {
    /*错误码*/
    private Integer code;

    /*信息*/
    private String msg;

    /**/
    private T data;
}
