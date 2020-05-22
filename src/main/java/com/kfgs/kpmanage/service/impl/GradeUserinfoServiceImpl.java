package com.kfgs.kpmanage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kfgs.kpmanage.entity.GradeUserinfo;
import com.kfgs.kpmanage.mapper.GradeUserinfoMapper;
import com.kfgs.kpmanage.service.GradeUserinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lsy
 */
@Service
public class GradeUserinfoServiceImpl implements GradeUserinfoService {

    @Autowired
    GradeUserinfoMapper gradeUserinfoMapper;

    @Override
    public IPage findAllUsers(String pageNo, String limit) {
        Page<GradeUserinfo> page = new Page<GradeUserinfo>(Long.parseLong(pageNo),Long.parseLong(limit));
        return gradeUserinfoMapper.selectPage(page,null);
    }
}
