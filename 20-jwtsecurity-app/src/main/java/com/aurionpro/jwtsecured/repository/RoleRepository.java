package com.aurionpro.jwtsecured.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurionpro.jwtsecured.entity.Role;



@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

	public Role findByRoleName(String roleName);
}
