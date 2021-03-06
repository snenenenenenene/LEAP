package com.bavostepbros.leap.integrationtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.bavostepbros.leap.domain.model.Capability;
import com.bavostepbros.leap.domain.model.CapabilityApplication;
import com.bavostepbros.leap.domain.model.Environment;
import com.bavostepbros.leap.domain.model.ITApplication;
import com.bavostepbros.leap.domain.model.Status;
import com.bavostepbros.leap.domain.model.dto.CapabilityApplicationDto;
import com.bavostepbros.leap.domain.model.paceofchange.PaceOfChange;
import com.bavostepbros.leap.domain.model.targetoperatingmodel.TargetOperatingModel;
import com.bavostepbros.leap.domain.model.timevalue.TimeValue;
import com.bavostepbros.leap.domain.service.capabilityapplicationservice.CapabilityApplicationService;
import com.bavostepbros.leap.persistence.CapabilityApplicationDAL;
import com.bavostepbros.leap.persistence.CapabilityDAL;
import com.bavostepbros.leap.persistence.EnvironmentDAL;
import com.bavostepbros.leap.persistence.ITApplicationDAL;
import com.bavostepbros.leap.persistence.StatusDAL;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CapabilityApplicationControllerTest extends ApiIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private StatusDAL statusDAL;

	@Autowired
	private EnvironmentDAL environmentDAL;

	@Autowired
	private CapabilityDAL capabilityDAL;

	@Autowired
	private ITApplicationDAL itApplicationDAL;

	@Autowired
	private CapabilityApplicationDAL capabilityApplicationDAL;

	@Autowired
	private CapabilityApplicationService capabilityApplicationService;

	static final String PATH = "/api/capabilityapplication/";

	private Status statusFirst;
	private Status statusSecond;
	private Environment environmentFirst;
	private Environment environmentSecond;
	private Capability capabilityFirst;
	private Capability capabilitySecond;
	private ITApplication itApplicationFirst;
	private ITApplication itApplicationSecond;
	private CapabilityApplication capabilityApplicationFirst;
	private CapabilityApplication capabilityApplicationSecond;
	private CapabilityApplication capabilityApplicationThird;
	
	private Integer capabilityId;
	private Integer applicationId;
	private Integer efficiencySupport;
	private Integer functionalCoverage;
	private Integer correctnessBusinessFit;
	private Integer futurePotential;
	private Integer completeness;
	private Integer correctnessInformationFit;
	private Integer availability;

	@BeforeAll
	public void authenticate() throws Exception {
		super.authenticate();
	}

	@BeforeEach
	public void init() {
		statusFirst = statusDAL.save(new Status(1, LocalDate.of(2021, 05, 15)));
		statusSecond = statusDAL.save(new Status(2, LocalDate.of(2021, 05, 20)));
		environmentFirst = environmentDAL.save(new Environment(1, "Test 1"));
		environmentSecond = environmentDAL.save(new Environment(2, "Test 2"));
		capabilityFirst = capabilityDAL.save(new Capability(1, environmentFirst, statusFirst, 1, "Capability 1",
				"Description 1", PaceOfChange.DIFFERENTIATION, TargetOperatingModel.COORDINATION, 1, 4.0, 4.0));
		capabilitySecond = capabilityDAL.save(
				new Capability(2, environmentFirst, statusFirst, capabilityFirst.getCapabilityId(), "Capability 2",
						"Description 2", PaceOfChange.INNOVATIVE, TargetOperatingModel.DIVERSIFICATION, 1, 4.0, 4.0));
		itApplicationFirst = itApplicationDAL.save(new ITApplication(1, statusFirst, "application 1", "1.20.1",
				LocalDate.of(2021, 01, 20), LocalDate.of(2025, 05, 20), 1, 2, 3, 4, 5, 4, 3, 2, "EUR", 1000.0, 5, 70.0,
				100.0, TimeValue.MIGRATE));
		itApplicationSecond = itApplicationDAL.save(new ITApplication(2, statusSecond, "application 2", "1.20.1",
				LocalDate.of(2021, 01, 20), LocalDate.of(2025, 05, 20), 2, 3, 4, 5, 4, 3, 2, 1, "EUR", 1000.0, 4, 70.0,
				100.0, TimeValue.TOLERATE));
		capabilityApplicationFirst = capabilityApplicationDAL
				.save(new CapabilityApplication(capabilityFirst, itApplicationFirst, 1.0, 1, 2, 3, 4, 5, 4, 3));
		capabilityApplicationSecond = capabilityApplicationDAL
				.save(new CapabilityApplication(capabilitySecond, itApplicationFirst, 0.50, 2, 3, 4, 5, 4, 3, 2));
		capabilityApplicationThird = capabilityApplicationDAL
				.save(new CapabilityApplication(capabilitySecond, itApplicationSecond, 0.50, 5, 4, 3, 2, 1, 2, 3));
		
		capabilityId = capabilityApplicationFirst.getCapability().getCapabilityId();
		applicationId = capabilityApplicationFirst.getApplication().getItApplicationId();
		efficiencySupport = capabilityApplicationFirst.getEfficiencySupport();
		functionalCoverage = capabilityApplicationFirst.getFunctionalCoverage();
		correctnessBusinessFit = capabilityApplicationFirst.getCorrectnessBusinessFit();
		futurePotential = capabilityApplicationFirst.getFuturePotential();
		completeness = capabilityApplicationFirst.getCompleteness();
		correctnessInformationFit = capabilityApplicationFirst.getCorrectnessInformationFit();
		availability = capabilityApplicationFirst.getAvailability();
	}

	@AfterEach
	public void close() {
		statusDAL.delete(statusFirst);
		statusDAL.delete(statusSecond);
		environmentDAL.delete(environmentFirst);
		environmentDAL.delete(environmentSecond);
		capabilityDAL.delete(capabilityFirst);
		capabilityDAL.delete(capabilitySecond);
		itApplicationDAL.delete(itApplicationFirst);
		itApplicationDAL.delete(itApplicationSecond);
		capabilityApplicationDAL.delete(capabilityApplicationFirst);
		capabilityApplicationDAL.delete(capabilityApplicationSecond);
		capabilityApplicationDAL.delete(capabilityApplicationThird);
	}

	@Test
	void should_notBeNull() {
		assertNotNull(statusDAL);
		assertNotNull(environmentDAL);
		assertNotNull(capabilityDAL);
		assertNotNull(itApplicationDAL);
		assertNotNull(capabilityApplicationDAL);
		assertNotNull(capabilityApplicationService);
		assertNotNull(statusFirst);
		assertNotNull(statusSecond);
		assertNotNull(environmentFirst);
		assertNotNull(environmentSecond);
		assertNotNull(capabilityFirst);
		assertNotNull(capabilitySecond);
		assertNotNull(itApplicationFirst);
		assertNotNull(itApplicationSecond);
		assertNotNull(capabilityApplicationFirst);
		assertNotNull(capabilityApplicationSecond);
		assertNotNull(capabilityApplicationThird);
	}

	@Test
	public void should_postCapabilityApplication_whenSaveCapabilityApplication() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						post(PATH + capabilityId + "/" + applicationId).contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
								.param("efficiencySupport", efficiencySupport.toString())
								.param("functionalCoverage", functionalCoverage.toString())
								.param("correctnessBusinessFit", correctnessBusinessFit.toString())
								.param("futurePotential", futurePotential.toString())
								.param("completeness", completeness.toString())
								.param("correctnessInformationFit", correctnessInformationFit.toString())
								.param("availability", availability.toString()).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		CapabilityApplicationDto capabilityApplicationDto = objectMapper
				.readValue(mvcResult.getResponse().getContentAsString(), CapabilityApplicationDto.class);

		CapabilityApplication capabilityApplication = capabilityApplicationService.get(capabilityId, applicationId);

		assertNotNull(capabilityApplicationDto);
		testCapabilityApplication(capabilityApplication, capabilityApplicationDto);
	}

	@Test
	public void should_getCapabilityApplication_whenGetCapabilityApplication() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get(PATH + capabilityId + "/" + applicationId))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		CapabilityApplicationDto capabilityApplicationDto = objectMapper
				.readValue(mvcResult.getResponse().getContentAsString(), CapabilityApplicationDto.class);

		assertNotNull(capabilityApplicationDto);
		testCapabilityApplication(capabilityApplicationFirst, capabilityApplicationDto);
	}

	@Test
	public void should_putCapabilityApplication_whenUpdateCapabilityApplication() throws Exception {
		Integer efficiencySupport = 5;

		MvcResult mvcResult = mockMvc
				.perform(put(PATH + capabilityId + "/" + applicationId).contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
						.param("efficiencySupport", efficiencySupport.toString())
						.param("functionalCoverage", functionalCoverage.toString())
						.param("correctnessBusinessFit", correctnessBusinessFit.toString())
						.param("futurePotential", futurePotential.toString())
						.param("completeness", completeness.toString())
						.param("correctnessInformationFit", correctnessInformationFit.toString())
						.param("availability", availability.toString()).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		CapabilityApplicationDto capabilityApplicationDto = objectMapper
				.readValue(mvcResult.getResponse().getContentAsString(), CapabilityApplicationDto.class);

		CapabilityApplication capabilityApplication = capabilityApplicationService.get(capabilityId, applicationId);

		assertNotNull(capabilityApplicationDto);
		testCapabilityApplication(capabilityApplication, capabilityApplicationDto);
	}

	@Test
	public void should_deleteCapabilityApplication_whenDeleteCapabilityApplication() throws Exception {
		mockMvc.perform(delete(PATH + capabilityId + "/" + applicationId))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void should_getCapabilityApplications_whenGetlAllByCapabilityIdCapabilityApplication() throws Exception {
		Integer capabilityId = capabilityApplicationSecond.getCapability().getCapabilityId();

		MvcResult mvcResult = mockMvc.perform(get(PATH + "all-capabilityApplications-by-capabilityid/" + capabilityId))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		List<CapabilityApplicationDto> capabilityApplicationDto = objectMapper.readValue(
				mvcResult.getResponse().getContentAsString(), new TypeReference<List<CapabilityApplicationDto>>() {});

		assertNotNull(capabilityApplicationDto);
		assertEquals(2, capabilityApplicationDto.size());
		testCapabilityApplication(capabilityApplicationSecond, capabilityApplicationDto.get(0));
		testCapabilityApplication(capabilityApplicationThird, capabilityApplicationDto.get(1));
	}
	
	@Test
	public void should_getCapabilityApplications_whenGetlAllByApplicationIdCapabilityApplication() throws Exception {
		Integer applicationId = capabilityApplicationFirst.getApplication().getItApplicationId();

		MvcResult mvcResult = mockMvc.perform(get(PATH + "all-capabilityApplications-by-applicationid/" + applicationId))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		List<CapabilityApplicationDto> capabilityApplicationDto = objectMapper.readValue(
				mvcResult.getResponse().getContentAsString(), new TypeReference<List<CapabilityApplicationDto>>() {});

		assertNotNull(capabilityApplicationDto);
		assertEquals(2, capabilityApplicationDto.size());
		testCapabilityApplication(capabilityApplicationFirst, capabilityApplicationDto.get(0));
		testCapabilityApplication(capabilityApplicationSecond, capabilityApplicationDto.get(1));
	}

	private void testCapabilityApplication(CapabilityApplication expectedObject,
			CapabilityApplicationDto actualObject) {
		assertEquals(expectedObject.getCapability().getCapabilityId(), actualObject.getCapability().getCapabilityId());
		assertEquals(expectedObject.getCapability().getEnvironment().getEnvironmentId(),
				actualObject.getCapability().getEnvironment().getEnvironmentId());
		assertEquals(expectedObject.getCapability().getEnvironment().getEnvironmentName(),
				actualObject.getCapability().getEnvironment().getEnvironmentName());
		assertEquals(expectedObject.getCapability().getStatus().getStatusId(),
				actualObject.getCapability().getStatus().getStatusId());
		assertEquals(expectedObject.getCapability().getStatus().getValidityPeriod(),
				actualObject.getCapability().getStatus().getValidityPeriod());
		assertEquals(expectedObject.getCapability().getParentCapabilityId(),
				actualObject.getCapability().getParentCapabilityId());
		assertEquals(expectedObject.getCapability().getCapabilityName(),
				actualObject.getCapability().getCapabilityName());
		assertEquals(expectedObject.getCapability().getLevel(), actualObject.getCapability().getLevel());
		assertEquals(expectedObject.getCapability().getPaceOfChange(), actualObject.getCapability().getPaceOfChange());
		assertEquals(expectedObject.getCapability().getTargetOperatingModel(),
				actualObject.getCapability().getTargetOperatingModel());
		assertEquals(expectedObject.getCapability().getResourceQuality(),
				actualObject.getCapability().getResourceQuality());
		assertEquals(expectedObject.getCapability().getInformationQuality(),
				actualObject.getCapability().getInformationQuality());
		assertEquals(expectedObject.getCapability().getApplicationFit(),
				actualObject.getCapability().getApplicationFit());

		assertEquals(expectedObject.getApplication().getItApplicationId(),
				actualObject.getApplication().getItApplicationId());
		assertEquals(expectedObject.getApplication().getStatus().getStatusId(),
				actualObject.getApplication().getStatus().getStatusId());
		assertEquals(expectedObject.getApplication().getStatus().getValidityPeriod(),
				actualObject.getApplication().getStatus().getValidityPeriod());
		assertEquals(expectedObject.getApplication().getName(), actualObject.getApplication().getName());
		assertEquals(expectedObject.getApplication().getVersion(), actualObject.getApplication().getVersion());
		assertEquals(expectedObject.getApplication().getPurchaseDate(),
				actualObject.getApplication().getPurchaseDate());
		assertEquals(expectedObject.getApplication().getEndOfLife(), actualObject.getApplication().getEndOfLife());
		assertEquals(expectedObject.getApplication().getCurrentScalability(),
				actualObject.getApplication().getCurrentScalability());
		assertEquals(expectedObject.getApplication().getExpectedScalability(),
				actualObject.getApplication().getExpectedScalability());
		assertEquals(expectedObject.getApplication().getCurrentPerformance(),
				actualObject.getApplication().getCurrentPerformance());
		assertEquals(expectedObject.getApplication().getExpectedPerformance(),
				actualObject.getApplication().getExpectedPerformance());
		assertEquals(expectedObject.getApplication().getCurrentSecurityLevel(),
				actualObject.getApplication().getCurrentSecurityLevel());
		assertEquals(expectedObject.getApplication().getExpectedSecurityLevel(),
				actualObject.getApplication().getExpectedSecurityLevel());
		assertEquals(expectedObject.getApplication().getCurrentStability(),
				actualObject.getApplication().getCurrentStability());
		assertEquals(expectedObject.getApplication().getExpectedStability(),
				actualObject.getApplication().getExpectedStability());
		assertEquals(expectedObject.getApplication().getCurrencyType(),
				actualObject.getApplication().getCurrencyType());
		assertEquals(expectedObject.getApplication().getCostCurrency(),
				actualObject.getApplication().getCostCurrency());
		assertEquals(expectedObject.getApplication().getCurrentValue(),
				actualObject.getApplication().getCurrentValue());
		assertEquals(expectedObject.getApplication().getCurrentYearlyCost(),
				actualObject.getApplication().getCurrentYearlyCost());
		assertEquals(expectedObject.getApplication().getAcceptedYearlyCost(),
				actualObject.getApplication().getAcceptedYearlyCost());
		assertEquals(expectedObject.getApplication().getTimeValue(), actualObject.getApplication().getTimeValue());

		assertEquals(expectedObject.getImportance(), actualObject.getImportance());
		assertEquals(expectedObject.getEfficiencySupport(), actualObject.getEfficiencySupport());
		assertEquals(expectedObject.getFunctionalCoverage(), actualObject.getFunctionalCoverage());
		assertEquals(expectedObject.getCorrectnessBusinessFit(), actualObject.getCorrectnessBusinessFit());
		assertEquals(expectedObject.getFuturePotential(), actualObject.getFuturePotential());
		assertEquals(expectedObject.getCompleteness(), actualObject.getCompleteness());
		assertEquals(expectedObject.getCorrectnessInformationFit(), actualObject.getCorrectnessInformationFit());
		assertEquals(expectedObject.getAvailability(), actualObject.getAvailability());
	}
}
