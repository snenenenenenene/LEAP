package com.bavostepbros.leap.domain.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.bavostepbros.leap.domain.model.capabilitylevel.CapabilityLevel;
import com.bavostepbros.leap.domain.model.paceofchange.PaceOfChange;
import com.bavostepbros.leap.domain.model.targetoperatingmodel.TargetOperatingModel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "ENVIRONMENTID", "CAPABILITYNAME" }) })
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Capability {

	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	@Column(name = "CAPABILITYID")
	private Integer capabilityId;

	@ManyToOne
	@JoinColumn(name = "ENVIRONMENTID", nullable = false)
	private Environment environment;

	@OneToOne
	@JoinColumn
	private Status status;

	@PositiveOrZero(message = "The capability id must be positive.")
	@Column(name = "PARENTCAPABILITYID")
	private Integer parentCapabilityId;

	@NotBlank(message = "Capability name is required.")
	@Column(name = "CAPABILITYNAME")
	private String capabilityName;

	@Column(name = "CAPABILITYDESCRIPTION")
	private String capabilityDescription;

	@Column(name = "LEVEL")
	private CapabilityLevel level;

	@NotNull(message = "Pace of change must not be null.")
	@Column(name = "PACEOFCHANGE")
	private PaceOfChange paceOfChange;

	@NotNull(message = "Target operating model must not be null.")
	@Column(name = "TARGETOPERATINGMODEL")
	private TargetOperatingModel targetOperatingModel;

	@NotNull(message = "Resource quality must not be null.")
	@Min(value = 1, message = "Resource quality must be between 1 and 5, inclusive.")
	@Max(value = 5, message = "Resource quality must be between 1 and 5, inclusive.")
	@Column(name = "RESOURCEQUALITY")
	private Integer resourceQuality;

	@Column(name = "INFORMATIONQUALITY")
	private Double informationQuality;

	@Column(name = "APPLICATIONFIT")
	private Double applicationFit;

	@OneToMany(mappedBy = "capability")
	private List<CapabilityItem> capabilityItems;

	@OneToMany(mappedBy = "capability")
	private List<CapabilityApplication> capabilityApplication;

	@OneToMany(mappedBy = "capability")
	private List<CapabilityInformation> capabilityInformation;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "CAPABILITY_PROJECT", joinColumns = { @JoinColumn(name = "CAPABILITYID") }, inverseJoinColumns = {
			@JoinColumn(name = "PROJECTID") }, uniqueConstraints = {
					@UniqueConstraint(columnNames = { "CAPABILITYID", "PROJECTID" }) })
	private Set<Project> projects = new HashSet<>();

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "CAPABILITY_BUSINESSPROCESS", joinColumns = {
			@JoinColumn(name = "CAPABILITYID") }, inverseJoinColumns = {
					@JoinColumn(name = "BUSINESSPROCESSID") }, uniqueConstraints = {
							@UniqueConstraint(columnNames = { "CAPABILITYID", "BUSINESSPROCESSID" }) })
	private Set<BusinessProcess> businessProcess = new HashSet<>();

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "CAPABILITY_RESOURCE", joinColumns = {
			@JoinColumn(name = "CAPABILITYID") }, inverseJoinColumns = {
					@JoinColumn(name = "RESOURCEID") }, uniqueConstraints = {
							@UniqueConstraint(columnNames = { "CAPABILITYID", "RESOURCEID" }) })
	private Set<Resource> resources = new HashSet<>();

	public Capability(Environment environment, Status status, Integer parentCapabilityId, String capabilityName,
			String capabilityDescription, PaceOfChange paceOfChange, TargetOperatingModel targetOperatingModel,
			Integer resourceQuality, Double informationQuality, Double applicationFit) {
		this.environment = environment;
		this.status = status;
		this.parentCapabilityId = parentCapabilityId;
		this.capabilityName = capabilityName;
		this.capabilityDescription = capabilityDescription;
		this.paceOfChange = paceOfChange;
		this.targetOperatingModel = targetOperatingModel;
		this.resourceQuality = resourceQuality;
		this.informationQuality = informationQuality;
		this.applicationFit = applicationFit;
	}

	public Capability(Integer capabilityId, Environment environment, Status status, Integer parentCapabilityId,
			String capabilityName, String capabilityDescription, PaceOfChange paceOfChange,
			TargetOperatingModel targetOperatingModel, Integer resourceQuality, Double informationQuality,
			Double applicationFit) {
		this.capabilityId = capabilityId;
		this.environment = environment;
		this.status = status;
		this.parentCapabilityId = parentCapabilityId;
		this.capabilityName = capabilityName;
		this.capabilityDescription = capabilityDescription;
		this.paceOfChange = paceOfChange;
		this.targetOperatingModel = targetOperatingModel;
		this.resourceQuality = resourceQuality;
		this.informationQuality = informationQuality;
		this.applicationFit = applicationFit;
	}

	public Capability(Environment environment, Status status, Integer parentCapabilityId,
			String capabilityName, String capabilityDescription, PaceOfChange paceOfChange,
			TargetOperatingModel targetOperatingModel, Integer resourceQuality) {
		this.environment = environment;
		this.status = status;
		this.parentCapabilityId = parentCapabilityId;
		this.capabilityName = capabilityName;
		this.capabilityDescription = capabilityDescription;
		this.paceOfChange = paceOfChange;
		this.targetOperatingModel = targetOperatingModel;
		this.resourceQuality = resourceQuality;
	}
	
	public Capability(Integer capabilityId, Environment environment, Status status, Integer parentCapabilityId,
			String capabilityName, String capabilityDescription, PaceOfChange paceOfChange,
			TargetOperatingModel targetOperatingModel, Integer resourceQuality) {
		this.capabilityId = capabilityId;
		this.environment = environment;
		this.status = status;
		this.parentCapabilityId = parentCapabilityId;
		this.capabilityName = capabilityName;
		this.capabilityDescription = capabilityDescription;
		this.paceOfChange = paceOfChange;
		this.targetOperatingModel = targetOperatingModel;
		this.resourceQuality = resourceQuality;
	}

	public Capability(Integer capabilityId, Environment environment, Status status, Integer parentCapabilityId) {
		this.capabilityId = capabilityId;
		this.environment = environment;
		this.status = status;
		this.parentCapabilityId = parentCapabilityId;
		this.capabilityName = "Default " + capabilityId;
		this.paceOfChange = PaceOfChange.STANDARD;
		this.targetOperatingModel = TargetOperatingModel.COORDINATION;
		this.resourceQuality = 1;
	}

	/**
	 * @param project
	 */
	public void addProject(Project project) {
		projects.add(project);
		project.getCapabilities().add(this);
	}

	/**
	 * @param project
	 */
	public void removeProject(Project project) {
		projects.remove(project);
		project.getCapabilities().remove(this);
	}

	/**
	 * @return Set<Project>
	 */
	public Set<Project> getProjects() {
		return projects;
	}

	/**
	 * @param businessProcessItem
	 */
	public void addBusinessProcess(BusinessProcess businessProcessItem) {
		businessProcess.add(businessProcessItem);
		businessProcessItem.getCapabilities().add(this);
	}

	/**
	 * @param businessProcessItem
	 */
	public void removeBusinessProcess(BusinessProcess businessProcessItem) {
		businessProcess.remove(businessProcessItem);
		businessProcessItem.getCapabilities().remove(this);
	}

	/**
	 * @return Set<BusinessProcess>
	 */
	public Set<BusinessProcess> getBusinessProcess() {
		return businessProcess;
	}

	/**
	 * @param resource
	 */
	public void addResource(Resource resource) {
		resources.add(resource);
		resource.getCapabilities().add(this);
	}

	/**
	 * @param resource
	 */
	public void removeResource(Resource resource) {
		resources.remove(resource);
		resource.getCapabilities().remove(this);
	}

	/**
	 * @return Set<Resource>
	 */
	public Set<Resource> getResources() {
		return resources;
	}

}