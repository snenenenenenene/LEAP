package com.bavostepbros.leap.domain.model.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/** 
 * @return Integer
 */

/** 
 * @return LocalDate
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusDto {
	private Integer statusId;
	private LocalDate validityPeriod;
}
