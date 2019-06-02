package com.htp.repository;

import com.htp.domain.Car;
import com.htp.domain.Rent;

import java.util.List;

public interface RentDao extends GenericDao<Rent, Long> {
  List<Car> findCar();
}
