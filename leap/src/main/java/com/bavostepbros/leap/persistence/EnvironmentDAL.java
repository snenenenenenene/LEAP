package com.bavostepbros.leap.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bavostepbros.leap.domain.model.Environment;

public interface EnvironmentDAL extends JpaRepository<Environment, Integer>{
	List<Environment> findByEnvironmentName(String environmentName);
}