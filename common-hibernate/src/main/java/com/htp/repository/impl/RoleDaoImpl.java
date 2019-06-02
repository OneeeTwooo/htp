package com.htp.repository.impl;

import com.htp.domain.Role;
import com.htp.repository.RoleDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Qualifier("roleDao")
public class RoleDaoImpl implements RoleDao {
  @Override
  public List<Role> getRolesByUserId(Long userId) {
    return null;
  }

  @Override
  public List<Role> findAll() {
    return null;
  }

  @Override
  public Role findById(Long id) {
    return null;
  }

  @Override
  public void delete(Long id) {}

  @Override
  public Role save(Role entity) {
    return null;
  }

  @Override
  public Role update(Role entity) {
    return null;
  }
}
