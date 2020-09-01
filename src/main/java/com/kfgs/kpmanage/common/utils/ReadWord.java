package com.kfgs.kpmanage.common.utils;

import com.kfgs.kpmanage.entity.GradeExamination;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.XmlException;
import org.apache.poi.hwpf.extractor.WordExtractor;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadWord {

    public final static String EXCEL_PATH_PREFIX = "static/upload/excel/";
    Map<String,String> userMap = NameToID.getNameAndID();
    static int number=0;
    public ReadWord(String filePath){

        String content = "";
        try {
            if (filePath.endsWith(".doc")){
                InputStream inputStream = new FileInputStream(new File(filePath));
                WordExtractor extractor = new WordExtractor(inputStream);
                content = extractor.getText();
                getContent(content);
                extractor.close();
            }else if (filePath.endsWith("docx")){
                OPCPackage opcPackage = POIXMLDocument.openPackage(filePath);
                POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
                content = extractor.getText();
                getContent(content);
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

    private void getContent(String text) {
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

        //将文件保存到指定位置
        String excelPath = new String("src/main/resources/" + EXCEL_PATH_PREFIX);
        String excelName = userID + ".xls";
        String[] excelTitle = {"考核条目id","考核内容","所属员工id","相同条目标记"};
        File excel = new File(excelPath+excelName);
        if (excel.exists()){
            excel.delete();
        }
        try {
            excel.createNewFile();
            //创建Excel对象
            WritableWorkbook workbook = Workbook.createWorkbook(excel);
            //HSSFWorkbook workbook = new HSSFWorkbook();
            //创建工作表单
            WritableSheet sheet = workbook.createSheet("个人考核内容",0);
            Label label = null;
            //设置列名
            for (int i=0;i < excelTitle.length;i++){
                label = new Label(i,0,excelTitle[i]);
                sheet.addCell(label);
            }
            DecimalFormat decimalFormat = new DecimalFormat("00");
            String sameworkid = "";
            String ss = "";
            //获取数据
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

                //设值
                Label labelA = null;
                Label labelB = null;
                Label labelC = null;
                Label labelD = null;
                labelA = new Label(0,i+1,exa_id);
                labelB = new Label(1,i+1,value);
                labelC = new Label(2,i+1,userID);
                labelD = new Label(3,i+1,sameworkid);
                sheet.addCell(labelA);
                sheet.addCell(labelB);
                sheet.addCell(labelC);
                sheet.addCell(labelD);
            }
            workbook.write();
            workbook.close();
            System.out.println("文件"+excelName+"写入完毕");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }

    }

}
