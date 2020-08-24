package com.kfgs.kpmanage.mapper;

import com.kfgs.kpmanage.entity.GradeUserinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author lsy
 */
public interface GradeUserinfoMapper extends BaseMapper<GradeUserinfo> {

    int insertMultiUsers(List<GradeUserinfo> list);

}
