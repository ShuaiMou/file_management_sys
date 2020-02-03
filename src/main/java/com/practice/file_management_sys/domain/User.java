package com.practice.file_management_sys.domain;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{
    private String email;
    private String password;
    private String gender;
    private String domain;
}
