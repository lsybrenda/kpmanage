package com.kfgs.kpmanage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kfgs.kpmanage.entity.GradeRelation;
import com.kfgs.kpmanage.mapper.GradeRelationMapper;
import com.kfgs.kpmanage.service.GradeRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lsy
 */
@Service
public class GradeRelationServiceImpl implements GradeRelationService {

    @Autowired
    GradeRelationMapper gradeRelationMapper;

    @Override
    @Transactional
    public IPage getRelations(String pageNo, String limit) {
        Page<GradeRelation> page = new Page<>(Long.parseLong(pageNo),Long.parseLong(limit));
        IPage<GradeRelation> iPage = gradeRelationMapper.getRelationList(page);
        return iPage;
    }
}
