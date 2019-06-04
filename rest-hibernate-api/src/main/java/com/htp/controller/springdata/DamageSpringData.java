package com.htp.controller.springdata;

import com.htp.controller.requests.DamageCreateRequest;
import com.htp.domain.Damage;
import com.htp.repository.springdata.DamageDataImpl;
import com.htp.repository.springdata.RentDataImpl;
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
@RequestMapping(value = "/rest/springdata/damages")
public class DamageSpringData {

  @Autowired
  @Qualifier("damageDataImpl")
  private DamageDataImpl damageData;

  @Autowired
  @Qualifier("rentDataImpl")
  private RentDataImpl rentData;

  @GetMapping("/all")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Page<Damage>> getDamages(@ApiIgnore Pageable pageable) {
    return new ResponseEntity<>(damageData.findAll(pageable), HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<Optional<Damage>> getDamageById(
      @ApiParam("Damage Path Id") @PathVariable Long id) {
    Optional<Damage> damage = damageData.findById(id);
    return new ResponseEntity<>(damage, HttpStatus.OK);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Damage> createDamage(@RequestBody DamageCreateRequest request) {
    Damage damage = new Damage();
    Date date = new Date();

    damage.setName(request.getName());
    damage.setCost(request.getCost());
    damage.setIsDeleted("NO");
    damage.setModifyWhen(new Timestamp(date.getTime()));
    damage.setCreateWhen(new Timestamp(date.getTime()));
    damage.setRent(rentData.findById(request.getRentId()).get());

    Damage savedDamage = damageData.saveAndFlush(damage);

    return new ResponseEntity<>(savedDamage, HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Damage> updateDamage(
      @PathVariable("id") Long damageId, @RequestBody DamageCreateRequest request) {
    Damage damage = damageData.findById(damageId).get();
    Date date = new Date();
    damage.setName(request.getName());
    damage.setCost(request.getCost());
    damage.setIsDeleted("NO");
    damage.setModifyWhen(new Timestamp(date.getTime()));
    damage.setCreateWhen(new Timestamp(date.getTime()));
    damage.setRent(rentData.findById(request.getRentId()).get());

    damageData.saveAndFlush(damage);

    return new ResponseEntity<>(damage, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Long> deleteDamage(@PathVariable("id") Long damageId) {
    damageData.deleteById(damageId);
    return new ResponseEntity<>(damageId, HttpStatus.OK);
  }
}
