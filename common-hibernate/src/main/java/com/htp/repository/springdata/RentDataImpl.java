package com.htp.repository.springdata;

import com.htp.domain.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RentDataImpl
    extends JpaRepository<Rent, Long>,
        CrudRepository<Rent, Long>,
        PagingAndSortingRepository<Rent, Long> {}
