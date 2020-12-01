package com.example.springbootdemo.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;



@Controller
@PropertySource({"classpath:application.properties"})
public class FileController {
    public static String UPLOAD_IP = "";
    public static final String PATH = "upload/";
    @Value("${web.upload-path}")
    private String filepath;

    static {

        try {
            String IP = InetAddress.getLocalHost().getHostAddress();
            UPLOAD_IP = "http://"+IP+":8080/"+PATH;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     * 实现文件上传
     * @param file
     * @return
     */
    @RequestMapping("/fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("fileName")MultipartFile file){
        String fileName = file.getOriginalFilename();

        File dest = new File(filepath + PATH + fileName);
        System.out.println(dest);
        try {
            file.transferTo(dest);
            System.out.println("文件上传成功");
            return UPLOAD_IP+fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return "false";
        }


    }

    @RequestMapping("/fileDownload")
    @ResponseBody
    public String fileDownload(String filename, HttpServletResponse response){
        System.out.println(filepath);


        File file = new File("/Users/yy/Documents/upload"+"/"+filename);
        // 配置文件下载
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        // 下载文件能正常显示中文
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 实现文件下载
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }

            System.out.println("Download the song successfully!");
        } catch(Exception e){
            System.out.println("下载失败");
        }finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

    }


        return "true";
    }




}
