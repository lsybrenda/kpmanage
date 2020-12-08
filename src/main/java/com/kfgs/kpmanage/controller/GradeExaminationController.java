package com.kfgs.kpmanage.controller;


import com.alibaba.druid.sql.dialect.oracle.ast.expr.OracleSizeExpr;
import com.kfgs.kpmanage.common.controller.BaseController;
import com.kfgs.kpmanage.common.utils.NameToID;
import com.kfgs.kpmanage.common.utils.ReadWord;
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

    @Autowired
    GradeExaminationService gradeExaminationService;

    @Autowired
    GradeUserinfoService gradeUserinfoService;

    @Autowired
    GradeRelationService gradeRelationService;


    @ApiOperation("查询考核信息列表")
    @GetMapping("/getExamList")
    public Map getExamList(String pageIndex,String pageSize){
        Map resultMap = new HashMap();
        //Map<String, Object> dataTable = getDataTable(gradeExaminationService.getExamList(pageIndex,pageSize));
        //List<GradeUserinfo> candidateList = gradeRelationService.getCandidateList();
        Map<String,Object> dataTable = getDataTable(gradeRelationService.getCandidates(pageIndex,pageSize));
        resultMap.put("data",dataTable);
        resultMap.put("code",20000);
        return resultMap;
    }

    @ApiOperation("根据id查询考核详细信息")
    @GetMapping("/getExamDetail")
    public Map getExamDetail(String owner){
        Map resultMap = new HashMap();
        List list = gradeExaminationService.getExamDetail(owner);
        resultMap.put("owner",owner);
        resultMap.put("examination",list);
        return resultMap;
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
    @RequestMapping(value = "/getWordContent")
    public void getWordContent() throws FileNotFoundException {
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        String wordPath = new String("src/main/resources/" + WORD_PATH_PREFIX);
        File upload = new File(wordPath);
        File[] files = upload.listFiles();
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            String filepath = file.getAbsolutePath();
            System.out.println(filepath);
            ReadWord readWord = new ReadWord(filepath);
        }
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
