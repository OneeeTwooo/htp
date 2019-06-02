package com.htp.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;
import java.util.Objects;

public class User {
  private Long userId;
  private String firstName;
  private String lastName;
  private String passportId;
  private String phoneNumber;
  private String userName;
  private String userPassword;
  private Timestamp createdWhen;
  private Timestamp modifyWhen;
  private String isDeleted;

  public User() {}

  public User(
      Long userId,
      String firstName,
      String lastName,
      String passportId,
      String phoneNumber,
      String userName,
      String userPassword,
      Timestamp createdWhen,
      Timestamp modifyWhen,
      String isDeleted) {
    this.userId = userId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.passportId = passportId;
    this.phoneNumber = phoneNumber;
    this.userName = userName;
    this.userPassword = userPassword;
    this.createdWhen = createdWhen;
    this.modifyWhen = modifyWhen;
    this.isDeleted = isDeleted;
  }

  public String getIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(String isDeleted) {
    this.isDeleted = isDeleted;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPassportId() {
    return passportId;
  }

  public void setPassportId(String passportId) {
    this.passportId = passportId;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  public Timestamp getCreatedWhen() {
    return createdWhen;
  }

  public void setCreatedWhen(Timestamp createdWhen) {
    this.createdWhen = createdWhen;
  }

  public Timestamp getModifyWhen() {
    return modifyWhen;
  }

  public void setModifyWhen(Timestamp modifyWhen) {
    this.modifyWhen = modifyWhen;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(userId, user.userId)
        && Objects.equals(firstName, user.firstName)
        && Objects.equals(lastName, user.lastName)
        && Objects.equals(passportId, user.passportId)
        && Objects.equals(phoneNumber, user.phoneNumber)
        && Objects.equals(userName, user.userName)
        && Objects.equals(userPassword, user.userPassword)
        && Objects.equals(createdWhen, user.createdWhen)
        && Objects.equals(modifyWhen, user.modifyWhen)
        && Objects.equals(isDeleted, user.isDeleted);
  }

  @Override
  public int hashCode() {

    return Objects.hash(
        userId,
        firstName,
        lastName,
        passportId,
        phoneNumber,
        userName,
        userPassword,
        createdWhen,
        modifyWhen,
        isDeleted);
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
