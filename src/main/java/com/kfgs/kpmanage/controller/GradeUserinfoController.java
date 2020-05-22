package com.kfgs.kpmanage.controller;


import com.kfgs.kpmanage.common.controller.BaseController;
import com.kfgs.kpmanage.service.GradeUserinfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lsy
 */
@RestController
@RequestMapping("/userinfo")
public class GradeUserinfoController extends BaseController {

    @Autowired
    GradeUserinfoService gradeUserinfoService;

    @ApiOperation("查询所有人员信息")
    @GetMapping("/findAllUsers")
    public Map findAllUsers(String pageNo,String limit){
        Map resultMap = new HashMap();
        Map<String, Object> dataTable = getDataTable(gradeUserinfoService.findAllUsers(pageNo,limit));
        resultMap.put("userlist",dataTable);
        resultMap.put("code",20000);
        return resultMap;
    }
}
