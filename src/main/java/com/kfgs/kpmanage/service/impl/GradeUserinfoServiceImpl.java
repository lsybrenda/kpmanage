package com.kfgs.kpmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kfgs.kpmanage.entity.GradeUserinfo;
import com.kfgs.kpmanage.entity.response.CommonCode;
import com.kfgs.kpmanage.entity.response.QueryResponseResult;
import com.kfgs.kpmanage.mapper.GradeUserinfoMapper;
import com.kfgs.kpmanage.service.GradeUserinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
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
public class GradeUserinfoServiceImpl implements GradeUserinfoService {

    @Autowired
    GradeUserinfoMapper gradeUserinfoMapper;

    @Override
    public IPage findAllUsers(String name,String pageNo, String limit) {
        Page<GradeUserinfo> page = new Page<>(Long.parseLong(pageNo),Long.parseLong(limit));
        QueryWrapper queryWrapper = new QueryWrapper();
        if (name != ""){
            queryWrapper.eq("name",name);
            IPage<GradeUserinfo> iPage = gradeUserinfoMapper.selectPage(page,queryWrapper);
            return iPage;
        }else{
            IPage<GradeUserinfo> iPage = gradeUserinfoMapper.selectPage(page,null);
            return iPage;
        }
    }

    //更新人员信息
    @Override
    public int updateUserInfo(String id,String name,String email,String position,String department,String isdafen){
        GradeUserinfo gradeUserinfo = new GradeUserinfo();
        QueryWrapper queryWrapper = new QueryWrapper();
        if (id != null && id != ""){
            gradeUserinfo.setId(id);
            queryWrapper.eq("id",id);
        }
        //name不为空
        gradeUserinfo.setName(name);
        gradeUserinfo.setEmail(email);
        gradeUserinfo.setPosition(position);
        gradeUserinfo.setDepartment(department);
        gradeUserinfo.setIsdafen(isdafen);
        int returnbak = gradeUserinfoMapper.update(gradeUserinfo,queryWrapper);
        return returnbak;
    }

    //根据id删除人员信息
    @Override
    public int deleteUserInfo(String ids){
        int returnbak = 1;
        String[] idlist = ids.split(",");
        int length = idlist.length;
        for(int i = 0; i< length;i++){
            returnbak = gradeUserinfoMapper.deleteById(idlist[i]);
        }
        return returnbak;
    }

    //新增单个人员信息
    @Override
    public void addUserInfo(String id,String name,String email,String position,String department,String isdafen){
        GradeUserinfo gradeUserinfo = new GradeUserinfo();
        QueryWrapper queryWrapper = new QueryWrapper();
        gradeUserinfo.setId(id);
        gradeUserinfo.setName(name);
        gradeUserinfo.setEmail(email);
        gradeUserinfo.setPosition(position);
        gradeUserinfo.setDepartment(department);
        gradeUserinfo.setIsdafen(isdafen);
        gradeUserinfo.setHiredate("");
        gradeUserinfoMapper.insert(gradeUserinfo);
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public int batchImport(MultipartFile file) throws Exception {
        List<GradeUserinfo> ss = new ArrayList<GradeUserinfo>();
        Workbook workbook = null;
        InputStream inputStream = file.getInputStream();
        workbook = WorkbookFactory.create(inputStream);
        //Workbook workbook = new HSSFWorkbook(new POIFSFileSystem(inputStream));
        //确定版本
        boolean isExcel = file.getOriginalFilename().endsWith("xls")?true:false;
        isExcel = file.getOriginalFilename().endsWith("xlsx")?true:false;
        List<Map<String,Object>> returnMap;
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
                /*row.getCell(0).setCellType(CellType.STRING);
                String cell0 = row.getCell(0).getStringCellValue().trim();*/
                GradeUserinfo gradeUserinfo = new GradeUserinfo();
                gradeUserinfo.setId(row.getCell(0)==null?"":row.getCell(0).getStringCellValue());
                gradeUserinfo.setName(row.getCell(1)==null?"":row.getCell(1).getStringCellValue());
                gradeUserinfo.setEmail(row.getCell(2)==null?"":row.getCell(2).getStringCellValue());
                gradeUserinfo.setPosition(row.getCell(3)==null?"":row.getCell(3).getStringCellValue());
                gradeUserinfo.setDepartment(row.getCell(4)==null?"":row.getCell(4).getStringCellValue());
                gradeUserinfo.setIsdafen(row.getCell(5)==null?"":row.getCell(5).getStringCellValue());
                gradeUserinfo.setHiredate(row.getCell(6)==null?"":row.getCell(6).getStringCellValue());
                ss.add(gradeUserinfo);
                //gradeUserinfoMapper.insert(gradeUserinfo);
            }
        }
        int ret = gradeUserinfoMapper.insertMultiUsers(ss);
        if (ret == ss.size()){
            return 1;
        }else{
            return 0;
        }
    }
}
