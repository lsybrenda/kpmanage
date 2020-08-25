package com.kfgs.kpmanage.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.kfgs.kpmanage.common.controller.BaseController;
import com.kfgs.kpmanage.entity.GradeRelation;
import com.kfgs.kpmanage.entity.GradeUserinfo;
import com.kfgs.kpmanage.entity.response.CommonCode;
import com.kfgs.kpmanage.entity.response.QueryResponseResult;
import com.kfgs.kpmanage.service.GradeRelationService;
import com.kfgs.kpmanage.service.GradeUserinfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    GradeUserinfoService gradeUserinfoService;

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

    @ApiOperation("新增单条考核信息")
    @GetMapping("/addRelationInfo")
    public QueryResponseResult addRelationInfo(String examiner, String candidate, String type, String groups) throws Exception{
        GradeRelation gradeRelation = new GradeRelation();
        gradeRelation.setExaminer(examiner);
        gradeRelation.setCandidate(candidate);
        gradeRelation.setType(type);
        gradeRelation.setGroups(groups);
        int res = gradeRelationService.addRelationInfo(gradeRelation);
        if (res == 1){
            return new QueryResponseResult(CommonCode.SUCCESS,null);
        }else {
            return new QueryResponseResult(CommonCode.FAIL,null);
        }
    }

    @ApiOperation("删除考核信息")
    @PostMapping("/deleteRelationInfo")
    public QueryResponseResult deleteRelationInfo(@RequestBody String params) throws Exception{
        List<GradeRelation> gradeRelations = JSONArray.parseArray(params,GradeRelation.class);
        List<GradeRelation> delRelationList = new ArrayList<>();
        for (int i=0;i<gradeRelations.size();i++){
            GradeRelation gradeRelation = gradeRelations.get(i);
            String examinerName = gradeRelation.getExaminer();
            String candidateName = gradeRelation.getCandidate();
            String type = gradeRelation.getType();
            String groups = gradeRelation.getGroups();
            String examiner = gradeUserinfoService.getIdByName(examinerName);
            String candidate = gradeUserinfoService.getIdByName(candidateName);
            GradeRelation gradeRelation1 = new GradeRelation();
            gradeRelation1.setExaminer(examiner);
            gradeRelation1.setCandidate(candidate);
            gradeRelation1.setType(type);
            gradeRelation1.setGroups(groups);
            delRelationList.add(gradeRelation1);
        }
        int returnbak = gradeRelationService.deleteRelationInfo(delRelationList);
        if (returnbak == delRelationList.size()){
            return new QueryResponseResult(CommonCode.SUCCESS,null);
        }else {
            return new QueryResponseResult(CommonCode.FAIL,null);
        }
    }
}
