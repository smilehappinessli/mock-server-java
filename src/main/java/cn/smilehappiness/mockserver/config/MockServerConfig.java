package cn.smilehappiness.mockserver.config;

import org.mockserver.client.MockServerClient;
import org.mockserver.configuration.ConfigurationProperties;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.MediaType;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

/**
 * <p>
 * Mock Server配置类
 * <p/>
 *
 * @author smilehappiness
 * @Date 2020/8/16 16:59
 */
@Configuration
public class MockServerConfig {

    private ClientAndServer mockServer;

    /**
     * <p>
     * To start the server or proxy create a client
     * UI界面：http://localhost:1080/mockserver/dashboard
     * <p/>
     *
     * @param
     * @return void
     * @Date 2020/8/16 17:35
     */
    @PostConstruct
    public void startMockServer() {
        ConfigurationProperties.enableCORSForAPI(true);
        //初始化期望配置类
        //ConfigurationProperties.initializationClass(ExpectationInit.class.getName());

        //持久化期望数据
        ConfigurationProperties.persistExpectations(true);
        //持久化文件路径
        ConfigurationProperties.persistedExpectationsPath("persistedExpectations.json");
        //加载期望数据的文件路径
        ConfigurationProperties.initializationJsonPath("persistedExpectations.json");

        mockServer = startClientAndServer(1080);
        System.out.println("mock server【" + mockServer + "】 start...");
    }

    //@PostConstruct
    public void initExpectations() {
        new MockServerClient("localhost", 1080)
                .when(
                        request()
                                .withMethod("POST")
                                .withPath("/login")
                                .withContentType(MediaType.APPLICATION_JSON)
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withBody("test response")
                );
    }

}
