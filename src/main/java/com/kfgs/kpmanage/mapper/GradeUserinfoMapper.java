package com.kfgs.kpmanage.mapper;

import com.kfgs.kpmanage.entity.GradeUserinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lsy
 */
public interface GradeUserinfoMapper extends BaseMapper<GradeUserinfo> {

    int insertMultiUsers(List<GradeUserinfo> list);

    String getIdByName(String name);

    List<Map<String,String>> getNameAndID();

}
