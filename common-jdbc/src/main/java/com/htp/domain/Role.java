package com.htp.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

public class Role {

  private Long idRole;
  private String nameRole;
  private String roleType;

  public Role() {}

  public Role(Long roleId, String nameRole, String roleType) {
    this.idRole = roleId;
    this.nameRole = nameRole;
    this.roleType = roleType;
  }

  public Long getIdRole() {
    return idRole;
  }

  public void setIdRole(Long idRole) {
    this.idRole = idRole;
  }

  public String getNameRole() {
    return nameRole;
  }

  public void setNameRole(String nameRole) {
    this.nameRole = nameRole;
  }

  public String getRoleType() {
    return roleType;
  }

  public void setRoleType(String roleType) {
    this.roleType = roleType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Role role = (Role) o;
    return Objects.equals(idRole, role.idRole)
        && Objects.equals(nameRole, role.nameRole)
        && Objects.equals(roleType, role.roleType);
  }

  @Override
  public int hashCode() {

    return Objects.hash(idRole, nameRole, roleType);
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
