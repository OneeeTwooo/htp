package com.htp.repository.springdata;

import com.htp.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleDataImpl
    extends JpaRepository<Role, Long>,
        CrudRepository<Role, Long>,
        PagingAndSortingRepository<Role, Long> {}
