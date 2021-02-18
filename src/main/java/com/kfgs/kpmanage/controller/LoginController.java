package com.kfgs.kpmanage.controller;

import com.kfgs.kpmanage.entity.GradeIdentity;
import com.kfgs.kpmanage.entity.GradeUserinfo;
import com.kfgs.kpmanage.entity.response.CommonCode;
import com.kfgs.kpmanage.entity.response.QueryResponseResult;
import com.kfgs.kpmanage.entity.response.QueryResult;
import com.kfgs.kpmanage.mapper.GradeIdentityMapper;
import com.kfgs.kpmanage.mapper.GradeUserinfoMapper;
import com.kfgs.kpmanage.service.GradeIdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController

public class LoginController {

    @Autowired
    GradeIdentityService gradeIdentityService;

    //配置拦截字符串
    @RequestMapping("/login")
    public QueryResponseResult login(@RequestBody GradeIdentity gradeIdentity){
        HashMap map = new HashMap();
        String userid = gradeIdentity.getUserid();
        String password = gradeIdentity.getPassword();
        //模拟从数据库中查询出用户名和密码
        String loginpass = gradeIdentityService.getPasswordByID(userid);
        if (loginpass.equals(password)){
            QueryResult queryResult = new QueryResult();
            /*map.put("userid",userid);
            map.put("password",loginpass);
            queryResult.setMap(map);*/
            return new QueryResponseResult(CommonCode.SUCCESS,null);
        }else {
            return new QueryResponseResult(CommonCode.USER_CREDENTIALS_ERROR,null);
        }
    }
}
