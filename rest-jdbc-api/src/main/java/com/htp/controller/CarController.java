package com.htp.controller;

import com.htp.controller.requests.CarCreateRequest;
import com.htp.domain.Car;
import com.htp.repository.CarDao;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/cars")
public class CarController {
  @Autowired private CarDao carDao;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<Car>> getCars() {
    return new ResponseEntity<>(carDao.findAll(), HttpStatus.OK);
  }

  @PostMapping
  @Transactional
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Car> createCar(@RequestBody CarCreateRequest request) {
    Car car = new Car();
    Date date = new Date();
    // userId is empty - will be generated by DB

    car.setGosNumber(request.getGosNumber());
    car.setMark(request.getMark());
    car.setModel(request.getModel());
    car.setYearOut(request.getYearOut());
    car.setType(request.getType());
    car.setFullCost(request.getFullCost());
    car.setCostOneDay(request.getCostOneDay());
    car.setIsDeleted(request.getIsDeleted());
    car.setCreatedWhen(new Timestamp(date.getTime()));
    car.setModifyWhen(new Timestamp(date.getTime()));

    Car savedCar = carDao.save(car);

    return new ResponseEntity<>(savedCar, HttpStatus.OK);
  }

  @ApiOperation(value = "Get car from server by id")
  @ApiResponses({
    @ApiResponse(code = 200, message = "Successful getting car"),
    @ApiResponse(code = 400, message = "Invalid Car ID supplied"),
    @ApiResponse(code = 401, message = "Lol kek"),
    @ApiResponse(code = 404, message = "Car was not found"),
    @ApiResponse(code = 500, message = "Server error, something wrong")
  })
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<Car> getCarById(@ApiParam("Car Id") @PathVariable Long id) {
    Car car = carDao.findById(id);
    return new ResponseEntity<>(car, HttpStatus.OK);
  }

  @ApiOperation(value = "Update Car by userId")
  @ApiResponses({
    @ApiResponse(code = 200, message = "Successful Car update"),
    @ApiResponse(code = 400, message = "Invalid Car ID supplied"),
    @ApiResponse(code = 404, message = "Car was not found"),
    @ApiResponse(code = 500, message = "Server error, something wrong")
  })
  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "X-Auth-Token",
        value = "token",
        required = true,
        dataType = "string",
        paramType = "header")
  })
  // TODO: FOR WHAT THIS?
  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Car> updateCar(
      @PathVariable("id") Long carId, @RequestBody CarCreateRequest request) {
    Car car = carDao.findById(carId);
    Date date = new Date();

    car.setGosNumber(request.getGosNumber());
    car.setMark(request.getMark());
    car.setModel(request.getModel());
    car.setYearOut(request.getYearOut());
    car.setType(request.getType());
    car.setFullCost(request.getFullCost());
    car.setCostOneDay(request.getCostOneDay());
    car.setIsDeleted(request.getIsDeleted());
    car.setCreatedWhen(car.getCreatedWhen());
    car.setModifyWhen(new Timestamp(date.getTime()));

    carDao.update(car);

    return new ResponseEntity<>(car, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Long> deleteCar(@PathVariable("id") Long carId) {
    carDao.delete(carId);
    return new ResponseEntity<>(carId, HttpStatus.OK);
  }
}
