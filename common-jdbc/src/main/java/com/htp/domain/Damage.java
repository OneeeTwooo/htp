package com.htp.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;
import java.util.Objects;

public class Damage {
  private Long damage_id;
  private String name;
  private Float cost;
  private String isDeleted;
  private Timestamp createdWhen;
  private Timestamp modifyWhen;

  public Damage() {}

  public Damage(
      Long damage_id,
      String name,
      Float cost,
      String isDeleted,
      Timestamp createdWhen,
      Timestamp modifyWhen) {
    this.damage_id = damage_id;
    this.name = name;
    this.cost = cost;
    this.isDeleted = isDeleted;
    this.createdWhen = createdWhen;
    this.modifyWhen = modifyWhen;
  }

  public Long getDamage_id() {
    return damage_id;
  }

  public void setDamage_id(Long damage_id) {
    this.damage_id = damage_id;
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
    Damage damage = (Damage) o;
    return Objects.equals(damage_id, damage.damage_id)
        && Objects.equals(name, damage.name)
        && Objects.equals(cost, damage.cost)
        && Objects.equals(isDeleted, damage.isDeleted)
        && Objects.equals(createdWhen, damage.createdWhen)
        && Objects.equals(modifyWhen, damage.modifyWhen);
  }

  @Override
  public int hashCode() {

    return Objects.hash(damage_id, name, cost, isDeleted, createdWhen, modifyWhen);
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
