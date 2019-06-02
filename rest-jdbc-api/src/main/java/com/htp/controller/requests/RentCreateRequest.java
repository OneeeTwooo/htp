package com.htp.controller.requests;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.sql.Timestamp;
import java.util.Objects;

public class RentCreateRequest {
  private Long userId;
  private Long carId;
  private Long damageId;
  private Timestamp rentalStartDate;
  private Timestamp rentalFinishDate;
  private Timestamp createdWhen;
  private Timestamp modifyWhen;

  public RentCreateRequest() {}

  public RentCreateRequest(
      Long userId,
      Long carId,
      Long damageId,
      Timestamp rentalStartDate,
      Timestamp rentalFinishDate,
      Timestamp createdWhen,
      Timestamp modifyWhen) {
    this.userId = userId;
    this.carId = carId;
    this.damageId = damageId;
    this.rentalStartDate = rentalStartDate;
    this.rentalFinishDate = rentalFinishDate;
    this.createdWhen = createdWhen;
    this.modifyWhen = modifyWhen;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getCarId() {
    return carId;
  }

  public void setCarId(Long carId) {
    this.carId = carId;
  }

  public Long getDamageId() {
    return damageId;
  }

  public void setDamageId(Long damageId) {
    this.damageId = damageId;
  }

  public Timestamp getRentalStartDate() {
    return rentalStartDate;
  }

  public void setRentalStartDate(Timestamp rentalStartDate) {
    this.rentalStartDate = rentalStartDate;
  }

  public Timestamp getRentalFinishDate() {
    return rentalFinishDate;
  }

  public void setRentalFinishDate(Timestamp rentalFinishDate) {
    this.rentalFinishDate = rentalFinishDate;
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
    RentCreateRequest that = (RentCreateRequest) o;
    return Objects.equals(userId, that.userId)
        && Objects.equals(carId, that.carId)
        && Objects.equals(damageId, that.damageId)
        && Objects.equals(rentalStartDate, that.rentalStartDate)
        && Objects.equals(rentalFinishDate, that.rentalFinishDate)
        && Objects.equals(createdWhen, that.createdWhen)
        && Objects.equals(modifyWhen, that.modifyWhen);
  }

  @Override
  public int hashCode() {

    return Objects.hash(
        userId, carId, damageId, rentalStartDate, rentalFinishDate, createdWhen, modifyWhen);
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
