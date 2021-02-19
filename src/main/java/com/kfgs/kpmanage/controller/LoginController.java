package com.kfgs.kpmanage.controller;

import com.kfgs.kpmanage.KpmanageApplication;
import com.kfgs.kpmanage.entity.GradeIdentity;
import com.kfgs.kpmanage.entity.GradeUserinfo;
import com.kfgs.kpmanage.entity.response.CommonCode;
import com.kfgs.kpmanage.entity.response.QueryResponseResult;
import com.kfgs.kpmanage.entity.response.QueryResult;
import com.kfgs.kpmanage.mapper.GradeIdentityMapper;
import com.kfgs.kpmanage.mapper.GradeUserinfoMapper;
import com.kfgs.kpmanage.service.GradeIdentityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("/identity")
public class LoginController {

    @Autowired
    GradeIdentityService gradeIdentityService;

    private static Logger logger = Logger.getLogger(KpmanageApplication.class);

    //配置拦截字符串
    @PostMapping("/login")
    public QueryResponseResult login(String userid,String password){
        logger.info("用户"+userid+"开始登录...");
        System.out.println(userid + password);
        HashMap map = new HashMap();
        /*String userid = gradeIdentity.getUserid();
        String password = gradeIdentity.getPassword();*/
        //模拟从数据库中查询出用户名和密码
        //判断用户名是否存在
        int sum = gradeIdentityService.userIDJudge(userid);
        if (sum == 0){
            logger.error(userid+"账号不存在");
            return new QueryResponseResult(CommonCode.USER_ACCOUNT_NOT_EXIST,null);
        }
        String loginpass = gradeIdentityService.getPasswordByID(userid);
        if (loginpass.equals(password)){
            QueryResult queryResult = new QueryResult();
            map.put("userid",userid);
            map.put("password",loginpass);
            queryResult.setMap(map);
            logger.info(userid+"登录成功");
            return new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        }else {
            logger.error(userid+"登录失败");
            return new QueryResponseResult(CommonCode.USER_CREDENTIALS_ERROR,null);
        }
    }
}
