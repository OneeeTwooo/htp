package com.htp.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.sql.Timestamp;
import java.util.Objects;

public class Rent {
  private Long rentId;
  private Long userId;
  private Long carId;
  private Timestamp rentalStartDate;
  private Timestamp rentalFinishDate;
  private Timestamp createdWhen;
  private Timestamp modifyWhen;

  public Rent() {}

  public Rent(
      Long rentId,
      Long userId,
      Long carId,
      Timestamp rentalStartDate,
      Timestamp rentalFinishDate,
      Timestamp createdWhen,
      Timestamp modifyWhen) {
    this.rentId = rentId;
    this.userId = userId;
    this.carId = carId;
    this.rentalStartDate = rentalStartDate;
    this.rentalFinishDate = rentalFinishDate;
    this.createdWhen = createdWhen;
    this.modifyWhen = modifyWhen;
  }

  public Long getRentId() {

    return rentId;
  }

  public void setRentId(Long rentId) {
    this.rentId = rentId;
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
    Rent rent = (Rent) o;
    return Objects.equals(rentId, rent.rentId)
        && Objects.equals(userId, rent.userId)
        && Objects.equals(carId, rent.carId)
        && Objects.equals(rentalStartDate, rent.rentalStartDate)
        && Objects.equals(rentalFinishDate, rent.rentalFinishDate)
        && Objects.equals(createdWhen, rent.createdWhen)
        && Objects.equals(modifyWhen, rent.modifyWhen);
  }

  @Override
  public int hashCode() {

    return Objects.hash(
        rentId, userId, carId, rentalStartDate, rentalFinishDate, createdWhen, modifyWhen);
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
