/*
 * Copyright (C) 2019
 *
 *
 *
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package edu.wright.cs.raiderplanner.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**Represents details for user's search result.
 * @author zooko
 *
 */
public class Details {

	private String majorDuties;
	private String education;
	private String requirements;
	private String evaluations;
	private String howToApply;
	private String whatToExpectNext;
	private String requiredDocuments;
	private String benefits;
	private String benefitsUrl;
	private String otherInformation;
	private String keyRequirements;
	private String jobSummary;
	private WhomMayApply whomMayApply;
	private String lowGrade;
	private String highGrade;
	private String subAgencyName;
	private String organizationCodes;

	/**Default constructor.
	 */
	public Details() {

	}

	/**Field Constructor.
	 * @param majorDuties duties
	 * @param education education
	 * @param requirements requirements
	 * @param evaluations evaluatoins
	 * @param howToApply how to apply
	 * @param whatToExpectNext what to expect next
	 * @param requiredDocuments required documents
	 * @param benefits benefits
	 * @param benefitsUrl benefits url
	 * @param otherInformation other information
	 * @param keyRequirements key requirements
	 * @param jobSummary job summary
	 * @param whomMayApply whom may apply
	 * @param lowGrade low grade
	 * @param highGrade high grade
	 * @param subAgencyName subagency name
	 * @param organizationCodes organization code
	 */
	@JsonCreator
	public Details(
			@JsonProperty("MajorDuties") String majorDuties,
			@JsonProperty("Education") String education,
			@JsonProperty("Requirements") String requirements,
			@JsonProperty("Evaluations") String evaluations,
			@JsonProperty("HowToApply") String howToApply,
			@JsonProperty("WhatToExpecttNext") String whatToExpectNext,
			@JsonProperty("RequiredDocuments") String requiredDocuments,
			@JsonProperty("Benefits") String benefits,
			@JsonProperty("BenefitsUrl") String benefitsUrl,
			@JsonProperty("OtherInformation") String otherInformation,
			@JsonProperty("KeyRequirements") String keyRequirements,
			@JsonProperty("JobSummary") String jobSummary,
			@JsonProperty("WhomMayApply") WhomMayApply whomMayApply,
			@JsonProperty("LowGrade") String lowGrade,
			@JsonProperty("HighGrade") String highGrade,
			@JsonProperty("SubAgencyName") String subAgencyName,
			@JsonProperty("OrganizationCodes") String organizationCodes) {
		this.majorDuties = majorDuties;
		this.education = education;
		this.requirements = requirements;
		this.evaluations = evaluations;
		this.howToApply = howToApply;
		this.whatToExpectNext = whatToExpectNext;
		this.requiredDocuments = requiredDocuments;
		this.benefits = benefits;
		this.benefitsUrl = benefitsUrl;
		this.otherInformation = otherInformation;
		this.keyRequirements = keyRequirements;
		this.jobSummary = jobSummary;
		this.whomMayApply = whomMayApply;
		this.lowGrade = lowGrade;
		this.highGrade = highGrade;
		this.subAgencyName = subAgencyName;
		this.organizationCodes = organizationCodes;
	}

	/** Getter/Setter.
	 * @return the majorDuties
	 */
	public String getMajorDuties() {
		return majorDuties;
	}

	/** Getter/Setter.
	 * @param majorDuties the majorDuties to set
	 */
	public void setMajorDuties(String majorDuties) {
		this.majorDuties = majorDuties;
	}

	/** Getter/Setter.
	 * @return the education
	 */
	public String getEducation() {
		return education;
	}

	/** Getter/Setter.
	 * @param education the education to set
	 */
	public void setEducation(String education) {
		this.education = education;
	}

	/** Getter/Setter.
	 * @return the requirements
	 */
	public String getRequirements() {
		return requirements;
	}

	/** Getter/Setter.
	 * @param requirements the requirements to set
	 */
	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	/** Getter/Setter.
	 * @return the evaluations
	 */
	public String getEvaluations() {
		return evaluations;
	}

	/** Getter/Setter.
	 * @param evaluations the evaluations to set
	 */
	public void setEvaluations(String evaluations) {
		this.evaluations = evaluations;
	}

	/** Getter/Setter.
	 * @return the howToApply
	 */
	public String getHowToApply() {
		return howToApply;
	}

	/** Getter/Setter.
	 * @param howToApply the howToApply to set
	 */
	public void setHowToApply(String howToApply) {
		this.howToApply = howToApply;
	}

	/** Getter/Setter.
	 * @return the whatToExpectNext
	 */
	public String getWhatToExpectNext() {
		return whatToExpectNext;
	}

	/** Getter/Setter.
	 * @param whatToExpectNext the whatToExpectNext to set
	 */
	public void setWhatToExpectNext(String whatToExpectNext) {
		this.whatToExpectNext = whatToExpectNext;
	}

	/** Getter/Setter.
	 * @return the requiredDocuments
	 */
	public String getRequiredDocuments() {
		return requiredDocuments;
	}

	/** Getter/Setter.
	 * @param requiredDocuments the requiredDocuments to set
	 */
	public void setRequiredDocuments(String requiredDocuments) {
		this.requiredDocuments = requiredDocuments;
	}

	/** Getter/Setter.
	 * @return the benefits
	 */
	public String getBenefits() {
		return benefits;
	}

	/** Getter/Setter.
	 * @param benefits the benefits to set
	 */
	public void setBenefits(String benefits) {
		this.benefits = benefits;
	}

	/** Getter/Setter.
	 * @return the benefitsUrl
	 */
	public String getBenefitsUrl() {
		return benefitsUrl;
	}

	/** Getter/Setter.
	 * @param benefitsUrl the benefitsUrl to set
	 */
	public void setBenefitsUrl(String benefitsUrl) {
		this.benefitsUrl = benefitsUrl;
	}

	/** Getter/Setter.
	 * @return the otherInformation
	 */
	public String getOtherInformation() {
		return otherInformation;
	}

	/** Getter/Setter.
	 * @param otherInformation the otherInformation to set
	 */
	public void setOtherInformation(String otherInformation) {
		this.otherInformation = otherInformation;
	}

	/** Getter/Setter.
	 * @return the keyRequirements
	 */
	public String getKeyRequirements() {
		return keyRequirements;
	}

	/** Getter/Setter.
	 * @param keyRequirements the keyRequirements to set
	 */
	public void setKeyRequirements(String keyRequirements) {
		this.keyRequirements = keyRequirements;
	}

	/** Getter/Setter.
	 * @return the jobSummary
	 */
	public String getJobSummary() {
		return jobSummary;
	}

	/** Getter/Setter.
	 * @param jobSummary the jobSummary to set
	 */
	public void setJobSummary(String jobSummary) {
		this.jobSummary = jobSummary;
	}

	/** Getter/Setter.
	 * @return the whomMayApply
	 */
	public WhomMayApply getWhomMayApply() {
		return whomMayApply;
	}

	/** Getter/Setter.
	 * @param whomMayApply the whomMayApply to set
	 */
	public void setWhomMayApply(WhomMayApply whomMayApply) {
		this.whomMayApply = whomMayApply;
	}

	/** Getter/Setter.
	 * @return the lowGrade
	 */
	public String getLowGrade() {
		return lowGrade;
	}

	/** Getter/Setter.
	 * @param lowGrade the lowGrade to set
	 */
	public void setLowGrade(String lowGrade) {
		this.lowGrade = lowGrade;
	}

	/** Getter/Setter.
	 * @return the highGrade
	 */
	public String getHighGrade() {
		return highGrade;
	}

	/** Getter/Setter.
	 * @param highGrade the highGrade to set
	 */
	public void setHighGrade(String highGrade) {
		this.highGrade = highGrade;
	}

	/** Getter/Setter.
	 * @return the subAgencyName
	 */
	public String getSubAgencyName() {
		return subAgencyName;
	}

	/** Getter/Setter.
	 * @param subAgencyName the subAgencyName to set
	 */
	public void setSubAgencyName(String subAgencyName) {
		this.subAgencyName = subAgencyName;
	}

	/** Getter/Setter.
	 * @return the organizationCodes
	 */
	public String getOrganizationCodes() {
		return organizationCodes;
	}

	/** Getter/Setter.
	 * @param organizationCodes the organizationCodes to set
	 */
	public void setOrganizationCodes(String organizationCodes) {
		this.organizationCodes = organizationCodes;
	}


}
