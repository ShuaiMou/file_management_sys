package com.practice.file_management_sys.service.serviceImpl;

import com.practice.file_management_sys.domain.FMSFile;
import com.practice.file_management_sys.mapper.FileMapper;
import com.practice.file_management_sys.service.FileService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("fileService")
@CacheConfig(cacheNames = "fmsFile")
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
    @CachePut(key = "#p0")
    @Override
    public FMSFile store(String fileName, String uploaderName, String uploaderEmail, int size) {
        FMSFile fmsFile = new FMSFile();
        fmsFile.setFileName(fileName);
        fmsFile.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        fmsFile.setUploaderName(uploaderName);
        fmsFile.setUploaderEmail(uploaderEmail);
        fmsFile.setSize(size);
        int flag = fileMapper.addFile(fmsFile);
        if (flag == 1){
            return  fmsFile;
        }
        return null;

    }

    /**
     * 根据email查询上传过的文件
     *
     * @param uploaderEmail 上传者email
     * @return 成功返回  List<FMSFile> 数据
     *         失败返回  状态码 + 提示信息
     */
    @Cacheable(value = "fileList", keyGenerator = "simpleKeyGenerator",unless = "#result == null")
    @Override
    public List<FMSFile> queryUploadHistory(String uploaderEmail) {
        return fileMapper.uploadHistory(uploaderEmail);

    }

    /**
     * 根据email查询下载过的文件
     *
     * @param downloaderEmail 下载者email
     * @return 成功返回  List<FMSFile> 数据
     *         失败返回  状态码 + 提示信息
     */
    @Cacheable(value = "fileList", keyGenerator = "simpleKeyGenerator", unless = "#result == null")
    @Override
    public List<FMSFile> queryDownloadHistory(String downloaderEmail) {
        return fileMapper.downloadHistory(downloaderEmail);

    }
}
