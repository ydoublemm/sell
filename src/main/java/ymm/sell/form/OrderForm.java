package ymm.sell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: ymm
 * @Date: 2018/8/4 16:03
 * @Description:
 */
@Data
public class OrderForm {

    /*买家姓名*/
    @NotEmpty(message = "姓名必填")
    private String name;

    /*手机号*/
    @NotEmpty(message = "手机号必填")
    private String phone;


    /*地址*/
    @NotEmpty(message = "地址必填")
    private String address;

    /*openid*/
    @NotEmpty(message = "openid必填")
    private String openid;

    /*购物车*/
    @NotEmpty(message = "购物车不能为空")
    private String items;

}
