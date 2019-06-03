package com.htp.controller.requests;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.sql.Timestamp;
import java.util.Objects;

public class DamageCreateRequest {
  private String name;
  private Float cost;
  private String isDeleted;
  private Timestamp createdWhen;
  private Timestamp modifyWhen;

  public DamageCreateRequest() {}

  public DamageCreateRequest(
      String name, Float cost, String isDeleted, Timestamp createdWhen, Timestamp modifyWhen) {
    this.name = name;
    this.cost = cost;
    this.isDeleted = isDeleted;
    this.createdWhen = createdWhen;
    this.modifyWhen = modifyWhen;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Float getCost() {
    return cost;
  }

  public void setCost(Float cost) {
    this.cost = cost;
  }

  public String getIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(String isDeleted) {
    this.isDeleted = isDeleted;
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
    DamageCreateRequest that = (DamageCreateRequest) o;
    return Objects.equals(name, that.name)
        && Objects.equals(cost, that.cost)
        && Objects.equals(isDeleted, that.isDeleted)
        && Objects.equals(createdWhen, that.createdWhen)
        && Objects.equals(modifyWhen, that.modifyWhen);
  }

  @Override
  public int hashCode() {

    return Objects.hash(name, cost, isDeleted, createdWhen, modifyWhen);
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
