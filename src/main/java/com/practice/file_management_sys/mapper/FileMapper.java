package com.practice.file_management_sys.mapper;

import com.practice.file_management_sys.domain.FMSFile;
import org.apache.ibatis.annotations.Insert;




public interface FileMapper {
    @Insert("insert into fms_file (file_name, User_email, User_username, create_time, size) values " +
            "(#{fileName}, #{uploaderEmail}, #{uploaderName}, #{createTime}, #{size})")
    void addFile(FMSFile file);
}
