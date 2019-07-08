package com.cheer.exam.model;
//javabean admin
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class Admin implements Serializable {
    @NotBlank(message = "用户名不可以为空")
    @Size(min=4,max=10)
    private String username;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public Admin(){

    }

    @NotBlank(message="密码不可以为空")
    private String password;

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "admin{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
