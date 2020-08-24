package com.kfgs.kpmanage.controller;


import com.fasterxml.jackson.databind.ser.Serializers;
import com.kfgs.kpmanage.common.controller.BaseController;
import com.kfgs.kpmanage.service.GradeRelationService;
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
@RequestMapping("/relation")
public class GradeRelationController extends BaseController {

    @Autowired
    GradeRelationService gradeRelationService;

    @ApiOperation("查询关系列表")
    @GetMapping("/getRelations")
    public Map getRelations(String pageIndex,String pageSize){
        Map resultMap = new HashMap();
        Map<String, Object> dataTable = getDataTable(gradeRelationService.getRelations(pageIndex,pageSize));
        resultMap.put("data",dataTable);
        resultMap.put("code",20000);
        return resultMap;
    }

    @ApiOperation("更新打分关系")
    @GetMapping("/updateRelation")
    public int updateRelation(String examiner,String candidate,String type,String groups){
        int returnbak = gradeRelationService.updateRelation(examiner,candidate,type,groups);
        return returnbak;
    }
}
