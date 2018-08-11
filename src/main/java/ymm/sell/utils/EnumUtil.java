package ymm.sell.utils;

import ymm.sell.emnums.CodeEnum;

/**
 * @Author: ymm
 * @Date: 2018/8/11 10:51
 * @Description:
 */
public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code,Class<T> enumClass){
        for(T each:enumClass.getEnumConstants()){
            if(code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
