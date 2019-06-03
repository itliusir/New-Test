package com.itliusir.test.http;

import java.util.HashMap;
import java.util.Map;


public class HttpResult {
    private int statusCode;
    private Object body;
    private Map<String,String> header = new HashMap<>();

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }
}
