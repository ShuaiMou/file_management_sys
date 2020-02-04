package com.practice.file_management_sys.mapper;

import com.practice.file_management_sys.domain.User;
import org.apache.ibatis.annotations.*;

public interface UserMapper {

    @Insert("insert into fms_user(email, gender, password, domain) values (#{email}, #{gender}, #{password} ,#{domain})")
    void addUser(User user);

    @Select("select * from fms_user where email = #{email}")
    User findByEmail(@Param("email") String email);

    @Update("update fms_user set domain = #{domain}, gender=#{gender} where email=#{email}")
    int updateInformation(@Param("email") String email, @Param("gender") String gender, @Param("domain") String domain);

}
