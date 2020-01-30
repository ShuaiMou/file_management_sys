package com.practice.file_management_sys.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class User implements Serializable{
    private String email;
    private String password;
    private String gender;
    private String domain;
}
