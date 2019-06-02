package com.htp.repository.impl;

import com.htp.domain.Car;
import com.htp.repository.CarDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Qualifier("carDao")
public class CarDaoImpl implements CarDao {
  @Override
  public List<Car> findAll() {
    return null;
  }

  @Override
  public Car findById(Long id) {
    return null;
  }

  @Override
  public void delete(Long id) {}

  @Override
  public Car save(Car entity) {
    return null;
  }

  @Override
  public Car update(Car entity) {
    return null;
  }
}
