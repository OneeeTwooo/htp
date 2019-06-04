package com.htp.controller.springdata;

import com.htp.controller.requests.CarCreateRequest;
import com.htp.domain.Car;
import com.htp.repository.springdata.CarDataImpl;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/springdata/cars")
public class CarSpringData {

  @Autowired
  @Qualifier("carDataImpl")
  private CarDataImpl carData;

  @GetMapping("/all")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Page<Car>> getCars(@ApiIgnore Pageable pageable) {
    return new ResponseEntity<>(carData.findAll(pageable), HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<Optional<Car>> getCarById(@ApiParam("Car Path Id") @PathVariable Long id) {
    Optional<Car> car = carData.findById(id);
    return new ResponseEntity<>(car, HttpStatus.OK);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Car> createCar(@RequestBody CarCreateRequest request) {
    Car car = new Car();
    Date date = new Date();

    car.setGosNumber(request.getGosNumber());
    car.setMark(request.getMark());
    car.setModel(request.getModel());
    car.setType(request.getType());
    car.setFullCost(request.getFullCost());
    car.setCostOneDay(request.getCostOneDay());
    car.setIsDeleted(request.getIsDeleted());
    car.setCreatedWhen(new Timestamp(date.getTime()));
    car.setModifyWhen(new Timestamp(date.getTime()));

    Car savedCar = carData.saveAndFlush(car);

    return new ResponseEntity<>(savedCar, HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Car> updateCar(
      @PathVariable("id") Long carId, @RequestBody CarCreateRequest request) {
    Car car = carData.findById(carId).get();
    Date date = new Date();

    car.setGosNumber(request.getGosNumber());
    car.setMark(request.getMark());
    car.setModel(request.getModel());
    car.setType(request.getType());
    car.setFullCost(request.getFullCost());
    car.setCostOneDay(request.getCostOneDay());
    car.setIsDeleted(request.getIsDeleted());
    car.setCreatedWhen(car.getCreatedWhen());
    car.setModifyWhen(new Timestamp(date.getTime()));

    carData.saveAndFlush(car);

    return new ResponseEntity<>(car, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Long> deleteCar(@PathVariable("id") Long carId) {
    carData.deleteById(carId);
    return new ResponseEntity<>(carId, HttpStatus.OK);
  }
}
