package com.htp.controller.springdata;

import com.htp.controller.requests.RentCreateRequest;
import com.htp.domain.Rent;
import com.htp.repository.springdata.CarDataImpl;
import com.htp.repository.springdata.RentDataImpl;
import com.htp.repository.springdata.UserDataImpl;
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
@RequestMapping(value = "/rest/springdata/rents")
public class RentSpringData {

  @Autowired
  @Qualifier("rentDataImpl")
  private RentDataImpl rentData;

  @Autowired
  @Qualifier("userDataImpl")
  private UserDataImpl userData;

  @Autowired
  @Qualifier("carDataImpl")
  private CarDataImpl carData;

  @GetMapping("/all")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Page<Rent>> getRents(@ApiIgnore Pageable pageable) {
    return new ResponseEntity<>(rentData.findAll(pageable), HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<Optional<Rent>> getRentById(
      @ApiParam("Rent Path Id") @PathVariable Long id) {
    Optional<Rent> rent = rentData.findById(id);
    return new ResponseEntity<>(rent, HttpStatus.OK);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Rent> createRent(@RequestBody RentCreateRequest request) {
    Rent rent = new Rent();
    Date date = new Date();

    rent.setRentalStartDate(new Timestamp(date.getTime()));
    rent.setRentalFinishDate(new Timestamp(date.getTime()));
    rent.setCreatedWhen(new Timestamp(date.getTime()));
    rent.setModifyWhen(new Timestamp(date.getTime()));
    rent.setCar(carData.findById(request.getCarId()).get());
    rent.setUser(userData.findById(request.getUserId()).get());

    Rent savedRent = rentData.saveAndFlush(rent);

    return new ResponseEntity<>(savedRent, HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Rent> updateRent(
      @PathVariable("id") Long rentId, @RequestBody RentCreateRequest request) {
    Rent rent = rentData.findById(rentId).get();
    Date date = new Date();
    rent.setRentalStartDate(new Timestamp(date.getTime()));
    rent.setRentalFinishDate(new Timestamp(date.getTime()));
    rent.setCreatedWhen(new Timestamp(date.getTime()));
    rent.setModifyWhen(new Timestamp(date.getTime()));
    rent.setCar(carData.findById(request.getCarId()).get());
    rent.setUser(userData.findById(request.getUserId()).get());
    rentData.saveAndFlush(rent);

    return new ResponseEntity<>(rent, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Long> deleteRent(@PathVariable("id") Long rentId) {
    rentData.deleteById(rentId);
    return new ResponseEntity<>(rentId, HttpStatus.OK);
  }
}
