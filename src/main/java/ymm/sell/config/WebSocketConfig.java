package ymm.sell.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Author: ymm
 * @Date: 2018/8/16 20:52
 * @Description:
 *
 * 这个类在测试的时候会报错，需要把这个类给注释掉
 * 网上没找到解决方法
 *
 * 项目打包的时候需要跳过测试
 * mvn package -DskipTests
 *
 */

@Component
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}