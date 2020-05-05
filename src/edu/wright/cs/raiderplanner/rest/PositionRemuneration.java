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

/**Represents position remuneration.
 * @author zooko
 *
 */
public class PositionRemuneration {

	private String minimumRange;
	private String maximumRange;
	private String rateIntervalCode;

	/**Default constructor.
	 */
	public PositionRemuneration() {

	}

	/**Field constructor.
	 * @param minimumRange minimum
	 * @param maximumRange maximum
	 * @param rateIntervalCode interval
	 */
	@JsonCreator
	public PositionRemuneration(
			@JsonProperty("MinimumRange") String minimumRange,
			@JsonProperty("MaximumRange") String maximumRange,
			@JsonProperty("RateIntervalCode") String rateIntervalCode) {
		this.minimumRange = minimumRange;
		this.maximumRange = maximumRange;
		this.rateIntervalCode = rateIntervalCode;
	}

	/** Getter/Setter.
	 * @return the minimumRange
	 */
	public String getMinimumRange() {
		return minimumRange;
	}

	/** Getter/Setter.
	 * @param minimumRange the minimumRange to set
	 */
	public void setMinimumRange(String minimumRange) {
		this.minimumRange = minimumRange;
	}

	/** Getter/Setter.
	 * @return the maximumRange
	 */
	public String getMaximumRange() {
		return maximumRange;
	}

	/** Getter/Setter.
	 * @param maximumRange the maximumRange to set
	 */
	public void setMaximumRange(String maximumRange) {
		this.maximumRange = maximumRange;
	}

	/** Getter/Setter.
	 * @return the rateIntervalCode
	 */
	public String getRateIntervalCode() {
		return rateIntervalCode;
	}

	/** Getter/Setter.
	 * @param rateIntervalCode the rateIntervalCode to set
	 */
	public void setRateIntervalCode(String rateIntervalCode) {
		this.rateIntervalCode = rateIntervalCode;
	}


}
