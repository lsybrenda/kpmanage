package com.kfgs.kpmanage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kfgs.kpmanage.entity.GradeUserinfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author lsy
 */
public interface GradeUserinfoService {

    //查询所有人员
    IPage findAllUsers(String name,String pageNo, String limit);

    //更新人员信息
    int updateUserInfo(String id,String name,String position,String department);

    //删除人员信息
    int deleteUserInfo(String ids);

}
