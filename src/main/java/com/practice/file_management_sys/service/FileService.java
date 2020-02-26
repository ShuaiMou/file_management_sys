package com.practice.file_management_sys.service;

import com.practice.file_management_sys.domain.FMSFile;

import java.util.List;

public interface FileService {

    FMSFile store(String fileName, String uploaderName, String uploaderEmail, int size);
    List<FMSFile> queryUploadHistory(String uploaderEmail);
    List<FMSFile> queryDownloadHistory(String downloaderEmail);

}
