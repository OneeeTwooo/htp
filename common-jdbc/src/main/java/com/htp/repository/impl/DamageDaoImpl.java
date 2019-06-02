package com.htp.repository.impl;

import com.htp.domain.Damage;
import com.htp.repository.DamageDao;
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
public class DamageDaoImpl implements DamageDao {

  private static final String ID_DAMAGE = "id_damage";
  private static final String NAME = "name";
  private static final String COST = "cost";
  private static final String IS_DELETED = "is_deleted";
  private static final String CREATE_WHEN = "create_when";
  private static final String MODIFY_WHEN = "modify_when";

  @Autowired private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  /*Read from Result Set by column name*/
  private Damage getDamageRowMapper(ResultSet resultSet, int i) throws SQLException {
    Damage damage = new Damage();
    damage.setDamage_id(resultSet.getLong(ID_DAMAGE));
    damage.setName(resultSet.getString(NAME));
    damage.setCost(resultSet.getFloat(COST));
    damage.setIsDeleted(resultSet.getString(IS_DELETED));
    damage.setCreatedWhen(resultSet.getTimestamp(CREATE_WHEN));
    damage.setModifyWhen(resultSet.getTimestamp(MODIFY_WHEN));

    return damage;
  }

  @Override
  public List<Damage> findAll() {
    final String findAllQuery = "select * from damage_cost";

    return namedParameterJdbcTemplate.query(findAllQuery, this::getDamageRowMapper);
  }

  @Override
  public Damage findById(Long id) {
    final String findById = "select * from damage_cost where id_damage = :idDamage";

    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("idDamage", id);

    return namedParameterJdbcTemplate.queryForObject(findById, params, this::getDamageRowMapper);
  }

  @Override
  public void delete(Long id) {
    final String delete = "delete from damage_cost where id_damage = :idDamage";

    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("idDamage", id);

    namedParameterJdbcTemplate.update(delete, params);
  }

  @Override
  public Damage save(Damage entity) {
    final String createQuery =
        "INSERT INTO damage_cost (name, cost, is_deleted, create_when, modify_when) "
            + "VALUES (:damageName, :cost, :isDeleted, :createdWhen, :modifyWhen);";

    KeyHolder keyHolder = new GeneratedKeyHolder();
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("damageName", entity.getName());
    params.addValue("cost", entity.getCost());
    params.addValue("isDeleted", entity.getIsDeleted());
    params.addValue("createdWhen", entity.getCreatedWhen());
    params.addValue("modifyWhen", entity.getModifyWhen());

    namedParameterJdbcTemplate.update(createQuery, params, keyHolder);

    long createdDamageId = Objects.requireNonNull(keyHolder.getKey()).longValue();

    return findById(createdDamageId);
  }

  @Override
  public Damage update(Damage entity) {

    final String updateQuery =
        "UPDATE damage_cost set name = : damageName, cost = :cost, create_when = :createdWhen, modify_when = :modifyWhen, "
            + "is_deleted = :isDeleted where id_damage = :idDamage";

    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("idDamage", entity.getDamage_id());
    params.addValue("damageName", entity.getName());
    params.addValue("cost", entity.getCost());
    params.addValue("isDeleted", entity.getIsDeleted());
    params.addValue("createdWhen", entity.getCreatedWhen());
    params.addValue("modifyWhen", entity.getModifyWhen());

    namedParameterJdbcTemplate.update(updateQuery, params);
    return findById(entity.getDamage_id());
  }
}
