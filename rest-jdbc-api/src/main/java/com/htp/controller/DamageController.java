package com.htp.controller;

import com.htp.controller.requests.DamageCreateRequest;
import com.htp.domain.Damage;
import com.htp.repository.DamageDao;
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
@RequestMapping(value = "/rest/damages")
public class DamageController {
  @Autowired private DamageDao damageDao;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<Damage>> getDamages() {
    return new ResponseEntity<>(damageDao.findAll(), HttpStatus.OK);
  }

  @PostMapping
  @Transactional
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Damage> createDamage(@RequestBody DamageCreateRequest request) {
    Damage damage = new Damage();
    Date date = new Date();
    // damageId is empty - will be generated by DB

    damage.setName(request.getName());
    damage.setCost(request.getCost());
    damage.setIsDeleted("NO");
    damage.setCreatedWhen(new Timestamp(date.getTime()));
    damage.setModifyWhen(new Timestamp(date.getTime()));
    Damage savedDamage = damageDao.save(damage);

    return new ResponseEntity<>(savedDamage, HttpStatus.OK);
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
  public ResponseEntity<Damage> getDamageById(@ApiParam("Damage Id") @PathVariable Long id) {
    Damage damage = damageDao.findById(id);
    return new ResponseEntity<>(damage, HttpStatus.OK);
  }

  @ApiOperation(value = "Update damage by damageId")
  @ApiResponses({
    @ApiResponse(code = 200, message = "Successful damage update"),
    @ApiResponse(code = 400, message = "Invalid damage ID supplied"),
    @ApiResponse(code = 404, message = "Damage was not found"),
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
  public ResponseEntity<Damage> updateDamage(
      @PathVariable("id") Long damageId, @RequestBody DamageCreateRequest request) {
    Damage damage = damageDao.findById(damageId);
    Date date = new Date();
    // damageId is empty - will be generated by DB

    damage.setName(request.getName());
    damage.setCost(request.getCost());
    damage.setIsDeleted(request.getIsDeleted());
    damage.setCreatedWhen(damage.getCreatedWhen());
    damage.setModifyWhen(new Timestamp(date.getTime()));

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
