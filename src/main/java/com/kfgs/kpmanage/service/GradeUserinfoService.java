package com.kfgs.kpmanage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kfgs.kpmanage.entity.GradeUserinfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author lsy
 */
public interface GradeUserinfoService {

    //根据姓名获取ID
    String getIdByName(String name);

    //查询所有人员
    IPage findAllUsers(String name,String pageNo, String limit);

    //更新人员信息
    int updateUserInfo(String id,String name,String email,String position,String department,String isdafen);

    //删除人员信息
    int deleteUserInfo(String ids);

    //新增单个人员信息
    void addUserInfo(String id,String name,String email,String position,String department,String isdafen);

    //导入Excel
    int batchImport(MultipartFile file) throws Exception;

}
