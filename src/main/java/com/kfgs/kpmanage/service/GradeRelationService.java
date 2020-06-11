package com.kfgs.kpmanage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kfgs.kpmanage.entity.GradeRelation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author lsy
 */
public interface GradeRelationService {
    //获取关系列表
    IPage getRelations(String pageNo, String limit);

}
