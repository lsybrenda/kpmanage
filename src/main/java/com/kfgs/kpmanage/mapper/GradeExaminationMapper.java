package com.kfgs.kpmanage.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kfgs.kpmanage.entity.GradeExamination;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @author lsy
 */
public interface GradeExaminationMapper extends BaseMapper<GradeExamination> {

    //IPage<GradeExamination> getExamList(IPage<GradeExamination> page);

    List<GradeExamination> getExamDetail(String owner);

    int deleteByOwner(String[] ids);

}
