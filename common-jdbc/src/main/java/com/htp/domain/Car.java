package com.htp.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class Car {
  private Long car_id;
  private String gosNumber;
  private String mark;
  private String model;
  private Date yearOut;
  private String type;
  private Float fullCost;
  private Float costOneDay;
  private String isDeleted;
  private Timestamp createdWhen;
  private Timestamp modifyWhen;

  public Car() {}

  public Car(
      Long car_id,
      String gosNumber,
      String mark,
      String model,
      Date yearOut,
      String type,
      Float fullCost,
      Float costOneDay,
      String isDeleted,
      Timestamp createdWhen,
      Timestamp modifyWhen) {
    this.car_id = car_id;
    this.gosNumber = gosNumber;
    this.mark = mark;
    this.model = model;
    this.yearOut = yearOut;
    this.type = type;
    this.fullCost = fullCost;
    this.costOneDay = costOneDay;
    this.isDeleted = isDeleted;
    this.createdWhen = createdWhen;
    this.modifyWhen = modifyWhen;
  }

  public String getIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(String isDeleted) {
    this.isDeleted = isDeleted;
  }

  public Long getCar_id() {
    return car_id;
  }

  public void setCar_id(Long car_id) {
    this.car_id = car_id;
  }

  public String getGosNumber() {
    return gosNumber;
  }

  public void setGosNumber(String gosNumber) {
    this.gosNumber = gosNumber;
  }

  public String getMark() {
    return mark;
  }

  public void setMark(String mark) {
    this.mark = mark;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public Date getYearOut() {
    return yearOut;
  }

  public void setYearOut(Date yearOut) {
    this.yearOut = yearOut;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Float getFullCost() {
    return fullCost;
  }

  public void setFullCost(Float fullCost) {
    this.fullCost = fullCost;
  }

  public Float getCostOneDay() {
    return costOneDay;
  }

  public void setCostOneDay(Float costOneDay) {
    this.costOneDay = costOneDay;
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
    Car car = (Car) o;
    return Objects.equals(car_id, car.car_id)
        && Objects.equals(gosNumber, car.gosNumber)
        && Objects.equals(mark, car.mark)
        && Objects.equals(model, car.model)
        && Objects.equals(yearOut, car.yearOut)
        && Objects.equals(type, car.type)
        && Objects.equals(fullCost, car.fullCost)
        && Objects.equals(costOneDay, car.costOneDay)
        && Objects.equals(isDeleted, car.isDeleted)
        && Objects.equals(createdWhen, car.createdWhen)
        && Objects.equals(modifyWhen, car.modifyWhen);
  }

  @Override
  public int hashCode() {

    return Objects.hash(
        car_id,
        gosNumber,
        mark,
        model,
        yearOut,
        type,
        fullCost,
        costOneDay,
        isDeleted,
        createdWhen,
        modifyWhen);
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
