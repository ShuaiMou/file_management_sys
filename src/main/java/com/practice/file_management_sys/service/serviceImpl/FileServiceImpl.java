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

@Service("fileService")
public class FileServiceImpl implements FileService{

    @Resource
    private FileMapper fileMapper;

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
}
