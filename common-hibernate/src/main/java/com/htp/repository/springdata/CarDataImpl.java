package com.htp.repository.springdata;

import com.htp.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CarDataImpl
    extends JpaRepository<Car, Long>,
        CrudRepository<Car, Long>,
        PagingAndSortingRepository<Car, Long> {}
