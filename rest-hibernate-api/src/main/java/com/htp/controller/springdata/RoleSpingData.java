package com.htp.controller.springdata;

import com.htp.controller.requests.RoleCreateRequest;
import com.htp.domain.Role;
import com.htp.repository.springdata.RoleDataImpl;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/springdata/roles")
public class RoleSpingData {
  @Autowired
  @Qualifier("roleDataImpl")
  private RoleDataImpl roleData;

  @GetMapping("/all")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Page<Role>> getRoles(@ApiIgnore Pageable pageable) {
    return new ResponseEntity<>(roleData.findAll(pageable), HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<Optional<Role>> getRoleById(
      @ApiParam("Role Path Id") @PathVariable Long id) {
    Optional<Role> role = roleData.findById(id);
    return new ResponseEntity<>(role, HttpStatus.OK);
  }

  @PostMapping
  // @Transactional
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Role> createRole(@RequestBody RoleCreateRequest request) {
    Role role = new Role();

    role.setNameRole(request.getNameRole());
    role.setTypeRole(request.getRoleType());

    Role savedRole = roleData.saveAndFlush(role);

    return new ResponseEntity<>(savedRole, HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Role> updateRole(
      @PathVariable("id") Long roleId, @RequestBody RoleCreateRequest request) {
    Role role = roleData.findById(roleId).get();

    role.setNameRole(request.getNameRole());
    role.setTypeRole(request.getRoleType());

    roleData.saveAndFlush(role);
    return new ResponseEntity<>(role, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Long> deleteRole(@PathVariable("id") Long roleId) {
    roleData.deleteById(roleId);
    return new ResponseEntity<>(roleId, HttpStatus.OK);
  }
}
