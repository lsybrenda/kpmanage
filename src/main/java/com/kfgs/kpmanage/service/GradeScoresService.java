package com.kfgs.kpmanage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kfgs.kpmanage.entity.GradeScores;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author lsy
 */
public interface GradeScoresService {


    //获取考核人列表
    IPage getCandidates(String pageNo, String limit);

}
