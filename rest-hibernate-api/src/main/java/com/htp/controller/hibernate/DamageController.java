package com.htp.controller.hibernate;

import com.htp.controller.requests.DamageCreateRequest;
import com.htp.domain.Damage;
import com.htp.repository.DamageDao;
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
@RequestMapping(value = "/rest/hibernate/damages")
public class DamageController {

  @Autowired private DamageDao damageDao;

  @Autowired private RentDao rentDao;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<Damage>> getDamages() {
    return new ResponseEntity<>(damageDao.findAll(), HttpStatus.OK);
  }

  @ApiOperation(value = "Get damage from server by id")
  @ApiResponses({
    @ApiResponse(code = 200, message = "Successful getting damage"),
    @ApiResponse(code = 400, message = "Invalid Damage ID supplied"),
    @ApiResponse(code = 401, message = "Lol kek"),
    @ApiResponse(code = 404, message = "Damage was not found"),
    @ApiResponse(code = 500, message = "Server error, something wrong")
  })
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<Damage> getDamageById(@ApiParam("Damage Path Id") @PathVariable Long id) {
    Damage damage = damageDao.findById(id);
    return new ResponseEntity<>(damage, HttpStatus.OK);
  }

  /**/
  @PostMapping
  @Transactional
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Damage> createDamage(@RequestBody DamageCreateRequest request) {
    Damage damage = new Damage();
    Date date = new Date();

    damage.setName(request.getName());
    damage.setCost(request.getCost());
    damage.setIsDeleted("NO");
    damage.setModifyWhen(new Timestamp(date.getTime()));
    damage.setCreateWhen(new Timestamp(date.getTime()));
    damage.setRent(rentDao.findById(request.getRentId()));

    Damage savedDamage = damageDao.save(damage);

    return new ResponseEntity<>(savedDamage, HttpStatus.OK);
  }

  @ApiOperation(value = "Update damage by damageID")
  @ApiResponses({
    @ApiResponse(code = 200, message = "Successful damage update"),
    @ApiResponse(code = 400, message = "Invalid Damage ID supplied"),
    @ApiResponse(code = 404, message = "Damage was not found"),
    @ApiResponse(code = 500, message = "Server error, something wrong")
  })
  @RequestMapping(value = "/{id}", method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Damage> updateDamage(
      @PathVariable("id") Long damageId, @RequestBody DamageCreateRequest request) {
    Damage damage = damageDao.findById(damageId);
    Date date = new Date();
    damage.setName(request.getName());
    damage.setCost(request.getCost());
    damage.setIsDeleted("NO");
    damage.setModifyWhen(new Timestamp(date.getTime()));
    damage.setCreateWhen(new Timestamp(date.getTime()));
    damage.setRent(rentDao.findById(request.getRentId()));

    damageDao.update(damage);

    return new ResponseEntity<>(damage, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Long> deleteDamage(@PathVariable("id") Long damageId) {
    damageDao.delete(damageId);
    return new ResponseEntity<>(damageId, HttpStatus.OK);
  }
}
