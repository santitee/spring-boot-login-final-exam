package com.codecamp3.boot.repository;

import org.springframework.data.repository.CrudRepository;

import com.codecamp3.boot.entity.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole, Long>  {

}
