package com.aurionpro.jwtsecured.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurionpro.jwtsecured.entity.User;

@Repository
public interface UserRespository extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String username);
	boolean existsByUsername(String username);
}
