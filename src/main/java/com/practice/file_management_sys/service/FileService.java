package com.practice.file_management_sys.service;

import com.practice.file_management_sys.domain.JsonData;

public interface FileService {
    JsonData store(String fileName, String uploaderName, String uploaderEmail, int size);
}
