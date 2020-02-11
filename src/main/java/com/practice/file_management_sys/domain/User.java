package com.practice.file_management_sys.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.practice.file_management_sys.enumClass.Membership;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable{

    @JsonProperty("account")
    private String email;


    private String username;

    @JsonIgnore
    private String password;

    private String gender;

    private String domain;

    private String createTime;

    private String updateTime;

    private Membership level;

    private int uploadCounter;

    private int downloadCounter;

}
