package ymm.sell.utils;

import ymm.sell.VO.ResultVO;

/**
 * @Author: ymm
 * @Date: 2018/8/2 14:29
 * @Description:
 */
public class ResultVOUtil {

    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setMsg("成功");
        resultVO.setCode(0);
        resultVO.setData(object);
        return resultVO;
    }


    public static ResultVO success(){
        return success(null);
    }

    public static ResultVO error (Integer code,String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setMsg(msg);
        resultVO.setCode(code);
        return resultVO;
    }
}
