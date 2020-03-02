package com.practice.file_management_sys.controller;


import com.practice.file_management_sys.domain.FMSFile;
import com.practice.file_management_sys.domain.JsonData;
import com.practice.file_management_sys.enumClass.StateType;
import com.practice.file_management_sys.service.FileService;
import com.practice.file_management_sys.utils.UUIDUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/file")
@PropertySource("classpath:config/application.properties")
@Api(tags = "文件管理")
public class FileController {

    @Resource
    private FileService fileService;

    @Value("${file.store.path}")
    private String fileStorePath;

    @PostMapping("/upload")
    @ApiOperation(value = "上传文件", notes = "上传文件到服务器")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "上传的文件", required = true, paramType = "form", dataType = "MultipartFile"),
            @ApiImplicitParam(name = "uploaderName", value = "上传者用户名", required = true, paramType = "query", dataType = "字符串"),
            @ApiImplicitParam(name = "uploaderEmail", value ="上传者账号-邮箱",required = true, paramType = "query", dataType = "字符串"),
            @ApiImplicitParam(name = "domain", value ="文件所属领域",required = true, paramType = "query", dataType = "字符串")
    })
    @ApiResponses({
            @ApiResponse(code = 201, message = "new resource is created", response = FMSFile.class),
            @ApiResponse(code = 500, message = "服务器内部异常", response = JsonData.class)
    })
    public JsonData uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
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
        file.transferTo(dest);
        FMSFile fmsFile = fileService.store(finalName.toString(), uploaderName, uploaderEmail, size);
        return JsonData.buildSuccess(StateType.CREATED.getCode(), fmsFile,StateType.CREATED.value());

    }

    /**
     *查询上传历史
     *
     * @param uploaderEmail 上传者email
     * @return 一组FMSFile对象
     */
    @GetMapping("/uploadHistory")
    @ApiOperation(value = "查询上传历史",notes = "用户查询自己的上传文件")
    @ApiImplicitParam(name = "uploaderEmail", value = "用户唯一标识符-邮箱", required = true, paramType = "query", dataType = "字符串")
    @ApiResponse(code = 200, message = "ok", response = List.class)
    public JsonData uploadHistory(String uploaderEmail){
        List<FMSFile> fmsFiles = fileService.queryUploadHistory(uploaderEmail);
        return JsonData.buildSuccess(fmsFiles);
    }

    /**
     *查询下载历史
     *
     * @param downloaderEmail 下载者email
     * @return 一组FMSFile对象
     */
    @GetMapping("/downloadHistory")
    @ApiOperation(value = "查询下载历史",notes = "用户查询自己的下载过的文件")
    @ApiImplicitParam(name = "downloaderEmail", value = "用户唯一标识符-邮箱", required = true, paramType = "query", dataType = "字符串")
    @ApiResponse(code = 200, message = "ok", response = List.class)
    public JsonData downloadHistory(String downloaderEmail){
        List<FMSFile> fmsFiles = fileService.queryDownloadHistory(downloaderEmail);
        return JsonData.buildSuccess(fmsFiles);
    }


}
