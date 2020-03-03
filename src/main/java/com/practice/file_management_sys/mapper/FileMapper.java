package com.practice.file_management_sys.mapper;

import com.practice.file_management_sys.domain.FMSFile;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface FileMapper {
    @Insert("insert into fms_file (file_name, User_email, User_username, create_time, size) values " +
            "(#{fileName}, #{uploaderEmail}, #{uploaderName}, #{createTime}, #{size})")
    int addFile(FMSFile file);

    @Select("select * from fms_file where User_email = #{uploaderEmail}")
    @Results({
            @Result(column = "file_name", property = "fileName"),
            @Result(column = "User_email", property = "uploaderEmail"),
            @Result(column = "User_username", property = "uploaderName"),
            @Result(column = "visited_times", property = "visitedTimes"),
            @Result(column = "create_time", property = "createTime"),
    })
    List<FMSFile> uploadHistory(String uploaderEmail);


    @Select("select * from fms_file where file_name in " +
            "(select fms_file_file_name from fms_download_history " +
            "where fms_user_email = #{uploaderEmail})")
    @Results({
            @Result(column = "file_name", property = "fileName"),
            @Result(column = "User_email", property = "uploaderEmail"),
            @Result(column = "User_username", property = "uploaderName"),
            @Result(column = "visited_times", property = "visitedTimes"),
            @Result(column = "create_time", property = "createTime"),
    })
    List<FMSFile> downloadHistory(String uploaderEmail);


}
