package com.kfgs.kpmanage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kfgs.kpmanage.entity.GradeRelation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author lsy
 */
public interface GradeRelationService {

    //获取关系列表
    IPage getRelations(String pageNo, String limit);

    //更新打分关系
    int updateRelation(String examiner,String candidate,String type,String groups);

    //新增单条考核关系
    int addRelationInfo(GradeRelation gradeRelation);

    //删除考核信息
    int deleteRelationInfo(List<GradeRelation> list);
}
