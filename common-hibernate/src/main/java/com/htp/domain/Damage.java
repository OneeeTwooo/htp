package com.htp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "damage_cost")
public class Damage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_damage")
  private Long damageId;

  @Column(name = "name")
  private String name;

  @Column(name = "cost")
  private Float cost;

  @Column(name = "is_deleted")
  private String isDeleted;

  @Column(name = "create_when")
  private Timestamp createWhen;

  @Column(name = "modify_when")
  private Timestamp modifyWhen;

  @JsonBackReference
  @ManyToMany(mappedBy = "damages", fetch = FetchType.EAGER)
  private Set<Rent> rents = Collections.emptySet();

  public Damage(
      String name,
      Float cost,
      String isDeleted,
      Timestamp createWhen,
      Timestamp modifyWhen,
      Set<Rent> rents) {
    this.name = name;
    this.cost = cost;
    this.isDeleted = isDeleted;
    this.createWhen = createWhen;
    this.modifyWhen = modifyWhen;
    this.rents = rents;
  }

  public Damage() {}

  public Long getDamageId() {
    return damageId;
  }

  public void setDamageId(Long damageId) {
    this.damageId = damageId;
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

  public Timestamp getCreateWhen() {
    return createWhen;
  }

  public void setCreateWhen(Timestamp createWhen) {
    this.createWhen = createWhen;
  }

  public Timestamp getModifyWhen() {
    return modifyWhen;
  }

  public void setModifyWhen(Timestamp modifyWhen) {
    this.modifyWhen = modifyWhen;
  }

  public Set<Rent> getRents() {
    return rents;
  }

  public void setRents(Set<Rent> rents) {
    this.rents = rents;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Damage that = (Damage) o;
    return Objects.equals(damageId, that.damageId)
        && Objects.equals(name, that.name)
        && Objects.equals(cost, that.cost)
        && Objects.equals(isDeleted, that.isDeleted)
        && Objects.equals(createWhen, that.createWhen)
        && Objects.equals(modifyWhen, that.modifyWhen);
  }

  @Override
  public int hashCode() {

    return Objects.hash(damageId, name, cost, isDeleted, createWhen, modifyWhen);
  }
}
