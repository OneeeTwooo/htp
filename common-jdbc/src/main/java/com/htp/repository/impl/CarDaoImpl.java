package com.htp.repository.impl;

import com.htp.domain.Car;
import com.htp.repository.CarDao;
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
public class CarDaoImpl implements CarDao {

  private static final String ID_CAR = "id_car";
  private static final String GOS_NUMBER = "gos_number";
  private static final String MARK = "mark";
  private static final String MODEL = "model";
  private static final String YEAR_OUT = "year_out";
  private static final String TYPE = "type";
  private static final String FULL_COST = "full_cost";
  private static final String COST_ONE_DAY = "cost_1_day";
  private static final String IS_DELETED = "is_deleted";
  private static final String CREATED_WHEN = "create_when";
  private static final String MODIFY_WHEN = "modify_when";

  @Autowired private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  /*Read from Result Set by column name*/
  private Car getCarRowMapper(ResultSet resultSet, int i) throws SQLException {
    Car car = new Car();
    car.setCar_id(resultSet.getLong(ID_CAR));
    car.setGosNumber(resultSet.getString(GOS_NUMBER));
    car.setMark(resultSet.getString(MARK));
    car.setModel(resultSet.getString(MODEL));
    car.setYearOut(resultSet.getDate(YEAR_OUT));
    car.setType(resultSet.getString(TYPE));
    car.setFullCost(resultSet.getFloat(FULL_COST));
    car.setCostOneDay(resultSet.getFloat(COST_ONE_DAY));
    car.setIsDeleted(resultSet.getString(IS_DELETED));
    car.setCreatedWhen(resultSet.getTimestamp(CREATED_WHEN));
    car.setModifyWhen(resultSet.getTimestamp(MODIFY_WHEN));

    return car;
  }

  @Override
  public List<Car> findAll() {
    final String findAllQuery = "select * from cars";

    return namedParameterJdbcTemplate.query(findAllQuery, this::getCarRowMapper);
  }

  @Override
  public Car findById(Long id) {
    final String findById = "select * from cars where id_car = :idCar";

    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("idCar", id);

    return namedParameterJdbcTemplate.queryForObject(findById, params, this::getCarRowMapper);
  }

  @Override
  public void delete(Long id) {
    final String delete = "delete from cars where id_car = :idCar";

    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("idCar", id);

    namedParameterJdbcTemplate.update(delete, params);
  }

  @Override
  public Car save(Car entity) {
    final String createQuery =
        "INSERT INTO cars (gos_number, mark, model, year_out, type, full_cost, cost_1_day, is_deleted, created_when, modify_when) "
            + "VALUES (:gosNumber, :mark, :model, :yearOut, :typeCar, :fullCost, :costOneDay, :isDeleted, :createdWhen, :modifyWhen);";

    KeyHolder keyHolder = new GeneratedKeyHolder();
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("gosNumber", entity.getGosNumber());
    params.addValue("mark", entity.getMark());
    params.addValue("model", entity.getModel());
    params.addValue("yearOut", entity.getYearOut());
    params.addValue("typeCar", entity.getType());
    params.addValue("fullCost", entity.getFullCost());
    params.addValue("costOneDay", entity.getCostOneDay());
    params.addValue("isDeleted", entity.getIsDeleted());
    params.addValue("createdWhen", entity.getCreatedWhen());
    params.addValue("modifyWhen", entity.getModifyWhen());

    namedParameterJdbcTemplate.update(createQuery, params, keyHolder);

    long createdDamageId = Objects.requireNonNull(keyHolder.getKey()).longValue();

    return findById(createdDamageId);
  }

  @Override
  public Car update(Car entity) {
    final String updateQuery =
        "UPDATE cars set gos_number = : gosNumber, mark = :mark, model = :model, year_out = :yearOut,type = :typeCar, "
            + "full_cost = :fullCost, cost_1_day = :costOneDay, create_when = :createdWhen, modify_when = :modifyWhen, "
            + "is_deleted = :isDeleted where id_damage = :idDamage";

    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("idCar", entity.getCar_id());
    params.addValue("gosNumber", entity.getGosNumber());
    params.addValue("mark", entity.getMark());
    params.addValue("model", entity.getModel());
    params.addValue("yearOut", entity.getYearOut());
    params.addValue("typeCar", entity.getType());
    params.addValue("fullCost", entity.getFullCost());
    params.addValue("costOneDay", entity.getCostOneDay());
    params.addValue("isDeleted", entity.getIsDeleted());
    params.addValue("createdWhen", entity.getCreatedWhen());
    params.addValue("modifyWhen", entity.getModifyWhen());

    namedParameterJdbcTemplate.update(updateQuery, params);
    return findById(entity.getCar_id());
  }
}
