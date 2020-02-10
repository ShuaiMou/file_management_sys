package com.practice.file_management_sys.service.serviceImpl;

import com.practice.file_management_sys.domain.FMSFile;
import com.practice.file_management_sys.domain.JsonData;
import com.practice.file_management_sys.enumClass.StateType;
import com.practice.file_management_sys.mapper.FileMapper;
import com.practice.file_management_sys.service.FileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("fileService")
public class FileServiceImpl implements FileService{

    @Resource
    private FileMapper fileMapper;

    /**
     * 上传存储文件
     *
     * @param fileName 文件名
     * @param uploaderName 上传者名字
     * @param uploaderEmail 上传者邮箱
     * @param size 文件大小（kb）
     * @return 状态码 + 提示信息
     */
    @Override
    public JsonData store(String fileName, String uploaderName, String uploaderEmail, int size) {
        FMSFile fmsFile = new FMSFile();
        fmsFile.setFileName(fileName);
        fmsFile.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        fmsFile.setUploaderName(uploaderName);
        fmsFile.setUploaderEmail(uploaderEmail);
        fmsFile.setSize(size);
        try{
            fileMapper.addFile(fmsFile);
            return JsonData.buildSuccess();
        }catch(Exception e){
            return JsonData.buildError(StateType.PROCESSING_EXCEPTION.getCode(), "数据库存储异常");
        }

    }

    /**
     * 根据email查询上传过的文件
     *
     * @param uploaderEmail 上传者email
     * @return 成功返回  List<FMSFile> 数据
     *         失败返回  状态码 + 提示信息
     */
    @Override
    public JsonData queryUploadHistory(String uploaderEmail) {
        try {
            List<FMSFile> files = fileMapper.uploadHistory(uploaderEmail);
            return JsonData.buildSuccess(files);
        }catch (Exception e){
            return JsonData.buildError(StateType.PROCESSING_EXCEPTION.getCode(), "数据库查询异常");
        }
    }

    /**
     * 根据email查询下载过的文件
     *
     * @param downloaderEmail 下载者email
     * @return 成功返回  List<FMSFile> 数据
     *         失败返回  状态码 + 提示信息
     */
    @Override
    public JsonData queryDownloadHistory(String downloaderEmail) {
        try {
            List<FMSFile> files = fileMapper.downloadHistory(downloaderEmail);
            return JsonData.buildSuccess(files);
        }catch (Exception e){
            return JsonData.buildError(StateType.PROCESSING_EXCEPTION.getCode(), "数据库查询异常");
        }
    }
}
