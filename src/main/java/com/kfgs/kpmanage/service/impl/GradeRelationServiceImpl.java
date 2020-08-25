package com.kfgs.kpmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kfgs.kpmanage.entity.GradeRelation;
import com.kfgs.kpmanage.mapper.GradeRelationMapper;
import com.kfgs.kpmanage.service.GradeRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lsy
 */
@Service
public class GradeRelationServiceImpl implements GradeRelationService {

    @Autowired
    GradeRelationMapper gradeRelationMapper;

    @Override
    public IPage getRelations(String pageNo, String limit) {
        Page<GradeRelation> page = new Page<>(Long.parseLong(pageNo),Long.parseLong(limit));
        IPage<GradeRelation> iPage = gradeRelationMapper.getRelationList(page);
        return iPage;
    }

    //更新打分关系
    @Override
    public int updateRelation(String examiner,String candidate,String type, String groups) {

        int returnbak = gradeRelationMapper.updateRelation(examiner,candidate,type,groups);
        return returnbak;
    }

    @Override
    public int addRelationInfo(GradeRelation gradeRelation) {
        return gradeRelationMapper.insert(gradeRelation);
    }

    @Override
    @Transactional
    public int deleteRelationInfo(List<GradeRelation> list) {
        int res=0;
        for (int i =0;i<list.size();i++){
            GradeRelation gradeRelation = list.get(i);
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("examiner",gradeRelation.getExaminer());
            queryWrapper.eq("candidate",gradeRelation.getCandidate());
            queryWrapper.eq("type",gradeRelation.getType());
            queryWrapper.eq("groups",gradeRelation.getGroups());
            res += gradeRelationMapper.delete(queryWrapper);
        }
        return res;
    }


}
