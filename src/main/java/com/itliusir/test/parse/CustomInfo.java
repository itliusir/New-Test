package com.itliusir.test.parse;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.List;

/**
 * 自定义List[<key,value>]数据Bean(特殊任务加机器...)
 *
 * @author liugang
 * @since 2019/5/15
 */

@ConfigurationProperties(prefix = "custom")
@Configuration
public class CustomInfo implements Serializable {

    private static final long serialVersionUID = -1212805210744458855L;

    /**
     * 特殊任务数据
     */
    @NestedConfigurationProperty
    private List<SpecialTaskInfo> specialTask;

    public List<SpecialTaskInfo> getSpecialTask() {
        return specialTask;
    }

    public void setSpecialTask(List<SpecialTaskInfo> specialTask) {
        this.specialTask = specialTask;
    }
}
