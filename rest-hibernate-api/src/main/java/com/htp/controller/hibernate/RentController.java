package com.htp.controller.hibernate;

import com.htp.controller.requests.RentCreateRequest;
import com.htp.domain.Rent;
import com.htp.repository.CarDao;
import com.htp.repository.RentDao;
import com.htp.repository.UserDao;
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
@RequestMapping(value = "/rest/hibernate/rents")
public class RentController {

  @Autowired private RentDao rentDao;

  @Autowired private UserDao userDao;

  @Autowired private CarDao carDao;

  @GetMapping("/all")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<Rent>> getRents() {
    return new ResponseEntity<>(rentDao.findAll(), HttpStatus.OK);
  }

  @ApiOperation(value = "Get rent from server by id")
  @ApiResponses({
    @ApiResponse(code = 200, message = "Successful getting rent"),
    @ApiResponse(code = 400, message = "Invalid Rent ID supplied"),
    @ApiResponse(code = 401, message = "Lol kek"),
    @ApiResponse(code = 404, message = "Rent was not found"),
    @ApiResponse(code = 500, message = "Server error, something wrong")
  })
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<Rent> getRentById(@ApiParam("Rent Path Id") @PathVariable Long id) {
    Rent rent = rentDao.findById(id);
    return new ResponseEntity<>(rent, HttpStatus.OK);
  }

  @PostMapping
  @Transactional
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Rent> createRent(@RequestBody RentCreateRequest request) {
    Rent rent = new Rent();
    Date date = new Date();

    rent.setRentalStartDate(new Timestamp(date.getTime()));
    rent.setRentalFinishDate(new Timestamp(date.getTime()));
    rent.setCreatedWhen(new Timestamp(date.getTime()));
    rent.setModifyWhen(new Timestamp(date.getTime()));
    rent.setCar(carDao.findById(request.getCarId()));
    rent.setUser(userDao.findById(request.getUserId()));

    Rent savedRent = rentDao.save(rent);

    return new ResponseEntity<>(savedRent, HttpStatus.OK);
  }

  @ApiOperation(value = "Update rent by rentID")
  @ApiResponses({
    @ApiResponse(code = 200, message = "Successful rent update"),
    @ApiResponse(code = 400, message = "Invalid Rent ID supplied"),
    @ApiResponse(code = 404, message = "Rent was not found"),
    @ApiResponse(code = 500, message = "Server error, something wrong")
  })
  @RequestMapping(value = "/{id}", method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Rent> updateRent(
      @PathVariable("id") Long rentId, @RequestBody RentCreateRequest request) {
    Rent rent = rentDao.findById(rentId);
    Date date = new Date();
    rent.setRentalStartDate(new Timestamp(date.getTime()));
    rent.setRentalFinishDate(new Timestamp(date.getTime()));
    rent.setCreatedWhen(new Timestamp(date.getTime()));
    rent.setModifyWhen(new Timestamp(date.getTime()));
    rent.setCar(carDao.findById(request.getCarId()));
    rent.setUser(userDao.findById(request.getUserId()));
    rentDao.update(rent);

    return new ResponseEntity<>(rent, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Long> deleteRent(@PathVariable("id") Long rentId) {
    rentDao.delete(rentId);
    return new ResponseEntity<>(rentId, HttpStatus.OK);
  }
}
