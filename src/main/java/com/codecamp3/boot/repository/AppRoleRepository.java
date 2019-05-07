package com.codecamp3.boot.repository;

import org.springframework.data.repository.CrudRepository;

import com.codecamp3.boot.entity.AppRole;

public interface AppRoleRepository extends CrudRepository<AppRole, Long>  {
   AppRole findByRoleName(String roleName);
}
