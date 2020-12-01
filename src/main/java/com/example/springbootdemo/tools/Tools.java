package com.example.springbootdemo.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Tools {

    public static void main(String[] args) {
        String filename = "D:/data/text.txt";
        getFileName(filename);

    }

    /**
     * 生成随机的文件名称
     * @return
     */
    public static String getFileName(String filename){
//        String file = "D:/data/text.txt";
        String file = filename;
        file = file.substring(file.lastIndexOf('.'));
        Random random = new Random();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
        StringBuilder tempName = new StringBuilder();
        tempName.append(simpleDateFormat.format(new Date()))
                .append(random.nextInt(100))
                .append(file);
//
//        String date = simpleDateFormat.format(new Date());
//        System.out.println(date);
        file = tempName.toString();
        System.out.println(file);
        return file;
    }

    public static String getTimeStr(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(new Date());
    }
}
