package com.htp.controller.requests;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

public class RoleCreateRequest {
  private String nameRole;
  private String roleType;

  public RoleCreateRequest() {}

  public RoleCreateRequest(String nameRole, String roleType) {
    this.nameRole = nameRole;
    this.roleType = roleType;
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
    RoleCreateRequest that = (RoleCreateRequest) o;
    return Objects.equals(nameRole, that.nameRole) && Objects.equals(roleType, that.roleType);
  }

  @Override
  public int hashCode() {

    return Objects.hash(nameRole, roleType);
  }

  @Override
  public String toString() {
    {
      return ToStringBuilder.reflectionToString(this);
    }
  }
}
