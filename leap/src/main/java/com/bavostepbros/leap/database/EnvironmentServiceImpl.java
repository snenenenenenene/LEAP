package com.bavostepbros.leap.database;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bavostepbros.leap.model.Environment;

@Service
@Transactional
public class EnvironmentServiceImpl implements EnvironmentService {
	
	private final EnvironmentDAL environmentDAL;
	
	@Autowired
	public EnvironmentServiceImpl(EnvironmentDAL environmentDAL) {
		super();
		this.environmentDAL = environmentDAL;
	}

	@Override
	public boolean save(Environment environment) {
		try {
			environmentDAL.save(environment);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Environment get(Integer id) {
		Environment environment = environmentDAL.findById(id).get();
		return environment;
	}

	@Override
	public void update(Environment environment) {
		environmentDAL.save(environment);
	}

	@Override
	public void delete(Integer id) {
		environmentDAL.deleteById(id);
	}

}
