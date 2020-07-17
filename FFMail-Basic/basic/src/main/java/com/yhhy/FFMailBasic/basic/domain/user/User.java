package com.yhhy.FFMailBasic.basic.domain.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

@Validated
public class User {
    /*
     * 登陆模式
     */
    public enum LoginType {
        ACCOUNT("1", "account"), TELEPHONE("2", "telephone");

        String typeCode;
        String typeStr;

        LoginType(String typeCode, String typeStr) {
            this.typeCode = typeCode;
            this.typeStr = typeStr;
        }
    }

    @NotEmpty
    @Size(min = 6, max = 10, message = "用户名需要为6-10位")
    private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @NotEmpty
    private String userName;
    @NotEmpty
    @Pattern(regexp = "[0-9]+")
    private String telephone;
    @NotEmpty
    @Size(min = 8, max = 18, message = "密码长度为8-18位")
    private String password;

    private LoginType loginType;

    private String token;

    public User() {

    }

    public User(LoginType loginType, String account, String password) {
        this.setLoginType(loginType);
        if (loginType.equals(LoginType.ACCOUNT)) {
            this.setAccount(account);
            this.setPassword(password);
        } else {
            this.setTelephone(account);
            this.setPassword(password);
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}
