package com.practice.file_management_sys.controller;


import com.practice.file_management_sys.domain.JsonData;
import com.practice.file_management_sys.enumClass.StateType;
import com.practice.file_management_sys.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/file")
@PropertySource("classpath:application.properties")
public class FileController {

    @Resource
    private FileService fileService;

    @Value("${file.store.path}")
    private String fileStorePath;

    @PostMapping("/upload")
    public JsonData uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        String uploaderName = request.getParameter("uploaderName");
        String uploaderEmail = request.getParameter("uploaderEmail");
        String domain = request.getParameter("domain");
        String fileName = file.getOriginalFilename();
        int size = (int) (file.getSize()/(1024*1024));

        //获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        //转换文件名称： domain + uuid
        StringBuilder finalName = new StringBuilder(domain);
        finalName.append("__").append(fileName).append(UUID.randomUUID()).append(suffix);

        File dest = new File(fileStorePath + finalName.toString());

        try {
            file.transferTo(dest);
        } catch (IOException e) {
           return JsonData.buildError(StateType.PROCESSING_EXCEPTION.getCode(), "存储异常");
        }

        try {
            fileService.store(finalName.toString(), uploaderName, uploaderEmail, size);
            return JsonData.buildSuccess();
        }catch (Exception e){
            return JsonData.buildError(StateType.PROCESSING_EXCEPTION.getCode(), "数据库插入异常");
        }
    }

}
