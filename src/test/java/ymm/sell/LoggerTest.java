package ymm.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: ymm
 * @Date: 2018/7/30 21:00
 * @Description:
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {

    //private final Logger logger= LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test1(){
        String name= "ymm";
        String pwd ="123";
        log.debug("debug....");
        log.info("info...");
        log.error("errod...");
        log.error("name: {},pwd {}",name,pwd);
    }
}
