package com.itliusir.test.hash;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class SecurityData implements Serializable {
    private static final long serialVersionUID = -829577908099953806L;
    private String serviceId;
    private List<SecurityDataitem> securityDataitems;
    private Map<String, List<String>> fullPath;

    public SecurityData() {
    }

    public SecurityData(String serviceId, List<SecurityDataitem> securityDataitems, Map<String, List<String>> fullPath) {
        this.serviceId = serviceId;
        this.securityDataitems = securityDataitems;
        this.fullPath = fullPath;
    }
}