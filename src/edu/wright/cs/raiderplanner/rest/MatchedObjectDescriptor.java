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

/**Entity to describe the individual job match.
 * @author zooko
 *
 */
public class MatchedObjectDescriptor {
	private String positionId;
	private String positionTitle;
	private String positionUri;
	private String[] applyUri;
	private PositionLocation[] positionLoaction;
	private String organizationName;
	private String departmentName;
	private JobCategory[] jobCategory;
	private JobGrade[] jobGrade;
	private PositionSchedule[] positionSchedule;
	private PositionOfferingType[] positionOfferingType;
	private String qualificationSummary;
	private PositionRemuneration positionRemuneration;
	private String positionStartDate;
	private String positionEndDate;
	private String publicationStartDate;
	private String applicationCloseDate;
	private PositionFormattedDescription positionFormateedDescription;

	/**Default constructor.
	 *
	 **/
	public MatchedObjectDescriptor() {

	}

	/**Field constructor.
	 * @param positionId id
	 * @param positionTitle title
	 * @param positionUri uri
	 * @param applyUri applyuri
	 * @param positionLoaction location
	 * @param organizationName company
	 * @param departmentName department
	 * @param jobCategory category
	 * @param jobGrade grade
	 * @param positionSchedule schedule
	 * @param positionOfferingType type
	 * @param qualificationSummary job summary
	 * @param positionRemuneration job pay
	 * @param positionStartDate start date
	 * @param positionEndDate end date
	 * @param publicationStartDate publish date
	 * @param applicationCloseDate close date
	 * @param positionFormateedDescription formatted description
	 */
	@JsonCreator
	public MatchedObjectDescriptor(
			@JsonProperty("PositionID") String positionId,
			@JsonProperty("PositionTitle") String positionTitle,
			@JsonProperty("PositionURI")String positionUri,
			@JsonProperty("ApplyURI")String[] applyUri,
			@JsonProperty("PositionLocation")PositionLocation[] positionLoaction,
			@JsonProperty("OrganizationName")String organizationName,
			@JsonProperty("DepartmentName")String departmentName,
			@JsonProperty("JobCategory")JobCategory[] jobCategory,
			@JsonProperty("JobGrade")JobGrade[] jobGrade,
			@JsonProperty("PosotionSchedule")PositionSchedule[] positionSchedule,
			@JsonProperty("PositionOfferingType")PositionOfferingType[] positionOfferingType,
			@JsonProperty("QualificationSummary")String qualificationSummary,
			@JsonProperty("PositionRemuneration")PositionRemuneration positionRemuneration,
			@JsonProperty("PositionStartDate")String positionStartDate,
			@JsonProperty("PositionEndDate")String positionEndDate,
			@JsonProperty("PublicationStartDate")String publicationStartDate,
			@JsonProperty("ApplicationCloseDate")String applicationCloseDate,
			@JsonProperty("PositionFormattedDescription")
			PositionFormattedDescription positionFormateedDescription) {
		this.positionId = positionId;
		this.positionTitle = positionTitle;
		this.positionUri = positionUri;
		this.applyUri = applyUri;
		this.positionLoaction = positionLoaction;
		this.organizationName = organizationName;
		this.departmentName = departmentName;
		this.jobCategory = jobCategory;
		this.jobGrade = jobGrade;
		this.positionSchedule = positionSchedule;
		this.positionOfferingType = positionOfferingType;
		this.qualificationSummary = qualificationSummary;
		this.positionRemuneration = positionRemuneration;
		this.positionStartDate = positionStartDate;
		this.positionEndDate = positionEndDate;
		this.publicationStartDate = publicationStartDate;
		this.applicationCloseDate = applicationCloseDate;
		this.positionFormateedDescription = positionFormateedDescription;
	}

	/** Getter/Setter.
	 * @return the positionId
	 */
	public String getPositionId() {
		return positionId;
	}

	/** Getter/Setter.
	 * @param positionId the positionId to set
	 */
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	/** Getter/Setter.
	 * @return the positionTitle
	 */
	public String getPositionTitle() {
		return positionTitle;
	}

	/** Getter/Setter.
	 * @param positionTitle the positionTitle to set
	 */
	public void setPositionTitle(String positionTitle) {
		this.positionTitle = positionTitle;
	}

	/** Getter/Setter.
	 * @return the positionUri
	 */
	public String getPositionUri() {
		return positionUri;
	}

	/** Getter/Setter.
	 * @param positionUri the positionUri to set
	 */
	public void setPositionUri(String positionUri) {
		this.positionUri = positionUri;
	}

	/** Getter/Setter.
	 * @return the applyUri
	 */
	public String[] getApplyUri() {
		return applyUri;
	}

	/** Getter/Setter.
	 * @param applyUri the applyUri to set
	 */
	public void setApplyUri(String[] applyUri) {
		this.applyUri = applyUri;
	}

	/** Getter/Setter.
	 * @return the positionLoaction
	 */
	public PositionLocation[] getPositionLoaction() {
		return positionLoaction;
	}

	/** Getter/Setter.
	 * @param positionLoaction the positionLoaction to set
	 */
	public void setPositionLoaction(PositionLocation[] positionLoaction) {
		this.positionLoaction = positionLoaction;
	}

	/** Getter/Setter.
	 * @return the organizationName
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/** Getter/Setter.
	 * @param organizationName the organizationName to set
	 */
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	/** Getter/Setter.
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/** Getter/Setter.
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/** Getter/Setter.
	 * @return the jobCategory
	 */
	public JobCategory[] getJobCategory() {
		return jobCategory;
	}

	/** Getter/Setter.
	 * @param jobCategory the jobCategory to set
	 */
	public void setJobCategory(JobCategory[] jobCategory) {
		this.jobCategory = jobCategory;
	}

	/** Getter/Setter.
	 * @return the jobGrade
	 */
	public JobGrade[] getJobGrade() {
		return jobGrade;
	}

	/** Getter/Setter.
	 * @param jobGrade the jobGrade to set
	 */
	public void setJobGrade(JobGrade[] jobGrade) {
		this.jobGrade = jobGrade;
	}

	/** Getter/Setter.
	 * @return the positionSchedule
	 */
	public PositionSchedule[] getPositionSchedule() {
		return positionSchedule;
	}

	/** Getter/Setter.
	 * @param positionSchedule the positionSchedule to set
	 */
	public void setPositionSchedule(PositionSchedule[] positionSchedule) {
		this.positionSchedule = positionSchedule;
	}

	/** Getter/Setter.
	 * @return the positionOfferingType
	 */
	public PositionOfferingType[] getPositionOfferingType() {
		return positionOfferingType;
	}

	/** Getter/Setter.
	 * @param positionOfferingType the positionOfferingType to set
	 */
	public void setPositionOfferingType(PositionOfferingType[] positionOfferingType) {
		this.positionOfferingType = positionOfferingType;
	}

	/** Getter/Setter.
	 * @return the qualificationSummary
	 */
	public String getQualificationSummary() {
		return qualificationSummary;
	}

	/** Getter/Setter.
	 * @param qualificationSummary the qualificationSummary to set
	 */
	public void setQualificationSummary(String qualificationSummary) {
		this.qualificationSummary = qualificationSummary;
	}

	/** Getter/Setter.
	 * @return the positionRemuneration
	 */
	public PositionRemuneration getPositionRemuneration() {
		return positionRemuneration;
	}

	/** Getter/Setter.
	 * @param positionRemuneration the positionRemuneration to set
	 */
	public void setPositionRemuneration(PositionRemuneration positionRemuneration) {
		this.positionRemuneration = positionRemuneration;
	}

	/** Getter/Setter.
	 * @return the positionStartDate
	 */
	public String getPositionStartDate() {
		return positionStartDate;
	}

	/** Getter/Setter.
	 * @param positionStartDate the positionStartDate to set
	 */
	public void setPositionStartDate(String positionStartDate) {
		this.positionStartDate = positionStartDate;
	}

	/** Getter/Setter.
	 * @return the positionEndDate
	 */
	public String getPositionEndDate() {
		return positionEndDate;
	}

	/** Getter/Setter.
	 * @param positionEndDate the positionEndDate to set
	 */
	public void setPositionEndDate(String positionEndDate) {
		this.positionEndDate = positionEndDate;
	}

	/** Getter/Setter.
	 * @return the publicationStartDate
	 */
	public String getPublicationStartDate() {
		return publicationStartDate;
	}

	/** Getter/Setter.
	 * @param publicationStartDate the publicationStartDate to set
	 */
	public void setPublicationStartDate(String publicationStartDate) {
		this.publicationStartDate = publicationStartDate;
	}

	/** Getter/Setter.
	 * @return the applicationCloseDate
	 */
	public String getApplicationCloseDate() {
		return applicationCloseDate;
	}

	/** Getter/Setter.
	 * @param applicationCloseDate the applicationCloseDate to set
	 */
	public void setApplicationCloseDate(String applicationCloseDate) {
		this.applicationCloseDate = applicationCloseDate;
	}

	/** Getter/Setter.
	 * @return the positionFormateedDescription
	 */
	public PositionFormattedDescription getPositionFormateedDescription() {
		return positionFormateedDescription;
	}

	/** Getter/Setter.
	 * @param positionFormateedDescription the positionFormateedDescription to set
	 */
	public void setPositionFormateedDescription(
			PositionFormattedDescription positionFormateedDescription) {
		this.positionFormateedDescription = positionFormateedDescription;
	}
}
