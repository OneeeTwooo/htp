package com.htp.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "rents")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "rentId")
public class Rent {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_rent")
  private Long rentId;

  @Column(name = "id_user")
  private Long userId;

  @Column(name = "rental_start_date")
  private Timestamp rentalStartDate;

  @Column(name = "rental_finish_date")
  private Timestamp rentalFinishDate;

  @Column(name = "created_when")
  private Timestamp createdWhen;

  @Column(name = "modify_when")
  private Timestamp modifyWhen;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_rent_car")
  private Car car;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(
      name = "damage_rent",
      joinColumns = @JoinColumn(name = "id_rent"),
      inverseJoinColumns = @JoinColumn(name = "id_damage"))
  private Set<Damage> damages = Collections.emptySet();

  public Rent() {}

  public Rent(
      Long userId,
      Timestamp rentalStartDate,
      Timestamp rentalFinishDate,
      Timestamp createdWhen,
      Timestamp modifyWhen,
      Car car,
      Set<Damage> damages) {
    this.userId = userId;
    this.rentalStartDate = rentalStartDate;
    this.rentalFinishDate = rentalFinishDate;
    this.createdWhen = createdWhen;
    this.modifyWhen = modifyWhen;
    this.car = car;
    this.damages = damages;
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

  public Set<Damage> getHibirnateDamages() {
    return damages;
  }

  public void setHibirnateDamages(Set<Damage> damages) {
    this.damages = damages;
  }

  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Rent rent = (Rent) o;
    return Objects.equals(rentId, rent.rentId)
        && Objects.equals(userId, rent.userId)
        && Objects.equals(rentalStartDate, rent.rentalStartDate)
        && Objects.equals(rentalFinishDate, rent.rentalFinishDate)
        && Objects.equals(createdWhen, rent.createdWhen)
        && Objects.equals(modifyWhen, rent.modifyWhen);
  }

  @Override
  public int hashCode() {

    return Objects.hash(rentId, userId, rentalStartDate, rentalFinishDate, createdWhen, modifyWhen);
  }
}
