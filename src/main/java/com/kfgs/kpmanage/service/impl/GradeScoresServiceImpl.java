package com.kfgs.kpmanage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kfgs.kpmanage.entity.GradeScores;
import com.kfgs.kpmanage.entity.GradeUserinfo;
import com.kfgs.kpmanage.mapper.GradeRelationMapper;
import com.kfgs.kpmanage.mapper.GradeScoresMapper;
import com.kfgs.kpmanage.service.GradeScoresService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lsy
 */
@Service
public class GradeScoresServiceImpl implements GradeScoresService {

    @Autowired
    GradeScoresMapper gradeScoresMapper;

    @Autowired
    GradeRelationMapper gradeRelationMapper;

    @Override
    public IPage getCandidates(String pageNo, String limit) {
        Page<GradeUserinfo> page = new Page<>(Long.parseLong(pageNo),Long.parseLong(limit));
        IPage<GradeUserinfo> iPage = gradeRelationMapper.getCandidates(page);
        return iPage;
    }
}
