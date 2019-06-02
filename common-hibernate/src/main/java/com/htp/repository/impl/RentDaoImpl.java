package com.htp.repository.impl;

import com.htp.domain.Car;
import com.htp.domain.Rent;
import com.htp.repository.RentDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("rentDao")
public class RentDaoImpl implements RentDao {
  @Autowired
  @Qualifier("sessionFactory")
  private SessionFactory sessionFactory;

  @Override
  public List<Rent> findAll() {
    try (Session session = sessionFactory.openSession()) {
      return session.createQuery("select tu from Rent tu", Rent.class).getResultList();
    }
  }

  @Override
  public List<Car> findCar() {
    try (Session session = sessionFactory.openSession()) {
      return session.createQuery("select tu from Car tu", Car.class).getResultList();
    }
  }

  @Override
  public Rent findById(Long id) {
    return null;
  }

  @Override
  public void delete(Long id) {}

  @Override
  public Rent save(Rent entity) {
    return null;
  }

  @Override
  public Rent update(Rent entity) {
    return null;
  }
}
