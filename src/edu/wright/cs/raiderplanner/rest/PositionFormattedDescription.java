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

/**Represents position formatted description.
 * @author zooko
 *
 */
public class PositionFormattedDescription {

	private String content;
	private String label;
	private String labelDescription;

	/**Default constructor.
	 */
	public PositionFormattedDescription() {

	}

	/**Field constructor.
	 * @param content job description
	 * @param label job label
	 * @param labelDescription label description
	 */
	@JsonCreator
	public PositionFormattedDescription(
			@JsonProperty("Content")String content,
			@JsonProperty("Label") String label,
			@JsonProperty("LabelDescription") String labelDescription) {
		this.content = content;
		this.label = label;
		this.labelDescription = labelDescription;
	}

	/** Getter/Setter.
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/** Getter/Setter.
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/** Getter/Setter.
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/** Getter/Setter.
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/** Getter/Setter.
	 * @return the labelDescription
	 */
	public String getLabelDescription() {
		return labelDescription;
	}

	/** Getter/Setter.
	 * @param labelDescription the labelDescription to set
	 */
	public void setLabelDescription(String labelDescription) {
		this.labelDescription = labelDescription;
	}
}
