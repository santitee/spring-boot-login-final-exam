package com.codecamp3.boot.repository;

import org.springframework.data.repository.CrudRepository;

import com.codecamp3.boot.entity.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, Long>  {
   AppUser findByUserName(String userName);
}
