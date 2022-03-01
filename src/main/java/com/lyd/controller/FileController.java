package com.lyd.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@RestController
@RequestMapping("/file")
@Slf4j
@Api(tags = "文件上传下载接口")
public class FileController {

    @Value("${filepath}")
    private String filepath;

    @PostMapping(value = "/upload")
    @ApiOperation(value = "文件上传")
    @CrossOrigin
    public String uploading(@RequestParam("file")MultipartFile file) {
        File targetFile = new File(filepath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        try (FileOutputStream out = new FileOutputStream(filepath +"/"+ file.getOriginalFilename())) {
            out.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            log.error("文件" + filepath + file.getOriginalFilename() + "上传失败");
            return "uploading failure";
        }
        log.info("文件" + filepath +"\\"+ file.getOriginalFilename() + "上传成功");
        return "uploading success";
    }

    @RequestMapping("/download")
    @ApiOperation(value = "文件下载")
    @CrossOrigin
    public void download(@RequestParam(value = "需要下载的文件名") String filename,HttpServletResponse response) throws UnsupportedEncodingException {
        File file = new File(filepath + "/" + filename + ".txt");
        log.info("请求下载路径为" + filepath + "\\" + filename + ".txt");
        if (file.exists()) {
            response.setContentType("application/octet-stream");
            response.setHeader("content-type", "application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(filename+".txt","utf8"));
            byte[] buffer = new byte[1024];
            OutputStream os = null;
            try (FileInputStream fis = new FileInputStream(file);
                 BufferedInputStream bis = new BufferedInputStream(fis);) {
                os = response.getOutputStream();
                int i = bis.read();
                while (i != -1) {
                    os.write(buffer);
                    i = bis.read(buffer);
                }
                log.info("文件" + filename + ".txt下载成功");
            } catch (Exception e) {
                e.printStackTrace();
                log.error("文件" + filename + ".txt 下载失败");
            }
        } else {
            log.error("文件" + filename + ".txt 不存在");
        }
    }

}
