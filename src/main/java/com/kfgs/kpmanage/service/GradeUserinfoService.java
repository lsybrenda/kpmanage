package com.kfgs.kpmanage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kfgs.kpmanage.entity.GradeUserinfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author lsy
 */
public interface GradeUserinfoService {

    //查询所有人员
    IPage findAllUsers(String pageNo, String limit);

}
