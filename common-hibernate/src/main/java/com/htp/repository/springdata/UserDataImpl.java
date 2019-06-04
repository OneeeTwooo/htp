package com.htp.repository.springdata;

import com.htp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDataImpl
    extends JpaRepository<User, Long>,
        CrudRepository<User, Long>,
        PagingAndSortingRepository<User, Long> {
//
//  @Query("select u from User u where u.userId > :lowerId")
//  User findByUserId(long id);
}
