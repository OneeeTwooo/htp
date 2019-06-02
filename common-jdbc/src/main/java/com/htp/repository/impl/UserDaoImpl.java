package com.htp.repository.impl;

import com.htp.domain.User;
import com.htp.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

  private static final String USER_ID = "id_user";
  private static final String FIRST_NAME = "first_name";
  private static final String LAST_NAME = "last_name";
  private static final String PASSPORT_ID = "id_passport";
  private static final String PHONE_NUMBER = "phone_number";
  private static final String USER_NAME = "user_name";
  private static final String USER_PASSWORD = "user_password";
  private static final String CREATED_WHEN = "created_when";
  private static final String MODIFY_WHEN = "modify_when";
  private static final String IS_DELETED = "is_deleted";

  @Autowired private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  /*Read from Result Set by column name*/
  private User getUserRowMapper(ResultSet resultSet, int i) throws SQLException {
    User user = new User();
    user.setUserId(resultSet.getLong(USER_ID));
    user.setFirstName(resultSet.getString(FIRST_NAME));
    user.setLastName(resultSet.getString(LAST_NAME));
    user.setPassportId(resultSet.getString(PASSPORT_ID));
    user.setPhoneNumber(resultSet.getString(PHONE_NUMBER));
    user.setUserName(resultSet.getString(USER_NAME));
    user.setUserPassword(resultSet.getString(USER_PASSWORD));
    user.setCreatedWhen(resultSet.getTimestamp(CREATED_WHEN));
    user.setModifyWhen(resultSet.getTimestamp(MODIFY_WHEN));
    user.setIsDeleted(resultSet.getString(IS_DELETED));
    return user;
  }

  @Override
  public User findByLogin(String login) {
    final String findByLogin = "select * from users where user_name = :user_name";

    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("user_name", login);

    return namedParameterJdbcTemplate.queryForObject(findByLogin, params, this::getUserRowMapper);
  }

  @Override
  public List<User> findAll() {
    final String findAllQuery = "select * from users";

    return namedParameterJdbcTemplate.query(findAllQuery, this::getUserRowMapper);
  }

  @Override
  public User findById(Long id) {
    final String findById = "select * from users where id_user = :idUser";

    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("idUser", id);

    return namedParameterJdbcTemplate.queryForObject(findById, params, this::getUserRowMapper);
  }

  @Override
  public void delete(Long id) {
    final String delete = "delete from users where id_user = :idUser";

    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("idUser", id);

    namedParameterJdbcTemplate.update(delete, params);
  }

  @Override
  public User save(User entity) {
    final String createQuery =
        "INSERT INTO users (first_name, last_name, id_passport, phone_number, user_name, "
            + "user_password, created_when, modify_when, is_deleted) "
            + "VALUES (:firsName, :lastName, :idPassport, :phoneNumber, :userName, :userPassword, :createdWhen, "
            + ":modifyWhen, :isDeleted);";

    KeyHolder keyHolder = new GeneratedKeyHolder();
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("firsName", entity.getFirstName());
    params.addValue("lastName", entity.getLastName());
    params.addValue("idPassport", entity.getPassportId());
    params.addValue("phoneNumber", entity.getPhoneNumber());
    params.addValue("userName", entity.getUserName());
    params.addValue("userPassword", entity.getUserPassword());
    params.addValue("createdWhen", entity.getCreatedWhen());
    params.addValue("modifyWhen", entity.getModifyWhen());
    params.addValue("isDeleted", entity.getIsDeleted());

    namedParameterJdbcTemplate.update(createQuery, params, keyHolder);

    long createdUserId = Objects.requireNonNull(keyHolder.getKey()).longValue();

    return findById(createdUserId);
  }

  @Override
  public User update(User entity) {

    final String updateQuery =
        "UPDATE users set first_name = :firsName, last_name = :lastName, id_passport = :idPassport,"
            + "phone_number = :phoneNumber, user_name = :userName, created_when = :createdWhen, modify_when = :modifyWhen, "
            + "is_deleted = :isDeleted where id_user = :idUser";

    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("idUser", entity.getUserId());
    params.addValue("firsName", entity.getFirstName());
    params.addValue("lastName", entity.getLastName());
    params.addValue("idPassport", entity.getPassportId());
    params.addValue("phoneNumber", entity.getPhoneNumber());
    params.addValue("userName", entity.getUserName());
    params.addValue("userPassword", entity.getUserPassword());
    params.addValue("createdWhen", entity.getCreatedWhen());
    params.addValue("modifyWhen", entity.getModifyWhen());
    params.addValue("isDeleted", entity.getIsDeleted());

    namedParameterJdbcTemplate.update(updateQuery, params);
    return findById(entity.getUserId());
  }
}
