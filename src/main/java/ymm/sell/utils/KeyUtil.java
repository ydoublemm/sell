package ymm.sell.utils;

import java.util.Random;

/**
 * @Author: ymm
 * @Date: 2018/8/2 19:32
 * @Description:
 */
public class KeyUtil {

    public static synchronized String getUniqueKey(){
        Random random = new Random();

        Integer number = random.nextInt(900000)+100000;

        return System.currentTimeMillis()+String.valueOf(number);
    }
}
