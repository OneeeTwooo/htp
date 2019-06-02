package com.htp.repository.impl;

import com.htp.domain.Role;
import com.htp.repository.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {

  private static final String ROLE_ID = "id_role";
  private static final String ROLE_NAME = "name_role";
  private static final String ROLE_TYPE = "type_role";

  @Autowired private JdbcTemplate jdbcTemplate;

  @Autowired private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  /*Read from Result Set by column name*/
  private Role getRoleRowMapper(ResultSet resultSet, int i) throws SQLException {
    Role role = new Role();
    role.setIdRole(resultSet.getLong(ROLE_ID));
    role.setNameRole(resultSet.getString(ROLE_NAME));
    role.setRoleType(resultSet.getString(ROLE_TYPE));
    return role;
  }

  @Override
  public List<Role> getRolesByUserId(Long userId) {
    final String getRolesByUserId =
        "select r.* from user_role ur "
            + "JOIN roles r ON r.id_role = ur.id_role "
            + "WHERE ur.id_user = ?";

    return jdbcTemplate.query(getRolesByUserId, new Object[] {userId}, this::getRoleRowMapper);
  }

  @Override
  public List<Role> findAll() {
    final String findAllQuery = "select * from roles";

    return namedParameterJdbcTemplate.query(findAllQuery, this::getRoleRowMapper);
  }

  @Override
  public Role findById(Long id) {
    final String findById = "select * from roles where id_role = :idRole";

    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("idRole", id);

    return namedParameterJdbcTemplate.queryForObject(findById, params, this::getRoleRowMapper);
  }

  @Override
  public void delete(Long id) {
    final String delete = "delete from roles where id_role = :idRole";

    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("idRole", id);

    namedParameterJdbcTemplate.update(delete, params);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT)
  public Role save(Role entity) {
    final String createQuery =
        "INSERT INTO roles (name_role, type_role) " + "VALUES (:nameRole, :typeRole);";

    KeyHolder keyHolder = new GeneratedKeyHolder();
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("nameRole", entity.getNameRole());
    params.addValue("typeRole", entity.getRoleType());

    namedParameterJdbcTemplate.update(createQuery, params, keyHolder);

    long createdRoleId = Objects.requireNonNull(keyHolder.getKey()).longValue();

    return findById(createdRoleId);
  }

  @Override
  public Role update(Role entity) {
    final String updateQuery =
        "UPDATE roles set name_role = :nameRole, type_role = :typeRole where id_role = :idRole";

    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("idRole", entity.getIdRole());
    params.addValue("nameRole", entity.getNameRole());
    params.addValue("typeRole", entity.getRoleType());

    namedParameterJdbcTemplate.update(updateQuery, params);
    return findById(entity.getIdRole());
  }
}
