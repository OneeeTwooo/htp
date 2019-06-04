package com.htp.repository.impl;

import com.htp.domain.Role;
import com.htp.repository.RoleDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("roleDao")
public class RoleDaoImpl implements RoleDao {
  @Autowired
  @Qualifier("sessionFactory")
  private SessionFactory sessionFactory;

  @Override
  public List<Role> getRolesByUserId(Long userId) {
    return null;
  }

  @Override
  public List<Role> findAll() {
    try (Session session = sessionFactory.openSession()) {
      return session.createQuery("select tu from Role tu", Role.class).getResultList();
    }
  }

  @Override
  public Role findById(Long id) {
    try (Session session = sessionFactory.openSession()) {
      return session.find(Role.class, id);
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
  public Role save(Role entity) {
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.getTransaction();
      transaction.begin();
      Long newRoleID = (Long) session.save(entity);
      transaction.commit();
      return session.find(Role.class, newRoleID);
    }
  }

  @Override
  public Role update(Role entity) {
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.getTransaction();
      transaction.begin();
      session.saveOrUpdate(entity);
      transaction.commit();
      return session.find(Role.class, entity.getRoleId());
    }
  }
}
