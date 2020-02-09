package com.practice.file_management_sys.domain;

import com.practice.file_management_sys.enumClass.Membership;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{

    private String email;
    private String username;
    private String password;
    private String gender;
    private String domain;
    private String createTime;
    private String updateTime;
    private Membership level;
    private int uploadCounter;
    private int downloadCounter;

}
