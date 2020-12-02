package com.kfgs.kpmanage.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.kfgs.kpmanage.common.controller.BaseController;
import com.kfgs.kpmanage.entity.response.CommonCode;
import com.kfgs.kpmanage.entity.response.QueryResponseResult;
import com.kfgs.kpmanage.service.GradeUserinfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @ApiOperation("人员登录")
    @PostMapping("/userLogin")
    public QueryResponseResult userLogin(String username,String password){
        System.out.println(username + password);
        if (username.equals("admin") && password.equals("111111")){
            return new QueryResponseResult(CommonCode.SUCCESS,null);
        }
        else return new QueryResponseResult(CommonCode.FAIL,null);
    }


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
    public int updateUserInfo(String id,String name,String email,String position,String department,String isdafen){
        int returnbak = gradeUserinfoService.updateUserInfo(id,name,email,position,department,isdafen);
        return returnbak;
    }

    @ApiOperation("删除人员信息")
    @GetMapping("/deleteUserInfo")
    public int deleteUserInfo(String ids){
        int returnbak = gradeUserinfoService.deleteUserInfo(ids);
        return returnbak;
    }

    @ApiOperation("新增单个人员信息")
    @GetMapping("/addUserInfo")
    public void addUserInfo(String id,String name,String email,String position,String department,String isdafen){
        if(email == null){
            email = "";
        }
        if(position == null){
            position = "";
        }
        if(department == null){
            department = "";
        }
        try {
            gradeUserinfoService.addUserInfo(id,name,email,position,department,isdafen);
        }catch (RuntimeException e){
            e.printStackTrace();
        }

    }

    @ApiOperation(value = "Excel导入",notes = "批量上传人员信息到数据库")
    @RequestMapping(value = "/upload")
    public QueryResponseResult upload(@RequestParam("file") MultipartFile file) throws Exception{
        String name = file.getName();
        int res = gradeUserinfoService.batchImport(file);
        if (res == 1){
            return new QueryResponseResult(CommonCode.SUCCESS,null);
        }else {
            return new QueryResponseResult(CommonCode.FAIL,null);
        }
    }

}
