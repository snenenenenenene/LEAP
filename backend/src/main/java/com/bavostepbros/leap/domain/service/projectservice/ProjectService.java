package com.bavostepbros.leap.domain.service.projectservice;

import java.util.List;
import java.util.Set;

import com.bavostepbros.leap.domain.model.Capability;
import com.bavostepbros.leap.domain.model.Project;

public interface ProjectService {
	Project save(String projectName, Integer programId, Integer statusId);

	Project get(Integer projectId);

	Project update(Integer projectId, String projectName, Integer programId, Integer statusId);

	void delete(Integer projectId);

	List<Project> getAll();

	List<Project> getAllProgramId(Integer programId);

	Project getProjectByName(String projectName);

	Project addCapability(Integer projectId, Integer capabilityId);

	void deleteCapability(Integer projectId, Integer capabilityId);

	Set<Capability> getAllCapabilitiesByProjectId(Integer projectId);
}
