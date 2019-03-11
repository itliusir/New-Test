package com.itliusir.test.request;


import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 输入N个网址，返回Get请求这N个网址的statusCode，此方法会被大量并发调用
 *
 * @author liugang
 * @since 2019/3/6
 */

@Slf4j
public class TestRequest {

    public static void main(String[] args) throws InterruptedException {


        /*
         * PoolingClientConnectionManager类将根据其配置分配连接。
         * 如果给定路由的所有连接都已租用，则会阻塞对连接的请求，直到有连接释放回到连接池。
         * 可以通过将“http.conn-manager.timeout”设置为正值来确保连接管理器在连接请求操作中不会无限期地阻塞。
         * 如果连接请求不能在给定的期限内提供服务,会抛出ConnectionPoolTimeoutException异常
         */
        List<String> urls = Arrays.asList("https://www.baidu.com", "https://www.baidu.com", "https://www.baidu.com");
        List<Integer> codes = get(urls);
        log.info("codes:{}", codes);

    }

    public static List<Integer> get(List<String> urls) {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(200);
        RequestConfig requestConfig = RequestConfig.custom()
                // 获取连接超时时间
                .setConnectionRequestTimeout(1000)
                // 请求超时时间
                .setConnectTimeout(1000)
                // 响应超时时间
                .setSocketTimeout(1000)
                .build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .setDefaultRequestConfig(requestConfig)
                .build();

        return urls.parallelStream().map(
                url -> {
                    HttpGet httpget = new HttpGet(url);
                    try (CloseableHttpResponse response = httpClient.execute(httpget, HttpClientContext.create())) {
                        return response.getStatusLine().getStatusCode();
                    } catch (IOException ex) {
                        log.error(ex.getMessage());
                    }
                    return 500;
                }
        ).collect(Collectors.toList());
    }
}
