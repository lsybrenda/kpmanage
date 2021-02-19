package com.kfgs.kpmanage.service;

import com.kfgs.kpmanage.entity.GradeIdentity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author lsy
 */
public interface GradeIdentityService {

    String getPasswordByID(String id);

    int userIDJudge(String userid);

}
