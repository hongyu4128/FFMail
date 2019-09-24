package com.yhhy.FFMailBasic.basic.domain.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

@Validated
public class User {
  @NotEmpty
  @Size(min = 6, max = 10, message = "用户名需要为6-10位")
  private String userName;
  @NotEmpty
  @Pattern(regexp = "[0-9]+")
  private String telephone;
  @NotEmpty
  @Size(min = 8, max = 18, message = "密码长度为8-18位")
  private String password;

  public User() {

  }

  public User(String userName, String telephone, String password) {
    this.setUserName(userName);
    this.setTelephone(telephone);
    this.setPassword(password);
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
}
