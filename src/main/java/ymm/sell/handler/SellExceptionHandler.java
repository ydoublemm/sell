package ymm.sell.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ymm.sell.VO.ResultVO;
import ymm.sell.execption.SellException;
import ymm.sell.execption.SellerAuthorizeException;
import ymm.sell.utils.ResultVOUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ymm
 * @Date: 2018/8/16 15:56
 * @Description:
 */
@ControllerAdvice
public class SellExceptionHandler {

    //拦截登陆异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerSellAuthorizeException(){
        return new ModelAndView("common/login");
    }


    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellException(SellException e){
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }
}
