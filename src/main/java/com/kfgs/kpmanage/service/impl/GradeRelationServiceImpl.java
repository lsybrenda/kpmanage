package com.kfgs.kpmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kfgs.kpmanage.entity.GradeRelation;
import com.kfgs.kpmanage.entity.GradeUserinfo;
import com.kfgs.kpmanage.mapper.GradeRelationMapper;
import com.kfgs.kpmanage.service.GradeRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lsy
 */
@Service
public class GradeRelationServiceImpl implements GradeRelationService {

    @Autowired
    GradeRelationMapper gradeRelationMapper;

    @Override
    public IPage getRelations(String pageNo, String limit) {
        Page<GradeRelation> page = new Page<>(Long.parseLong(pageNo),Long.parseLong(limit));
        IPage<GradeRelation> iPage = gradeRelationMapper.getRelationList(page);
        return iPage;
    }

    @Override
    public IPage getCandidates(String pageNo, String limit) {
        Page<GradeUserinfo> page = new Page<>(Long.parseLong(pageNo),Long.parseLong(limit));
        IPage<GradeUserinfo> iPage = gradeRelationMapper.getCandidates(page);
        return iPage;
    }

    //更新打分关系
    @Override
    public int updateRelation(String examiner,String candidate,String type, String groups) {

        int returnbak = gradeRelationMapper.updateRelation(examiner,candidate,type,groups);
        return returnbak;
    }

    @Override
    public int addRelationInfo(GradeRelation gradeRelation) {
        return gradeRelationMapper.insert(gradeRelation);
    }

    @Override
    @Transactional
    public int deleteRelationInfo(List<GradeRelation> list) {
        int res=0;
        for (int i =0;i<list.size();i++){
            GradeRelation gradeRelation = list.get(i);
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("examiner",gradeRelation.getExaminer());
            queryWrapper.eq("candidate",gradeRelation.getCandidate());
            queryWrapper.eq("type",gradeRelation.getType());
            queryWrapper.eq("groups",gradeRelation.getGroups());
            res += gradeRelationMapper.delete(queryWrapper);
        }
        return res;
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public int batchImport(MultipartFile file) throws Exception {
        List<GradeRelation> list = new ArrayList<>();
        Workbook workbook = null;
        InputStream inputStream = file.getInputStream();
        workbook = WorkbookFactory.create(inputStream);
        //确定版本
        boolean isExcel = file.getOriginalFilename().endsWith("xls")?true:false;
        isExcel = file.getOriginalFilename().endsWith("xlsx")?true:false;
        if (isExcel){
            //有多少个sheet
            int sheets = workbook.getNumberOfSheets();
            Sheet sheet = workbook.getSheetAt(0);
            //总行数
            int rowLength = sheet.getLastRowNum()+1;
            //工作表的列
            Row row = sheet.getRow(0);
            //总列数
            int colLength = row.getLastCellNum();
            for (int i=1;i<rowLength;i++){
                row = sheet.getRow(i);
                GradeRelation gradeRelation = new GradeRelation();
                gradeRelation.setExaminer(row.getCell(0)==null?"":row.getCell(0).getStringCellValue());
                gradeRelation.setCandidate(row.getCell(1)==null?"":row.getCell(1).getStringCellValue());
                gradeRelation.setType(row.getCell(2)==null?"":row.getCell(2).getStringCellValue());
                gradeRelation.setGroups(row.getCell(3)==null?"":row.getCell(3).getStringCellValue());
                list.add(gradeRelation);
            }
        }
        int res = gradeRelationMapper.insertMultiRels(list);
        if (res == list.size()){
            return 1;
        }else {
            return 0;
        }
    }

    /*@Override
    public List<GradeUserinfo> getCandidateList() {
        List<GradeUserinfo> list = gradeRelationMapper.getCandidateList();
        return list;
    }*/
}
