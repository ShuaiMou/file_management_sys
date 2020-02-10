package com.practice.file_management_sys.controller;


import com.practice.file_management_sys.domain.JsonData;
import com.practice.file_management_sys.enumClass.StateType;
import com.practice.file_management_sys.service.FileService;
import com.practice.file_management_sys.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/file")
@PropertySource("classpath:application.properties")
public class FileController {

    @Resource
    private FileService fileService;

    @Value("${file.store.path}")
    private String fileStorePath;

    /**
     * 功能：文件上传，解析参数
     *
     * @param file 文件
     * @param request 请求对象
     * @return 状态码 + 提示信息
     */
    @PostMapping("/upload")
    public JsonData uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        String uploaderName = request.getParameter("uploaderName");
        String uploaderEmail = request.getParameter("uploaderEmail");
        String domain = request.getParameter("domain");
        String fileName = file.getOriginalFilename().replace("_","" );
        int size = (int) (file.getSize()/1024);

        //获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        //转换文件名称： domain + uuid
        StringBuilder finalName = new StringBuilder(domain);
        finalName.append("_").append(fileName).append(fileName).append(UUIDUtils.getUUID()).append(suffix);

        File dest = new File(fileStorePath + finalName.toString());

        try {
            file.transferTo(dest);
        } catch (IOException e) {
           return JsonData.buildError(StateType.PROCESSING_EXCEPTION.getCode(), "存储异常");
        }

        return fileService.store(finalName.toString(), uploaderName, uploaderEmail, size);

    }

    /**
     *查询上传历史
     *
     * @param uploaderEmail 上传者email
     * @return 一组FMSFile对象
     */
    @GetMapping("/uploadHistory")
    public JsonData uploadHistory(String uploaderEmail){
        return fileService.queryUploadHistory(uploaderEmail);
    }

    /**
     *查询下载历史
     *
     * @param downloaderEmail 下载者email
     * @return 一组FMSFile对象
     */
    @GetMapping("/downloadHistory")
    public JsonData downloadHistory(String downloaderEmail){
        return fileService.queryDownloadHistory(downloaderEmail);
    }


}
