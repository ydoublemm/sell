package ymm.sell.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ymm.sell.VO.ResultVO;

/**
 * @Author: ymm
 * @Date: 2018/8/1 16:05
 * @Description:
 */

@RestController
@RequestMapping(value = "/buyer/product")
public class BuyerProductController {

    @GetMapping("/list")
    public ResultVO list(){
        ResultVO resultVO = new ResultVO();
        resultVO.setMsg("22222");
        return resultVO;
    }
}
