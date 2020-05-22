package com.kfgs.kpmanage.controller;


import com.kfgs.kpmanage.service.GradeAllService;
import io.swagger.annotations.Api;
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
@Api("总分表测试")
@RestController
@RequestMapping("/grade-all")
public class GradeAllController {

    @Autowired
    GradeAllService gradeAllService;

    @ApiOperation("查询总分")
    @GetMapping("/findAllScores")
    public Map findAllScores(){
        Map resultMap = new HashMap();
        resultMap.put("code",20000);
        resultMap.put("data","success");
        return resultMap;
    }
}
