package com.shishir.iothooks.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.xml.bind.v2.TODO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="users")
@Table(name="users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="username", length=8)
    private String username;

    @Column(name="firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;

    @Email
    private String emailid;

    @Column(name="role")
    private String role;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // annotation to hide password in GET request
    private String password;

    //ToDo - add timezone option in user's profile


}
