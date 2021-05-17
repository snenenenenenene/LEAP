package com.bavostepbros.leap.domain.service.itapplicationservice;

import com.bavostepbros.leap.domain.model.ITApplication;

import java.time.LocalDate;
import java.util.List;

public interface ITApplicationService {
    ITApplication save(Integer statusID, String name, String technology, String version, LocalDate purchaseDate,
                       LocalDate endOfLife, Integer currentScalability, Integer expectedScalability,
                       Integer currentPerformance, Integer expectedPerformance, Integer currentSecurityLevel,
                       Integer expectedSecurityLevel, Integer currentStability, Integer expectedStability,
                       String costCurrency, String currentValue, Double currentYearlyCost, LocalDate timeValue);

    ITApplication save(ITApplication itApplication);

    ITApplication get(long itApplicationID);

    List<ITApplication> get(String name);

    List<ITApplication> getAll();

    ITApplication update(long id, Integer statusID, String name, String technology, String version, LocalDate purchaseDate,
                         LocalDate endOfLife, Integer currentScalability, Integer expectedScalability,
                         Integer currentPerformance, Integer expectedPerformance, Integer currentSecurityLevel,
                         Integer expectedSecurityLevel, Integer currentStability, Integer expectedStability,
                         String costCurrency, String currentValue, Double currentYearlyCost, LocalDate timeValue);

    void delete(long itApplicationID);

    boolean existsById(long itApplicationID);

    boolean existsByName(String name);
}