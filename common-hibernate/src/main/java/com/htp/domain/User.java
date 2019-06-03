package com.htp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_user")
  private Long userId;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "id_passport")
  private String passportId;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "user_name")
  private String userName;

  @Column(name = "user_password")
  private String userPassword;

  @Column(name = "created_when")
  private Timestamp createdWhen;

  @Column(name = "modify_when")
  private Timestamp modifyWhen;

  @Column(name = "is_deleted")
  private String isDeleted;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_role")
  private Role role;

  public User() {}

  public User(
      String firstName,
      String lastName,
      String passportId,
      String phoneNumber,
      String userName,
      String userPassword,
      Timestamp createdWhen,
      Timestamp modifyWhen,
      String isDeleted,
      Role role) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.passportId = passportId;
    this.phoneNumber = phoneNumber;
    this.userName = userName;
    this.userPassword = userPassword;
    this.createdWhen = createdWhen;
    this.modifyWhen = modifyWhen;
    this.isDeleted = isDeleted;
    this.role = role;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
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
    return "User{"
        + "userId="
        + userId
        + ", firstName='"
        + firstName
        + '\''
        + ", lastName='"
        + lastName
        + '\''
        + ", passportId='"
        + passportId
        + '\''
        + ", phoneNumber='"
        + phoneNumber
        + '\''
        + ", userName='"
        + userName
        + '\''
        + ", userPassword='"
        + userPassword
        + '\''
        + ", createdWhen="
        + createdWhen
        + ", modifyWhen="
        + modifyWhen
        + ", isDeleted='"
        + isDeleted
        + '\''
        + '}';
  }
}
