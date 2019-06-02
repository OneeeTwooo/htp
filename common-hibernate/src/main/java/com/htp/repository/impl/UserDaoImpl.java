package com.htp.repository.impl;

import com.htp.domain.User;
import com.htp.repository.UserDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Qualifier("userDao")
public class UserDaoImpl implements UserDao {
  @Autowired
  @Qualifier("sessionFactory")
  private SessionFactory sessionFactory;

  //    @Autowired
  //    private EntityManagerFactory entityManagerFactory;

  @Override
  public List<User> findAll() {
    try (Session session = sessionFactory.openSession()) {
      return session.createQuery("select tu from User tu", User.class).getResultList();
    }
  }

  @Override
  public User findById(Long id) {
    try (Session session = sessionFactory.openSession()) {
      return session.find(User.class, id);
      }
  }

  @Override
  public void delete(Long id) {
    try (Session session = sessionFactory.openSession()) {
      session.remove(findById(id));
    }
  }

  @Override
  public User save(User entity) {
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.getTransaction();
      transaction.begin();
      Long newUserID = (Long) session.save(entity);
      transaction.commit();
      return session.find(User.class, newUserID);
    }
  }

  @Override
  public User update(User entity) {
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.getTransaction();
      transaction.begin();
      session.saveOrUpdate(entity);
      transaction.commit();
      return session.find(User.class, entity.getUserId());
    }
  }

  @Override
  public User findByLogin(String login) {
    try (Session session = sessionFactory.openSession()) {

      //SQLQuery

//            NativeQuery<TestUser> nativeQuery = session.createNativeQuery("select * from test_user", TestUser.class);
//            nativeQuery.getSingleResult();

//            Query query = session.createQuery("" +
//                    "select tu from TestUser tu where tu.userName = :login", TestUser.class);
//            query.setParameter("login", login);
//            return (TestUser)query.getSingleResult();

      TypedQuery<User> query = session.createQuery("" +
              "select tu from User tu where tu.userName = :login", User.class);
      query.setParameter("login", login);
      return query.getSingleResult();
    }
  }
}
