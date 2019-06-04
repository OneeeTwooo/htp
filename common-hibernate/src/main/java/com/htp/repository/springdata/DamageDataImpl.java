package com.htp.repository.springdata;

import com.htp.domain.Damage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DamageDataImpl
    extends JpaRepository<Damage, Long>,
        CrudRepository<Damage, Long>,
        PagingAndSortingRepository<Damage, Long> {}
