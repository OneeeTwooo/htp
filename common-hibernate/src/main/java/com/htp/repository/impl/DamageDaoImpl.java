package com.htp.repository.impl;

import com.htp.domain.Damage;
import com.htp.repository.DamageDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("damageDao")
public class DamageDaoImpl implements DamageDao {
  @Autowired
  @Qualifier("sessionFactory")
  private SessionFactory sessionFactory;

  @Override
  public List<Damage> findAll() {
    try (Session session = sessionFactory.openSession()) {
      return session.createQuery("select tu from Damage tu", Damage.class).getResultList();
    }
  }

  @Override
  public Damage findById(Long id) {
    try (Session session = sessionFactory.openSession()) {
      return session.find(Damage.class, id);
    }
  }

  @Override
  public void delete(Long id) {
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.getTransaction();
      transaction.begin();
      session.delete(findById(id));
      transaction.commit();
    }
  }

  @Override
  public Damage save(Damage entity) {
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.getTransaction();
      transaction.begin();
      Long newDamageID = (Long) session.save(entity);
      transaction.commit();
      return session.find(Damage.class, newDamageID);
    }
  }

  @Override
  public Damage update(Damage entity) {
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.getTransaction();
      transaction.begin();
      session.saveOrUpdate(entity);
      transaction.commit();
      return session.find(Damage.class, entity.getDamageId());
    }
  }
}
