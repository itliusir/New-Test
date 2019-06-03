package com.itliusir.test.parse;

import java.io.Serializable;

/**
 * TODO
 *
 * @author liugang
 * @since 2019/5/15
 */
public class SpecialTaskInfo implements Serializable{

    private static final long serialVersionUID = 3487252949703994136L;
    private Integer taskId;
    private Integer machineNum;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getMachineNum() {
        return machineNum;
    }

    public void setMachineNum(Integer machineNum) {
        this.machineNum = machineNum;
    }
}
