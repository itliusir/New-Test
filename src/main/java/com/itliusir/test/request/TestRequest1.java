package com.itliusir.test.request;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 返回代理的真实IP
 *
 * @author liugang
 * @since 2019/5/7
 */

@Slf4j
public class TestRequest1 {
    public static void main(String[] args) {
        String realIp = getRealIpByNginx("120.92.76.25", 16058);
        log.info(realIp);
    }


    private static String getRealIpByNginx(String ip, int port) {
        String realIp = "";
        String url = "http://www.taobao.com/help/getip.php";
        CloseableHttpClient httpClient = null;
        if (StringUtils.isNotBlank(ip) && port > 0) {
            HttpHost proxyHost = new HttpHost(ip, port);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(3000).setSocketTimeout(3000)
                    .setConnectionRequestTimeout(3000).build();
            httpClient = HttpClientBuilder.create()
                    .setDefaultHeaders(getDefaultHeaders())
                    .setRetryHandler(new DefaultHttpRequestRetryHandler(0, true))
                    .setProxy(proxyHost)
                    .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                    .setRedirectStrategy(new DefaultRedirectStrategy()).setDefaultRequestConfig(requestConfig).build();
            try {
                HttpEntity entity = httpClient.execute(new HttpGet(url)).getEntity();
                realIp = EntityUtils.toString(entity);
            } catch (IOException e) {
                log.error("代理获取真实IP地址request失败 | " + e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    if (httpClient != null) {
                        httpClient.close();
                    }
                } catch (IOException e) {
                    log.error("代理获取真实IP地址,Client关闭失败");
                }
            }
        }
        return realIp;
    }

    private static List<Header> getDefaultHeaders() {
        Header[] headers = {new BasicHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95"),
                new BasicHeader("Accept-Encoding", "gzip, deflate"),
                new BasicHeader("Accept-Language", "zh-CN,zh;q=0.9"),
                new BasicHeader("Authorization", "Basic aGlwcG86aGlwcG8="),
        };
        return Arrays.asList(headers);
    }
}
