package com.bavostepbros.leap.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bavostepbros.leap.domain.model.Technology;

public interface TechnologyDAL extends JpaRepository<Technology, Integer> {

}
