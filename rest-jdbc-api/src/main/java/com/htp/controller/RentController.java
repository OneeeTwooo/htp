package com.htp.controller;

import com.htp.controller.requests.RentCreateRequest;
import com.htp.domain.Rent;
import com.htp.repository.RentDao;
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
@RequestMapping(value = "/rest/rents")
public class RentController {
  @Autowired private RentDao rentDao;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<Rent>> getRents() {
    return new ResponseEntity<>(rentDao.findAll(), HttpStatus.OK);
  }

  @PostMapping
  @Transactional
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Rent> createRent(@RequestBody RentCreateRequest request) {
    Rent rent = new Rent();
    Date date = new Date();
    rent.setUserId(request.getUserId());
    rent.setCarId(request.getCarId());
    rent.setRentalStartDate(request.getRentalStartDate());
    rent.setRentalFinishDate(request.getRentalFinishDate());
    rent.setCreatedWhen(new Timestamp(date.getTime()));
    rent.setModifyWhen(new Timestamp(date.getTime()));

    Rent savedRent = rentDao.save(rent);

    return new ResponseEntity<>(savedRent, HttpStatus.OK);
  }

  @ApiOperation(value = "Get Rent from server by id")
  @ApiResponses({
    @ApiResponse(code = 200, message = "Successful getting Rent"),
    @ApiResponse(code = 400, message = "Invalid Rent ID supplied"),
    @ApiResponse(code = 401, message = "Lol kek"),
    @ApiResponse(code = 404, message = "Rent was not found"),
    @ApiResponse(code = 500, message = "Server error, something wrong")
  })
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<Rent> getRentById(@ApiParam("Rent Id") @PathVariable Long id) {
    Rent rent = rentDao.findById(id);
    return new ResponseEntity<>(rent, HttpStatus.OK);
  }

  @ApiOperation(value = "Update Rent by rentId")
  @ApiResponses({
    @ApiResponse(code = 200, message = "Successful rent update"),
    @ApiResponse(code = 400, message = "Invalid rent ID supplied"),
    @ApiResponse(code = 404, message = "Rent was not found"),
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

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Rent> updateRent(
      @PathVariable("id") Long rentId, @RequestBody RentCreateRequest request) {
    Rent rent = rentDao.findById(rentId);
    Date date = new Date();
    rent.setUserId(request.getUserId());
    rent.setCarId(request.getCarId());
    rent.setRentalStartDate(request.getRentalStartDate());
    rent.setRentalFinishDate(request.getRentalFinishDate());
    rent.setCreatedWhen(new Timestamp(date.getTime()));
    rent.setModifyWhen(new Timestamp(date.getTime()));
    rentDao.save(rent);

    return new ResponseEntity<>(rent, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Long> deleteRent(@PathVariable("id") Long rentId) {
    rentDao.delete(rentId);
    return new ResponseEntity<>(rentId, HttpStatus.OK);
  }
}
