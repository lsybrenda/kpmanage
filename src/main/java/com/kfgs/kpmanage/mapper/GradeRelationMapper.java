package com.kfgs.kpmanage.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kfgs.kpmanage.entity.GradeRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author lsy
 */
public interface GradeRelationMapper extends BaseMapper<GradeRelation> {

    IPage<GradeRelation> getRelationList(IPage<GradeRelation> page);

    int updateRelation(String examiner,String candidate,String type,String groups);

    int insertMultiRels(List<GradeRelation> list);
}
