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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lsy
 */
@Service
public class GradeExaminationServiceImpl implements GradeExaminationService {

    @Autowired
    GradeExaminationMapper gradeExaminationMapper;

    @Autowired
    GradeUserinfoMapper gradeUserinfoMapper;

    @Override
    public List<GradeExamination> getExamDetail(String owner) {
        //返回的list
        List list = new ArrayList();
        //存放考核信息
        Map<String,String> map = new HashMap<>();
        list = gradeExaminationMapper.getExamDetail(owner);
        return list;
    }
}
