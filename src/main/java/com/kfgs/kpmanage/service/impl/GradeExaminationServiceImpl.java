package com.kfgs.kpmanage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kfgs.kpmanage.entity.GradeExamination;
import com.kfgs.kpmanage.entity.GradeUserinfo;
import com.kfgs.kpmanage.mapper.GradeExaminationMapper;
import com.kfgs.kpmanage.mapper.GradeUserinfoMapper;
import com.kfgs.kpmanage.service.GradeExaminationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lsy
 */
@Service
public class GradeExaminationServiceImpl implements GradeExaminationService {

    @Autowired
    GradeExaminationMapper gradeExaminationMapper;

    @Autowired
    GradeUserinfoMapper gradeUserinfoMapper;

}
