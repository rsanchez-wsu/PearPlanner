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

/**This is the IpAddress class.
 * @author zooko
 *
 */
public class IpAddress {
	private String outcome;
	private AddressDetails addressDetails;
	/**This is default constructor.
	 * @author zooko
	 */

	public IpAddress(){
	}

	/**This is constructor with fields.
	 *@param outcome is outcome
	 * @param addressDetails is nested entity
	 */
	@JsonCreator
	public IpAddress(@JsonProperty("status") String outcome,
			@JsonProperty("data") AddressDetails addressDetails) {
		this.outcome = outcome;
		this.addressDetails = addressDetails;
	}

	/**This is a getter.
	 * @return the outcome
	 */

	public String getOutcome() {
		return outcome;
	}
	/**This is the outcome getter.
	 * @param outcome the outcome to set
	 */

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	/**This is a getter.
	 * @return addressDetails is adddress details
	 */
	public AddressDetails getAddressDetails() {
		return addressDetails;
	}

	/**This is setter.
	 * @param addressDetails address details
	 */
	public void setAddressDetails(AddressDetails addressDetails) {
		this.addressDetails = addressDetails;
	}
}
