package com.kfgs.kpmanage.controller;


import com.alibaba.druid.sql.dialect.oracle.ast.expr.OracleSizeExpr;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kfgs.kpmanage.common.controller.BaseController;
import com.kfgs.kpmanage.common.utils.NameToID;
import com.kfgs.kpmanage.common.utils.ReadWord;
import com.kfgs.kpmanage.entity.GradeExamination;
import com.kfgs.kpmanage.entity.GradeUserinfo;
import com.kfgs.kpmanage.entity.response.CommonCode;
import com.kfgs.kpmanage.entity.response.QueryResponseResult;
import com.kfgs.kpmanage.entity.response.UploadCode;
import com.kfgs.kpmanage.service.GradeExaminationService;
import com.kfgs.kpmanage.service.GradeRelationService;
import com.kfgs.kpmanage.service.GradeUserinfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lsy
 */
@CrossOrigin
@RestController
@RequestMapping("/examiner")
public class GradeExaminationController extends BaseController {

    public final static  String WORD_PATH_PREFIX = "static/upload/word";
    public final static String EXCEL_PATH_PREFIX = "static/upload/excel";

    @Autowired
    GradeExaminationService gradeExaminationService;

    @Autowired
    GradeUserinfoService gradeUserinfoService;

    @Autowired
    GradeRelationService gradeRelationService;


    @ApiOperation("查询考核信息列表")
    @GetMapping("/getExamList")
    public Map getExamList(String pageIndex,String pageSize,String name){
        Map resultMap = new HashMap();
        //Map<String, Object> dataTable = getDataTable(gradeExaminationService.getExamList(pageIndex,pageSize));
        //List<GradeUserinfo> candidateList = gradeRelationService.getCandidateList();
        Map<String,Object> dataTable = getDataTable(gradeRelationService.getCandidates(pageIndex,pageSize,name));
        resultMap.put("data",dataTable);
        resultMap.put("code",20000);
        return resultMap;
    }

    @ApiOperation("根据id查询考核详细信息")
    @GetMapping("/getExamDetail")
    public Map getExamDetail(String id){
        Map resultMap = new HashMap();
        List list = gradeExaminationService.getExamDetail(id);
        resultMap.put("owner",id);
        resultMap.put("examination",list);
        return resultMap;
    }

    @ApiOperation("根据id删除某个人的考核表信息")
    @PostMapping("/deleteExamIds")
    public QueryResponseResult deleteExamIds(@RequestBody GradeExamination gradeExamination) throws Exception{
        System.out.println(gradeExamination.getOwner());
        String[] owners = gradeExamination.getOwner().split(",");
        int rebak = 0;
        rebak = gradeExaminationService.deleteExamById(owners);
        if (rebak != 0){
            return new QueryResponseResult(CommonCode.SUCCESS,null);
        }else {
            return new QueryResponseResult(CommonCode.FAIL,null);
        }
    }

    @ApiOperation(value = "清空Word文件",notes = "批量清空服务器Word文件")
    @PostMapping(value = "/wordEmpty")
    public QueryResponseResult wordEmpty() throws FileNotFoundException {
        System.out.println("开始清空word文件......");
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        String wordPath = new String("src/main/resources/" + WORD_PATH_PREFIX);
        File deleteFiles = new File(wordPath);
        File[] files = deleteFiles.listFiles();
        int num = files.length;
        for (int i=0;i<files.length;i++){
            File file = files[i];
            String filepath = file.getAbsolutePath();
            if(file.exists() && file.isFile()){
                boolean flag = false;
                flag = file.delete();
                if (flag){
                    num -= 1;
                    System.out.println("删除文件:"+ filepath);
                    flag = false;
                }
            }
        }
        if (num == 0){
            return new QueryResponseResult(CommonCode.SUCCESS,null);
        }else {
            return new QueryResponseResult(CommonCode.FAIL,null);
        }
    }

    @ApiOperation(value = "下载解析后的Excel文件",notes = "下载解析后的Excel文件")
    @PostMapping(value = "/excelDownload")
    public QueryResponseResult excelDownload() {
        return null;
    }

    @ApiOperation(value = "清空Excel文件",notes = "批量清空服务器Excel文件")
    @PostMapping(value = "/excelEmpty")
    public QueryResponseResult excelEmpty() throws FileNotFoundException {
        System.out.println("开始清空Excel文件......");
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        String excelPath = new String("src/main/resources/" + EXCEL_PATH_PREFIX);
        File deleteFiles = new File(excelPath);
        File[] files = deleteFiles.listFiles();
        int num = files.length;
        for (int i=0;i<files.length;i++){
            File file = files[i];
            String filepath = file.getAbsolutePath();
            if(file.exists() && file.isFile()){
                boolean flag = false;
                flag = file.delete();
                if (flag){
                    num -= 1;
                    System.out.println("删除文件:"+ filepath);
                    flag = false;
                }
            }
        }
        if (num == 0){
            return new QueryResponseResult(CommonCode.SUCCESS,null);
        }else {
            return new QueryResponseResult(CommonCode.FAIL,null);
        }
    }

    @ApiOperation(value = "Word文件上传",notes = "批量上传Word到服务器")
    @RequestMapping(value = "/fileUpload")
    @Transactional
    public QueryResponseResult fileUpload(@RequestParam("file") MultipartFile[] fileList) throws Exception{
        String filePath = getUploadPath();
        for (int i=0;i<fileList.length;i++){
            String fileName = fileList[i].getOriginalFilename();
            if (!fileList[i].isEmpty()){
                try(BufferedOutputStream outputStream = new BufferedOutputStream(
                        new FileOutputStream(new File(filePath + File.separator + fileName))
                )){
                    outputStream.write(fileList[i].getBytes());
                    outputStream.flush();
                }catch (FileNotFoundException e){
                    return new QueryResponseResult(UploadCode.FILE_NOT_FOUND,null);
                }catch (IOException e){
                    return new QueryResponseResult(UploadCode.FILE_IO_ERROR,null);
                }
            }else {
                return new QueryResponseResult(UploadCode.FILE_IS_NULL,null);
            }
        }
        return new QueryResponseResult(CommonCode.SUCCESS,null);
    }

    @ApiOperation(value = "解析Word文档")
    @PostMapping(value = "/getWordContent")
    public QueryResponseResult getWordContent() throws FileNotFoundException {
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        String wordPath = new String("src/main/resources/" + WORD_PATH_PREFIX);
        File upload = new File(wordPath);
        File[] files = upload.listFiles();
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            String filepath = file.getAbsolutePath();
            System.out.println("开始处理："+ filepath);
            ReadWord readWord = new ReadWord(filepath);
        }
        return new QueryResponseResult(CommonCode.SUCCESS,null);
    }
    /**
     * 获取当前系统路径
     */
    private String getUploadPath(){
        //构建上传Word文件的存放文件夹路径
        File path = null;
        String wordPath = new String("src/main/resources/" + WORD_PATH_PREFIX);
        path = new File(wordPath);
        if (!path.exists()){
            path.mkdirs();
        }
        return path.getAbsolutePath();
    }

}
