package com.kfgs.kpmanage.entity.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.poi.ss.formula.functions.T;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class QueryResult<T> implements Serializable {
    //数据列表
    private List<T> list;
    //数据总数
    private long total;

    private Map<String,T> map;


    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Map<String, T> getMap() {
        return map;
    }

    public void setMap(Map<String, T> map) {
        this.map = map;
    }
}
