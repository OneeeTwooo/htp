package com.htp.controller;

import com.htp.domain.Car;
import com.htp.domain.Rent;
import com.htp.repository.RentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/hibernate/rents")
public class RentController {

  @Autowired private RentDao rentDao;

  @GetMapping("/all")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<Rent>> getRentsHibernate() {
    return new ResponseEntity<>(rentDao.findAll(), HttpStatus.OK);
  }

  @GetMapping("/test")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<Car>> findCar() {
    return new ResponseEntity<>(rentDao.findCar(), HttpStatus.OK);
  }
}
