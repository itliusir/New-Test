package com.itliusir.test.parse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * IP Proxy Test
 *
 * @author liugang
 * @since 2019/3/18
 */

@Slf4j
public class TestParse1 {

    protected String usedFor = "";

    private static ExecutorService es = Executors.newFixedThreadPool(51);

    public static void main(String[] args) throws Exception {
        TestParse1 testParse1 = new TestParse1();
        String res = "{\n" +
                "  \"data\": [\n" +
                "    {\n" +
                "      \"account\": \"vpn:account:lips.dobel.cn:pptp:hzvpn375\",\n" +
                "      \"connected_time\": \"2019-03-18T10:46:14.824013933+08:00\",\n" +
                "      \"err\": \"\",\n" +
                "      \"expired_in\": 26,\n" +
                "      \"failed_num\": 8332,\n" +
                "      \"failed_num_today\": 27,\n" +
                "      \"id\": \"8edc7f8cfdac\",\n" +
                "      \"interval\": 60,\n" +
                "      \"last_seen\": \"2019-03-18T10:46:47.236040953+08:00\",\n" +
                "      \"proxy_in_ip\": \"10.0.0.77\",\n" +
                "      \"proxy_out_ip\": \"120.92.90.26\",\n" +
                "      \"proxy_port\": 16051,\n" +
                "      \"remote_addr\": \"中国 华中 湖北省 随州市 随州市 电信\",\n" +
                "      \"remote_ip\": \"111.177.198.68\",\n" +
                "      \"success_num\": 153060,\n" +
                "      \"success_num_today\": 573,\n" +
                "      \"vpn_status\": \"connected\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"err\": null,\n" +
                "  \"ok\": true\n" +
                "}";
        res = res.replace("<br/>", "\n");
        JSONObject obj = JSON.parseObject(res);
        JSONArray arr = (JSONArray) obj.get("data");
        if (arr != null) {
            for (int i = 0; i < 3; i++) {
                testParse1.parseObjectToRedis(arr, "ev", 0);
            }
            testParse1.parseObjectToRedis(arr, "click", 0);
            testParse1.parseObjectToRedis(arr, "exclusive_click", 1234);
            }
    }

    private void parseObjectToRedis(JSONArray arr, String evOrClick, int taskId) throws Exception {

        List<Object> list = new ArrayList<>();
        for (int i = 0; i< arr.size() ; i++){
            list.add(arr.get(i));
            if(list.size() == 20) {
                List<Object> objects = list;
                es.execute(new InnerProcessProxy(objects,evOrClick,taskId,this.usedFor));
                list = new ArrayList<>();
            }
        }
        if(list.size() > 0){
            es.execute(new InnerProcessProxy(list,evOrClick,taskId,this.usedFor));
        }
    }

    class InnerProcessProxy implements Runnable{
        List<Object> arr;
        String evOrClick;
        int taskId;
        String usedFor;

        public InnerProcessProxy(List<Object> arr, String evOrClick, int taskId,String usedFor){
            this.arr = arr;
            this.evOrClick = evOrClick;
            this.taskId = taskId;
            this.usedFor = usedFor;
        }

        @Override
        public void run() {
            Set<String> keys = null;
            for (Object o : arr) {
                try {
                    JSONObject b = (JSONObject) o;
//                    String proxyIp = b.getString("proxy_in_ip");
                    String proxyIp = b.getString("proxy_out_ip");
                    int port = b.getInteger("proxy_port");
                    String hostIp = b.getString("remote_ip");
                    String vpsHost = b.getString("proxy_in_ip");
                    String status = b.getString("vpn_status");
                    String account = b.getString("account");
                    int interval = b.getInteger("interval") == null ? 45 : b.getInteger("interval");
                    String[] addrArr = b.getString("remote_addr").split("\\s+");
                    String addr = IpUtils.getIpArea(hostIp);
                    if ("".equals(addr)) {
                        addr = addrArr[2] + addrArr[3];
                    }
                    String netName = "cnc";
                    int expire = b.getInteger("expired_in");

                    StringBuilder sb = new StringBuilder(proxyIp).append("#").append(port).append("#").append("http");
                    sb.append("#").append(netName).append("#").append(vpsHost).append("#").append("").append("#").append(addr).append("#").append(account).append("#");

                    sb.append("#").append(hostIp).append("#");

                    //ivt

                    sb.append(hostIp).append("#");

                    sb.append(expire * 1000);
                    sb.append("#").append(System.currentTimeMillis());
                    log.info("xxxxxxx:{}",sb);
//                if ("vpn_test".equals(this.name)) {
//                    clickKey = clickKey + "_vpn_test";
//                    evKey = evKey + "_vpn_test";
//                    exclusiveKey = exclusiveKey + "_vpn_test";
//                    appointKey = appointKey + "_vpn_test";
//                }
                    if("appoint".equals(usedFor) && !evOrClick.contains("appoint")){
                        return;
                    }



                } catch (Exception er) {
                    log.error(er.getMessage());
                }
            }

        }

    }
}
