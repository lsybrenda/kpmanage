package com.kfgs.kpmanage.mapper;

import com.kfgs.kpmanage.entity.GradeIdentity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author lsy
 */
public interface GradeIdentityMapper extends BaseMapper<GradeIdentity> {

    String selectPassword(@Param("id") String id);

    int userIDJudge(@Param("userid") String userid);

}
