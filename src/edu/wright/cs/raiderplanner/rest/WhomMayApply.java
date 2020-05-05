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

/**Represents WhomMayApply entity.
 * @author zooko
 *
 */
public class WhomMayApply {

	private String name;
	private String code;

	/**Default constructor.
	 */
	public WhomMayApply() {

	}

	/**Field constructor.
	 * @param name name
	 * @param code code
	 */
	@JsonCreator
	public WhomMayApply(
			@JsonProperty("Name") String name,
			@JsonProperty("Code") String code) {
		this.name = name;
		this.code = code;
	}

	/** Getter/Setter.
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/** Getter/Setter.
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** Getter/Setter.
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/** Getter/Setter.
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

}
