package com.kfgs.kpmanage.service.impl;

import com.kfgs.kpmanage.entity.GradeIdentity;
import com.kfgs.kpmanage.mapper.GradeIdentityMapper;
import com.kfgs.kpmanage.service.GradeIdentityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lsy
 */
@Service
public class GradeIdentityServiceImpl implements GradeIdentityService {

    @Autowired
    GradeIdentityMapper gradeIdentityMapper;

    @Override
    public String getPasswordByID(String id) {
        String password = gradeIdentityMapper.selectPassword(id);
        return password;
    }

    @Override
    public int userIDJudge(String userid) {
        int sum = gradeIdentityMapper.userIDJudge(userid);
        return sum;
    }
}
