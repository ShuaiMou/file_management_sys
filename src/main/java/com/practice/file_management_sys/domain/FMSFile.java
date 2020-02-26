package com.practice.file_management_sys.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FMSFile implements Serializable {

    private long fileID;

    /**
     * 名字作为唯一ID，包含分类
     */
    private String fileName;

    /**
     * 上传者名字：外键
     */
    private String uploaderName;


    /**
     * 上传者邮箱：外键
     */
    private String uploaderEmail;

    /**
     * 被访问的次数
     */
    private long visitedTimes;

    /**
     * 上传时间
     */
    private String createTime;

    /**
     * 文件大小
     */
    private int size;

}
