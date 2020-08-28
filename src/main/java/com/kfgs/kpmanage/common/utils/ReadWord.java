package com.kfgs.kpmanage.common.utils;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
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

public class ReadWord {

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

    private void getContent(String text){
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
            String num = decimalFormat.format(i+1);
            //获取当前年份
            Calendar cal = Calendar.getInstance();
            String year = Integer.toString(cal.get(Calendar.YEAR)) + "A";
            String exa_id = year + userID + num;
            System.out.println(exa_id + "    "+ value + "  "+ userID + sameworkid );
        }
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
