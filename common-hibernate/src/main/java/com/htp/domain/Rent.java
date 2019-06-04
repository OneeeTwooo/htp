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

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_user")
  private User user;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "id_rent")
  // @JoinTable( name = "damage_rent",
  //   joinColumns = @JoinColumn(name = "id_rent"),
  //    inverseJoinColumns = @JoinColumn(name = "id_damage"))
  private Set<Damage> damages = Collections.emptySet();

  public Rent() {}

  public Rent(
      Timestamp rentalStartDate,
      Timestamp rentalFinishDate,
      Timestamp createdWhen,
      Timestamp modifyWhen,
      Car car,
      User user,
      Set<Damage> damages) {
    this.rentalStartDate = rentalStartDate;
    this.rentalFinishDate = rentalFinishDate;
    this.createdWhen = createdWhen;
    this.modifyWhen = modifyWhen;
    this.car = car;
    this.user = user;
    this.damages = damages;
  }

  public Long getRentId() {
    return rentId;
  }

  public void setRentId(Long rentId) {
    this.rentId = rentId;
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

  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Set<Damage> getDamages() {
    return damages;
  }

  public void setDamages(Set<Damage> damages) {
    this.damages = damages;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Rent rent = (Rent) o;
    return Objects.equals(rentId, rent.rentId)
        && Objects.equals(rentalStartDate, rent.rentalStartDate)
        && Objects.equals(rentalFinishDate, rent.rentalFinishDate)
        && Objects.equals(createdWhen, rent.createdWhen)
        && Objects.equals(modifyWhen, rent.modifyWhen)
        && Objects.equals(car, rent.car)
        && Objects.equals(user, rent.user)
        && Objects.equals(damages, rent.damages);
  }

  @Override
  public int hashCode() {

    return Objects.hash(
        rentId, rentalStartDate, rentalFinishDate, createdWhen, modifyWhen, car, user, damages);
  }

  @Override
  public String toString() {
    return "Rent{"
        + "rentId="
        + rentId
        + ", rentalStartDate="
        + rentalStartDate
        + ", rentalFinishDate="
        + rentalFinishDate
        + ", createdWhen="
        + createdWhen
        + ", modifyWhen="
        + modifyWhen
        + ", car="
        + car
        + ", user="
        + user
        + ", damages="
        + damages
        + '}';
  }
}
