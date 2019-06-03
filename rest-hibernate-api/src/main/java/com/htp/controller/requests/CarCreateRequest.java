package com.htp.controller.requests;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class CarCreateRequest {
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

  public CarCreateRequest() {}

  public CarCreateRequest(
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
    CarCreateRequest that = (CarCreateRequest) o;
    return Objects.equals(gosNumber, that.gosNumber)
        && Objects.equals(mark, that.mark)
        && Objects.equals(model, that.model)
        && Objects.equals(yearOut, that.yearOut)
        && Objects.equals(type, that.type)
        && Objects.equals(fullCost, that.fullCost)
        && Objects.equals(costOneDay, that.costOneDay)
        && Objects.equals(isDeleted, that.isDeleted)
        && Objects.equals(createdWhen, that.createdWhen)
        && Objects.equals(modifyWhen, that.modifyWhen);
  }

  @Override
  public int hashCode() {

    return Objects.hash(
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
    return ToStringBuilder.reflectionToString(this);
  }
}
