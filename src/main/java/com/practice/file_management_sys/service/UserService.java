package com.practice.file_management_sys.service;

import com.practice.file_management_sys.domain.JsonData;
import com.practice.file_management_sys.domain.User;

public interface UserService {
    JsonData checkLogin(String email, String password);

    JsonData register(User user, String verificationCode);
    JsonData updatePersonalInfo(String email, String gender, String domain, String password);
}
