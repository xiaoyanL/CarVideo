package com.fd.baselibrary.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HistoryBean implements Serializable {
   List<String> list;

    public HistoryBean() {
        list=new ArrayList<>();
    }

    public List<String> getList() {
        if (list==null)
            list=new ArrayList<>();
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void  addData(String name){
        if (list==null){
            list=new ArrayList<>();
        }
        list.add(name);
    }
}
