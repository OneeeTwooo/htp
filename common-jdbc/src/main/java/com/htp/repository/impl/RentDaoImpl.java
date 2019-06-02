package com.htp.repository.impl;

import com.htp.domain.Rent;
import com.htp.repository.RentDao;
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
public class RentDaoImpl implements RentDao {

  private static final String ID_RENT = "id_rent";
  private static final String ID_USER = "id_user";
  private static final String ID_CAR = "id_car";
  private static final String RENTAL_START_DATE = "rental_start_date";
  private static final String RENTAL_FINISH_DATE = "rental_finish_date";
  private static final String CREATED_WHEN = "created_when";
  private static final String MODIFY_WHEN = "modify_when";

  @Autowired private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  /*Read from Result Set by column name*/
  private Rent getRentRowMapper(ResultSet resultSet, int i) throws SQLException {
    Rent rent = new Rent();
    rent.setRentId(resultSet.getLong(ID_RENT));
    rent.setCarId(resultSet.getLong(ID_CAR));
    rent.setUserId(resultSet.getLong(ID_USER));
    rent.setRentalStartDate(resultSet.getTimestamp(RENTAL_START_DATE));
    rent.setRentalFinishDate(resultSet.getTimestamp(RENTAL_FINISH_DATE));
    rent.setCreatedWhen(resultSet.getTimestamp(CREATED_WHEN));
    rent.setModifyWhen(resultSet.getTimestamp(MODIFY_WHEN));

    return rent;
  }

  @Override
  public List<Rent> findAll() {
    final String findAllQuery = "select * from rents";

    return namedParameterJdbcTemplate.query(findAllQuery, this::getRentRowMapper);
  }

  @Override
  public Rent findById(Long id) {
    final String findById = "select * from rents where id_rent = :idRent";

    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("idRent", id);

    return namedParameterJdbcTemplate.queryForObject(findById, params, this::getRentRowMapper);
  }

  @Override
  public void delete(Long id) {
    final String delete = "delete from rents where id_rent = :idRent";

    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("idRent", id);

    namedParameterJdbcTemplate.update(delete, params);
  }

  @Override
  public Rent save(Rent entity) {
    final String createQuery =
        "INSERT INTO rents (id_user, id_car, rental_start_date, rental_finish_date, created_when, modify_when) "
            + "VALUES (:idUser, :idCar, :rentalStartDate, :rentalFinishDate, :createdWhen, :modifyWhen);";

    KeyHolder keyHolder = new GeneratedKeyHolder();
    MapSqlParameterSource params = new MapSqlParameterSource();

    params.addValue("idUser", entity.getUserId());
    params.addValue("idCar", entity.getCarId());
    params.addValue("rentalStartDate", entity.getRentalStartDate());
    params.addValue("rentalFinishDate", entity.getRentalFinishDate());
    params.addValue("createdWhen", entity.getCreatedWhen());
    params.addValue("modifyWhen", entity.getModifyWhen());

    namedParameterJdbcTemplate.update(createQuery, params, keyHolder);

    long createdRentId = Objects.requireNonNull(keyHolder.getKey()).longValue();

    return findById(createdRentId);
  }

  @Override
  public Rent update(Rent entity) {
    final String updateQuery =
        "UPDATE rents set id_rent = : idRent, id_user = :idUser, id_car = :idCar, rental_start_date = :rentalStartDate, "
            + "rental_finish_date = :rentalFinishDate create_when = :createdWhen, modify_when = :modifyWhen, "
            + "where id_rent = :idRent";

    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("idRent", entity.getRentId());
    params.addValue("idUser", entity.getUserId());
    params.addValue("idCar", entity.getCarId());
    params.addValue("rentalStartDate", entity.getRentalStartDate());
    params.addValue("rentalFinishDate", entity.getRentalFinishDate());
    params.addValue("createdWhen", entity.getCreatedWhen());
    params.addValue("modifyWhen", entity.getModifyWhen());

    namedParameterJdbcTemplate.update(updateQuery, params);
    return findById(entity.getRentId());
  }
}
