package com.kfgs.kpmanage.common.utils;

import com.kfgs.kpmanage.entity.GradeUserinfo;
import com.kfgs.kpmanage.mapper.GradeUserinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class NameToID {

    @Autowired
    private GradeUserinfoMapper gradeUserinfoMapper;
    private static NameToID nameToID;

    @PostConstruct
    public void init(){
        nameToID = this;
        nameToID.gradeUserinfoMapper = this.gradeUserinfoMapper;
    }


    public static Map<String,String> getNameAndID(){
       List<Map<String,String>> list = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
        list = nameToID.gradeUserinfoMapper.getNameAndID();
        Map<String,String> nameAndID = new HashMap<>();
        for (int i=0;i<list.size();i++){
            map = list.get(i);
            String id = map.get("ID");
            String name = map.get("NAME");
            nameAndID.put(name,id);
        }
        return nameAndID;
    }
}
