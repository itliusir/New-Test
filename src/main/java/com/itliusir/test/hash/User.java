package com.itliusir.test.hash;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author liugang
 * @since 2018/10/24
 */

public class User implements Serializable{
    private static final long serialVersionUID = 4318493282063074874L;

    private String id;

    private List<String> list;

    private Map<String,List<String>> map;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Map<String, List<String>> getMap() {
        return map;
    }

    public void setMap(Map<String, List<String>> map) {
        this.map = map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (list != null ? !list.equals(user.list) : user.list != null) return false;
        return map != null ? map.equals(user.map) : user.map == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (list != null ? list.hashCode() : 0);
        result = 31 * result + (map != null ? map.hashCode() : 0);
        return result;
    }
}
