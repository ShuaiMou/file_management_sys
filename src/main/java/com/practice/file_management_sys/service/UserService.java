package com.practice.file_management_sys.service;

import com.practice.file_management_sys.domain.JsonData;
import com.practice.file_management_sys.domain.User;
import exception.BusinessException;

public interface UserService {
    JsonData checkLogin(String email, String password) throws BusinessException;

    JsonData register(User user, String verificationCode) throws BusinessException;
    JsonData updatePersonalInfo(User user);
}
