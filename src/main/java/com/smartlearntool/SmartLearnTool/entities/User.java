package com.smartlearntool.SmartLearnTool.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String userId;

    private String userName;

    private String email;

    private String password;

    private String about;

    private boolean active;

    private boolean emailVerified;

    private boolean smsVerified;

    private String profilePath;

    private String recentOTP;

    private Date createdAt;

}
