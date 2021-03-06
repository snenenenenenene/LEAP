package com.bavostepbros.leap.unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bavostepbros.leap.domain.model.Capability;
import com.bavostepbros.leap.domain.model.CapabilityApplication;
import com.bavostepbros.leap.domain.model.Environment;
import com.bavostepbros.leap.domain.model.ITApplication;
import com.bavostepbros.leap.domain.model.Status;
import com.bavostepbros.leap.domain.model.Technology;
import com.bavostepbros.leap.domain.model.paceofchange.PaceOfChange;
import com.bavostepbros.leap.domain.model.targetoperatingmodel.TargetOperatingModel;
import com.bavostepbros.leap.domain.model.timevalue.TimeValue;
import com.bavostepbros.leap.domain.service.capabilityapplicationservice.CapabilityApplicationService;
import com.bavostepbros.leap.persistence.CapabilityApplicationDAL;
import com.bavostepbros.leap.persistence.CapabilityDAL;
import com.bavostepbros.leap.persistence.ITApplicationDAL;

@AutoConfigureMockMvc
@SpringBootTest
public class CapabilityApplicationServiceTest {

	@Autowired
	private CapabilityApplicationService capabilityApplicationService;

	@MockBean
	private CapabilityApplicationDAL capabilityApplicationDAL;
	
	@MockBean
	private ITApplicationDAL itApplicationDAL;
	
	@MockBean
	private CapabilityDAL capabilityDAL;

	private Status statusFirst;
	private Status statusSecond;
	private Technology technologyFirst;
	private Technology technologySecond;
	private ITApplication itApplicationFirst;
	private ITApplication itApplicationSecond;
	private Environment environmentFirst;
	private Environment environmentSecond;
	private Capability capabilityFirst;
	private Capability capabilitySecond;
	private CapabilityApplication capabilityApplicationFirst;
	private CapabilityApplication capabilityApplicationSecond;
	private List<CapabilityApplication> capabilityApplications;
	private Optional<ITApplication> optionalItApplicationFirst;
	private Optional<Capability> optionalCapabilityFirst;
	private Optional<CapabilityApplication> optionalCapabilityApplicationFirst;

	private Integer capabilityId;
	private Integer applicationId;
	private Integer efficiencySupport;
	private Integer functionalCoverage;
	private Integer correctnessBusinessFit;
	private Integer futurePotential;
	private Integer completeness;
	private Integer correctnessInformationFit;
	private Integer availability;

	@BeforeEach
	void init() {
		statusFirst = new Status(1, LocalDate.of(2021, 05, 15));
		statusSecond = new Status(2, LocalDate.of(2021, 10, 10));
		technologyFirst = new Technology(1, "Java");
		technologySecond = new Technology(1, "c#");
		itApplicationFirst = new ITApplication(1, statusFirst, "application 1", "1.20.1", LocalDate.of(2021, 01, 20),
				LocalDate.of(2025, 05, 20), 1, 2, 3, 4, 5, 4, 3, 2, "EUR", 1000.0, 5, 70.0, 100.0, TimeValue.ELIMINATE);
		itApplicationSecond = new ITApplication(2, statusSecond, "application 2", "1.20.1", LocalDate.of(2021, 01, 20),
				LocalDate.of(2025, 05, 20), 2, 3, 4, 5, 4, 3, 2, 1, "EUR", 1000.0, 4, 70.0, 100.0, TimeValue.INVEST);
		environmentFirst = new Environment(1, "Environment test");
		environmentSecond = new Environment(2, "Environment test");
		capabilityFirst = new Capability(1, environmentFirst, statusFirst, 0, "Capability 1", "Description 1",
				PaceOfChange.DIFFERENTIATION, TargetOperatingModel.COORDINATION, 10, 2.0, 3.0);
		capabilitySecond = new Capability(2, environmentFirst, statusFirst, capabilityFirst.getCapabilityId(),
				"Capability 2", "Description 2", PaceOfChange.DIFFERENTIATION, TargetOperatingModel.COORDINATION, 10, 2.0, 3.0);
		capabilityApplicationFirst = new CapabilityApplication(capabilityFirst, itApplicationFirst, 0.40, 2, 3, 4, 5, 6, 7, 8);
		capabilityApplicationSecond = new CapabilityApplication(capabilitySecond, itApplicationSecond, 0.60, 3, 4, 5, 6, 7, 8, 9);
		capabilityApplications = List.of(capabilityApplicationFirst, capabilityApplicationSecond);
		optionalItApplicationFirst = Optional.of(itApplicationFirst);
		optionalCapabilityFirst = Optional.of(capabilityFirst);
		optionalCapabilityApplicationFirst = Optional.of(capabilityApplicationFirst);
		
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

	@Test
	void shouldNotBeNull() {
		assertNotNull(capabilityApplicationService);
		assertNotNull(capabilityApplicationDAL);
		assertNotNull(statusFirst);
		assertNotNull(statusSecond);
		assertNotNull(technologyFirst);
		assertNotNull(technologySecond);
		assertNotNull(itApplicationFirst);
		assertNotNull(itApplicationSecond);
		assertNotNull(environmentFirst);
		assertNotNull(environmentSecond);
		assertNotNull(capabilityFirst);
		assertNotNull(capabilitySecond);
		assertNotNull(capabilityApplicationFirst);
		assertNotNull(capabilityApplicationSecond);
		assertNotNull(capabilityApplications);
		assertNotNull(optionalItApplicationFirst);
		assertNotNull(optionalCapabilityFirst);
		assertNotNull(optionalCapabilityApplicationFirst);
	}

	@Test
	void should_returnCapabilityApplication_whenSaveCapabilityApplication() {
		BDDMockito.given(capabilityDAL.findById(BDDMockito.anyInt())).willReturn(optionalCapabilityFirst);
		BDDMockito.given(itApplicationDAL.findById(BDDMockito.anyInt())).willReturn(optionalItApplicationFirst);
		BDDMockito.given(capabilityApplicationDAL.save(BDDMockito.any(CapabilityApplication.class)))
				.willReturn(capabilityApplicationFirst);

		CapabilityApplication capabilityApplication = capabilityApplicationService.save(capabilityId, applicationId,
				efficiencySupport, functionalCoverage, correctnessBusinessFit, futurePotential, completeness,
				correctnessInformationFit, availability);
		
		assertNotNull(capabilityApplication);
		assertTrue(capabilityApplication instanceof CapabilityApplication);
		testCapabilityApplication(capabilityApplicationFirst, capabilityApplication);
	}
	
	@Test 
	void should_throwNullPointerException_whenGetCapabilityApplicationByIdInvalidId() {
		BDDMockito.given(capabilityDAL.findById(BDDMockito.anyInt())).willReturn(optionalCapabilityFirst);
		BDDMockito.given(itApplicationDAL.findById(BDDMockito.anyInt())).willReturn(optionalItApplicationFirst);
		String expectedErrorMessage = "CapabilityApplication does not exist.";
		
		Exception exception = assertThrows(NullPointerException.class, 
				() -> capabilityApplicationService.get(capabilityId, applicationId));
		
		assertEquals(expectedErrorMessage, exception.getMessage());
	}
	
	@Test
	void should_returnCapabilityApplication_whenGetCapabilityApplicationById() {
		BDDMockito.given(capabilityDAL.findById(BDDMockito.anyInt())).willReturn(optionalCapabilityFirst);
		BDDMockito.given(itApplicationDAL.findById(BDDMockito.anyInt())).willReturn(optionalItApplicationFirst);
		BDDMockito.given(capabilityApplicationDAL.findByCapabilityAndApplication(BDDMockito.any(Capability.class), BDDMockito.any(ITApplication.class)))
				.willReturn(optionalCapabilityApplicationFirst);

		CapabilityApplication capabilityApplication = capabilityApplicationService.get(capabilityId, applicationId);
		
		assertNotNull(capabilityApplication);
		assertTrue(capabilityApplication instanceof CapabilityApplication);
		testCapabilityApplication(capabilityApplicationFirst, capabilityApplication);
	}
	
	@Test
	void should_returnCapabilityApplication_whenUpdateCapabilityApplication() {
		BDDMockito.given(capabilityDAL.findById(BDDMockito.anyInt())).willReturn(optionalCapabilityFirst);
		BDDMockito.given(itApplicationDAL.findById(BDDMockito.anyInt())).willReturn(optionalItApplicationFirst);
		BDDMockito.given(capabilityApplicationDAL.save(BDDMockito.any(CapabilityApplication.class)))
				.willReturn(capabilityApplicationFirst);

		CapabilityApplication capabilityApplication = capabilityApplicationService.update(capabilityId, applicationId,
				efficiencySupport, functionalCoverage, correctnessBusinessFit, futurePotential, completeness,
				correctnessInformationFit, availability);
		
		assertNotNull(capabilityApplication);
		assertTrue(capabilityApplication instanceof CapabilityApplication);
		testCapabilityApplication(capabilityApplicationFirst, capabilityApplication);
	}
	
	@Test 
	void should_verifyDeleted_whenDeleteCapabilityApplication() {
		BDDMockito.given(capabilityDAL.findById(BDDMockito.anyInt())).willReturn(optionalCapabilityFirst);
		BDDMockito.given(itApplicationDAL.findById(BDDMockito.anyInt())).willReturn(optionalItApplicationFirst);
		
		capabilityApplicationService.delete(capabilityId, applicationId);
		
		Mockito.verify(capabilityApplicationDAL, Mockito.times(1)).deleteByCapabilityAndApplication(
				BDDMockito.any(Capability.class), BDDMockito.any(ITApplication.class));
	}
	
	@Test
	void should_returnCapabilityApplications_whenGetCapabilityApplicationsByCapability() {
		BDDMockito.given(capabilityDAL.findById(BDDMockito.anyInt())).willReturn(optionalCapabilityFirst);
		BDDMockito.given(capabilityApplicationDAL.findByCapability(BDDMockito.any(Capability.class)))
				.willReturn(capabilityApplications);

		List<CapabilityApplication> capabilityApplicationsResult = capabilityApplicationService.getCapabilityApplicationsByCapability(capabilityId);
		
		assertNotNull(capabilityApplicationsResult);
		assertEquals(capabilityApplications.size(), capabilityApplicationsResult.size());
		testCapabilityApplication(capabilityApplicationFirst, capabilityApplicationsResult.get(0));
		testCapabilityApplication(capabilityApplicationSecond, capabilityApplicationsResult.get(1));
	}
	
	@Test
	void should_returnCapabilityApplications_whenGetCapabilityApplicationsByApplication() {
		BDDMockito.given(itApplicationDAL.findById(BDDMockito.anyInt())).willReturn(optionalItApplicationFirst);
		BDDMockito.given(capabilityApplicationDAL.findByApplication(BDDMockito.any(ITApplication.class)))
				.willReturn(capabilityApplications);

		List<CapabilityApplication> capabilityApplicationsResult = capabilityApplicationService.getCapabilityApplicationsByApplication(applicationId);
		
		assertNotNull(capabilityApplicationsResult);
		assertEquals(capabilityApplications.size(), capabilityApplicationsResult.size());
		testCapabilityApplication(capabilityApplicationFirst, capabilityApplicationsResult.get(0));
		testCapabilityApplication(capabilityApplicationSecond, capabilityApplicationsResult.get(1));
	}
	
	private void testCapabilityApplication(CapabilityApplication expectedObject, CapabilityApplication actualObject) {
		assertEquals(expectedObject.getCapability().getCapabilityId(), actualObject.getCapability().getCapabilityId());
		assertEquals(expectedObject.getApplication().getItApplicationId(), actualObject.getApplication().getItApplicationId());
		assertEquals(expectedObject.getEfficiencySupport(), actualObject.getEfficiencySupport());
		assertEquals(expectedObject.getFunctionalCoverage(), actualObject.getFunctionalCoverage());
		assertEquals(expectedObject.getCorrectnessBusinessFit(), actualObject.getCorrectnessBusinessFit());
		assertEquals(expectedObject.getFuturePotential(), actualObject.getFuturePotential());
		assertEquals(expectedObject.getCompleteness(), actualObject.getCompleteness());
		assertEquals(expectedObject.getCorrectnessInformationFit(), actualObject.getCorrectnessInformationFit());
		assertEquals(expectedObject.getAvailability(), actualObject.getAvailability());
	}
}
