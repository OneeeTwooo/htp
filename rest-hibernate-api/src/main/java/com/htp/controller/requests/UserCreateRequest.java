package com.htp.controller.requests;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.sql.Timestamp;
import java.util.Objects;

public class UserCreateRequest {
  private String firstName;
  private String lastName;
  private String passportId;
  private String phoneNumber;
  private String userName;
  private String userPassword;
  private Timestamp createdWhen;
  private Timestamp modifyWhen;
  private String isDeleted;

  public UserCreateRequest() {}

  public UserCreateRequest(
      String firstName,
      String lastName,
      String passportId,
      String phoneNumber,
      String userName,
      String userPassword,
      Timestamp createdWhen,
      Timestamp modifyWhen,
      String isDeleted) {
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

  public String getIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(String isDeleted) {
    this.isDeleted = isDeleted;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserCreateRequest that = (UserCreateRequest) o;
    return Objects.equals(firstName, that.firstName)
        && Objects.equals(lastName, that.lastName)
        && Objects.equals(passportId, that.passportId)
        && Objects.equals(phoneNumber, that.phoneNumber)
        && Objects.equals(userName, that.userName)
        && Objects.equals(userPassword, that.userPassword)
        && Objects.equals(createdWhen, that.createdWhen)
        && Objects.equals(modifyWhen, that.modifyWhen)
        && Objects.equals(isDeleted, that.isDeleted);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
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
    return ToStringBuilder.reflectionToString(this);
  }
}
