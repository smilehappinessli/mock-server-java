package cn.smilehappiness.mockserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = "cn.smilehappiness", exclude = {DataSourceAutoConfiguration.class})
public class MockServerBootStrap {

    public static void main(String[] args) {
        SpringApplication.run(MockServerBootStrap.class, args);
        System.out.println("[MOCK-SERVER]服务启动完成!!!");
    }

}
