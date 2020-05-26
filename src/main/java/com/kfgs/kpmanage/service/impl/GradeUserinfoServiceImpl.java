package com.kfgs.kpmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kfgs.kpmanage.entity.GradeUserinfo;
import com.kfgs.kpmanage.mapper.GradeUserinfoMapper;
import com.kfgs.kpmanage.service.GradeUserinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lsy
 */
@Service
public class GradeUserinfoServiceImpl implements GradeUserinfoService {

    @Autowired
    GradeUserinfoMapper gradeUserinfoMapper;

    @Override
    public IPage findAllUsers(String name,String pageNo, String limit) {
        Page<GradeUserinfo> page = new Page<>(Long.parseLong(pageNo),Long.parseLong(limit));
        QueryWrapper queryWrapper = new QueryWrapper();
        if (name != ""){
            queryWrapper.eq("name",name);
            IPage<GradeUserinfo> iPage = gradeUserinfoMapper.selectPage(page,queryWrapper);
            return iPage;
        }else{
            IPage<GradeUserinfo> iPage = gradeUserinfoMapper.selectPage(page,null);
            return iPage;
        }
    }

    //更新人员信息
    @Override
    public int updateUserInfo(String id,String name,String position,String department){
        GradeUserinfo gradeUserinfo = new GradeUserinfo();
        QueryWrapper queryWrapper = new QueryWrapper();
        if (id != null && id != ""){
            gradeUserinfo.setId(id);
            queryWrapper.eq("id",id);
        }
        //name不为空
        gradeUserinfo.setName(name);
        gradeUserinfo.setPosition(position);
        gradeUserinfo.setDepartment(department);
        int returnbak = gradeUserinfoMapper.update(gradeUserinfo,queryWrapper);
        return returnbak;
    }

    //根据id删除人员信息
    @Override
    public int deleteUserInfo(String ids){
        int returnbak = 1;
        String[] idlist = ids.split(",");
        int length = idlist.length;
        for(int i = 0; i< length;i++){
            returnbak = gradeUserinfoMapper.deleteById(idlist[i]);
        }
        return returnbak;
    }
}
