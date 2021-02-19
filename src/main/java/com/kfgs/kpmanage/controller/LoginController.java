package com.kfgs.kpmanage.controller;

import com.kfgs.kpmanage.entity.GradeIdentity;
import com.kfgs.kpmanage.entity.GradeUserinfo;
import com.kfgs.kpmanage.entity.response.CommonCode;
import com.kfgs.kpmanage.entity.response.QueryResponseResult;
import com.kfgs.kpmanage.entity.response.QueryResult;
import com.kfgs.kpmanage.mapper.GradeIdentityMapper;
import com.kfgs.kpmanage.mapper.GradeUserinfoMapper;
import com.kfgs.kpmanage.service.GradeIdentityService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("/identity")
public class LoginController {

    @Autowired
    GradeIdentityService gradeIdentityService;

    //配置拦截字符串
    @PostMapping("/login")
    public QueryResponseResult login(String userid,String password){
        System.out.println(userid + password);
        HashMap map = new HashMap();
        /*String userid = gradeIdentity.getUserid();
        String password = gradeIdentity.getPassword();*/
        //模拟从数据库中查询出用户名和密码
        //判断用户名是否存在
        int sum = gradeIdentityService.userIDJudge(userid);
        if (sum == 0){
            return new QueryResponseResult(CommonCode.USER_ACCOUNT_NOT_EXIST,null);
        }
        String loginpass = gradeIdentityService.getPasswordByID(userid);
        if (loginpass.equals(password)){
            QueryResult queryResult = new QueryResult();
            map.put("userid",userid);
            map.put("password",loginpass);
            queryResult.setMap(map);
            return new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        }else {
            return new QueryResponseResult(CommonCode.USER_CREDENTIALS_ERROR,null);
        }
    }
}
