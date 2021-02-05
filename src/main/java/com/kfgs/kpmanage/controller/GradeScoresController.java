package com.kfgs.kpmanage.controller;


import com.kfgs.kpmanage.common.controller.BaseController;
import com.kfgs.kpmanage.entity.response.QueryResponseResult;
import com.kfgs.kpmanage.service.GradeScoresService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lsy
 */
@CrossOrigin
@RestController
@RequestMapping("/score")
public class GradeScoresController extends BaseController {

    @Autowired
    GradeScoresService gradeScoresService;

    @ApiOperation("查询被考核人列表")
    @GetMapping("/getCandidates")
    public Map getCandidates(String pageIndex, String pageSize){
        Map resultMap = new HashMap();
        Map<String, Object> dataTable = getDataTable(gradeScoresService.getCandidates(pageIndex,pageSize));
        resultMap.put("data",dataTable);
        resultMap.put("code",20000);
        return resultMap;
    }
}
