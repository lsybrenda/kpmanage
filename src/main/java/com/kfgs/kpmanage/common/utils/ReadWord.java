package com.kfgs.kpmanage.common.utils;

import com.kfgs.kpmanage.entity.GradeExamination;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.XmlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.apache.poi.hwpf.extractor.WordExtractor;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadWord {

    Map<String,String> userMap = NameToID.getNameAndID();
    static int number=0;
    public ReadWord(String filePath){

        String candidate = filePath.substring(0,filePath.indexOf("."));
        String content = "";
        try {
            if (filePath.endsWith(".doc")){
                InputStream inputStream = new FileInputStream(new File(filePath));
                WordExtractor extractor = new WordExtractor(inputStream);
                content = extractor.getText();
                getContent(candidate,content);
                extractor.close();
            }else if (filePath.endsWith("docx")){
                OPCPackage opcPackage = POIXMLDocument.openPackage(filePath);
                POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
                content = extractor.getText();
                getContent(candidate,content);
                extractor.close();
            }else {
                return;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlException e) {
            e.printStackTrace();
        } catch (OpenXML4JException e) {
            e.printStackTrace();
        }
    }

    private void getContent(String candidate, String text){
        List<GradeExamination> list = new ArrayList<>();
        GradeExamination gradeExamination = new GradeExamination();
        String[] strs = text.split("\n");
        String name = "";
        ArrayList<String> yjmbList = new ArrayList<String>();
        boolean isyjmb = false;
        for (int i=0;i<strs.length;i++){
            String line = strs[i];
            if (isyjmb){
                if (line.startsWith("得分小计")){
                    break;
                }
                if (line.startsWith("二、工作能力") || line.startsWith("二、核心能力")){
                    break;
                }
                if (line.indexOf("4、5~超越目标") != -1){
                    line = line.substring(0,line.indexOf("4、5~超越目标"));
                }
                if (line.indexOf("4-5~超越目标") != -1){
                    line = line.substring(0,line.indexOf("4-5~超越目标"));
                }
                line = line.trim();
                if (!"".equals(line)){
                    number ++;
                    yjmbList.add(line);
                }
                continue;
            }
            if (line.startsWith("姓名") && line.indexOf("部门") != -1){
                name = line.substring(2,line.indexOf("部门")).trim();
            }
            if ("目标细分\t分数（10分为最高分）\r".equals(line)){
                isyjmb = true;
            }
            if ("目标细分".equals(line)){
                isyjmb = true;
            }
        }
        String userID = userMap.get(name);
        DecimalFormat decimalFormat = new DecimalFormat("00");
        String sameworkid = "";
        String ss = "";
        for (int i=0;i<yjmbList.size();i++){
            sameworkid = "";
            ss = "";
            String value = yjmbList.get(i).replaceAll("\\s","");
            if (value.endsWith("】")){
                ss = value.substring(value.length()-7);
                sameworkid = ss.substring(1,7);
                value = value.substring(0,value.length()-7);
            }
            Pattern pattern = Pattern.compile("[0-9]*");
            Matcher isNum = pattern.matcher(value.charAt(0)+"");
            if (!isNum.matches()){
                String thisNum = Integer.toString(i+1)+".";
                value = thisNum.concat(value);
            }
            String num = decimalFormat.format(i+1);
            //获取当前年份
            Calendar cal = Calendar.getInstance();
            String year = Integer.toString(cal.get(Calendar.YEAR)) + "A";
            String exa_id = year + userID + num;
            System.out.println(exa_id + "    "+ value + "   "+ userID + sameworkid );

            //创建Excel对象
            HSSFWorkbook workbook = new HSSFWorkbook();
            //创建工作表单
            HSSFSheet sheet = workbook.createSheet("个人考核内容");
            //创建HSSFRow对象
            HSSFRow row = sheet.createRow(0);
            //创建单元格，设置表头
            HSSFCell cell = row.createCell(0);
            cell.setCellValue("考核条目id");
            cell = row.createCell(1);
            cell.setCellValue("考核内容");
            cell = row.createCell(2);
            cell.setCellValue("所属员工id");
            cell = row.createCell(3);
            cell.setCellValue("相同条目标记");

            writeToExcel(candidate,exa_id,value,userID,sameworkid);
        }
    }

    /**
     * 将解析的Word内容写进Excel
     */
    public void writeToExcel(String candidate,String exa_id,String value,String userID,String sameworkid){


    }

    /*public static void main(String[] args) throws FileNotFoundException {
        *//*File path = new File(ResourceUtils.getURL("classpath:").getPath());;
        File upload = new File(path.getAbsolutePath(),"static/upload/");
        File[] files = upload.listFiles();
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            new ReadWord(file.getAbsolutePath());
            //System.out.println(i);
        }*//*

        HashMap<String,String> map =new ReadWord("").userMap;
        //String id = NameToID.gradeUserinfoMapper.getIdByName("马天旗");
    }*/
}
