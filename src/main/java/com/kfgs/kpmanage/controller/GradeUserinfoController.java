package com.kfgs.kpmanage.controller;


import com.kfgs.kpmanage.common.controller.BaseController;
import com.kfgs.kpmanage.service.GradeUserinfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lsy
 */
@CrossOrigin
@RestController
@RequestMapping("/userinfo")
public class GradeUserinfoController extends BaseController {

    @Autowired
    GradeUserinfoService gradeUserinfoService;

    @ApiOperation("查询所有人员信息")
    @GetMapping("/findAllUsers")
    public Map findAllUsers(String name,String pageIndex,String pageSize){
        if (name == null){
            name = "";
        }
        Map resultMap = new HashMap();
        Map<String, Object> dataTable = getDataTable(gradeUserinfoService.findAllUsers(name,pageIndex,pageSize));
        resultMap.put("data",dataTable);
        resultMap.put("code",20000);
        return resultMap;
    }

    @ApiOperation("编辑人员信息")
    @GetMapping("/updateUserInfo")
    public int updateUserInfo(String id,String name,String position,String department){
        int returnbak = gradeUserinfoService.updateUserInfo(id,name,position,department);
        return returnbak;
    }

    @ApiOperation("删除人员信息")
    @GetMapping("/deleteUserInfo")
    public int deleteUserInfo(String ids){
        int returnbak = gradeUserinfoService.deleteUserInfo(ids);
        return returnbak;
    }
}
