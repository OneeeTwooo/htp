package com.htp.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Year;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "cars")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "carId")
public class Car {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_car")
  private Long carId;

  @Column(name = "gos_number")
  private String gosNumber;

  @Column(name = "mark")
  private String mark;

  @Column(name = "model")
  private String model;

  @Column(name = "type")
  private String type;

  @Column(name = "full_cost")
  private Float fullCost;

  @Column(name = "cost_1_day")
  private Float costOneDay;

  @Column(name = "is_deleted")
  private String isDeleted;

  @Column(name = "created_when")
  private Timestamp createdWhen;

  @Column(name = "modify_when")
  private Timestamp modifyWhen;

  public Car() {}

  public Long getCarId() {
    return carId;
  }

  public void setCarId(Long car_id) {
    this.carId = car_id;
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

  public Car(
      String gosNumber,
      String mark,
      String model,
      String type,
      Float fullCost,
      Float costOneDay,
      String isDeleted,
      Timestamp createdWhen,
      Timestamp modifyWhen) {
    this.gosNumber = gosNumber;
    this.mark = mark;
    this.model = model;
    this.type = type;
    this.fullCost = fullCost;
    this.costOneDay = costOneDay;
    this.isDeleted = isDeleted;
    this.createdWhen = createdWhen;
    this.modifyWhen = modifyWhen;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Car car = (Car) o;
    return Objects.equals(carId, car.carId)
        && Objects.equals(gosNumber, car.gosNumber)
        && Objects.equals(mark, car.mark)
        && Objects.equals(model, car.model)
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
        carId,
        gosNumber,
        mark,
        model,
        type,
        fullCost,
        costOneDay,
        isDeleted,
        createdWhen,
        modifyWhen);
  }
}
