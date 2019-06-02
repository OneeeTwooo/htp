package com.htp.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "roles")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_role")
  private Long roleId;

  @Column(name = "name_role")
  private String nameRole;

  @Column(name = "type_role")
  private String typeRole;

  //    @JsonBackReference
  //    @ManyToOne(fetch = FetchType.EAGER)
  //    @JoinColumn(name = "role_user_id")
  //    private TestUser user;

  public Role() {}

  public Role(String nameRole, String typeRole) {
    this.nameRole = nameRole;
    this.typeRole = typeRole;
  }

  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }

  public String getNameRole() {
    return nameRole;
  }

  public void setNameRole(String nameRole) {
    this.nameRole = nameRole;
  }

  public String getTypeRole() {
    return typeRole;
  }

  public void setTypeRole(String typeRole) {
    this.typeRole = typeRole;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Role role = (Role) o;
    return Objects.equals(roleId, role.roleId)
        && Objects.equals(nameRole, role.nameRole)
        && Objects.equals(typeRole, role.typeRole);
  }

  @Override
  public int hashCode() {

    return Objects.hash(roleId, nameRole, typeRole);
  }

  @Override
  public String toString() {
    return "Role{"
        + "roleId="
        + roleId
        + ", nameRole='"
        + nameRole
        + '\''
        + ", typeRole='"
        + typeRole
        + '\''
        + '}';
  }
}
