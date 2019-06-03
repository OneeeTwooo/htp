package com.htp.repository.impl;

import com.htp.domain.Car;
import com.htp.repository.CarDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("carDao")
public class CarDaoImpl implements CarDao {
  @Autowired
  @Qualifier("sessionFactory")
  private SessionFactory sessionFactory;

  @Override
  public List<Car> findAll() {
    try (Session session = sessionFactory.openSession()) {
      return session.createQuery("select tu from Car tu", Car.class).getResultList();
    }
  }

  @Override
  public Car findById(Long id) {
    try (Session session = sessionFactory.openSession()) {
      return session.find(Car.class, id);
    }
  }

  @Override
  public void delete(Long id) {
    try (Session session = sessionFactory.openSession()) {
      session.remove(findById(id));
    }
  }

  @Override
  public Car save(Car entity) {
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.getTransaction();
      transaction.begin();
      Long newCarID = (Long) session.save(entity);
      transaction.commit();
      return session.find(Car.class, newCarID);
    }
  }

  @Override
  public Car update(Car entity) {
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.getTransaction();
      transaction.begin();
      session.saveOrUpdate(entity);
      transaction.commit();
      return session.find(Car.class, entity.getCarId());
    }
  }
}
