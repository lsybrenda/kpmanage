package com.kfgs.kpmanage.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kfgs.kpmanage.entity.GradeRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kfgs.kpmanage.entity.GradeUserinfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lsy
 */
public interface GradeRelationMapper extends BaseMapper<GradeRelation> {

    IPage<GradeRelation> getRelationList(IPage<GradeRelation> page);

    IPage<GradeUserinfo> getCandidates(Page<GradeUserinfo> page);

    int updateRelation(String examiner,String candidate,String type,String groups);

    int insertMultiRels(List<GradeRelation> list);

    List<GradeUserinfo> getCandidateList();
}
