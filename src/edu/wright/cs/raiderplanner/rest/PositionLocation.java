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

/** Job location.
 * @author zooko
 *
 */
public class PositionLocation {
	private String locationName;
	private String countryCode;
	private String countrySubDivisionCode;
	private String cityName;
	private float longitude;
	private float latitude;

	/**Default constructor.
	 */
	public PositionLocation() {

	}

	/**Field constructor.
	 * @param locationName location name
	 * @param countryCode country code
	 * @param countrySubDivisionCode country subdivision code
	 * @param cityName city name
	 * @param longitude long
	 * @param latitude lat
	 */
	@JsonCreator
	public PositionLocation(
			@JsonProperty ("LocationName") String locationName,
			@JsonProperty("CountryCode") String countryCode,
			@JsonProperty("CountrySubDivisionCode") String countrySubDivisionCode,
			@JsonProperty("CityName") String cityName,
			@JsonProperty("Longitude") float longitude,
			@JsonProperty("Latitude") float latitude) {
		this.locationName = locationName;
		this.countryCode = countryCode;
		this.countrySubDivisionCode = countrySubDivisionCode;
		this.cityName = cityName;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	/** Getter/Setter.
	 * @return the locationName
	 */
	public String getLocationName() {
		return locationName;
	}

	/** Getter/Setter.
	 * @param locationName the locationName to set
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	/** Getter/Setter.
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/** Getter/Setter.
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/** Getter/Setter.
	 * @return the countrySubDivisionCode
	 */
	public String getCountrySubDivisionCode() {
		return countrySubDivisionCode;
	}

	/** Getter/Setter.
	 * @param countrySubDivisionCode the countrySubDivisionCode to set
	 */
	public void setCountrySubDivisionCode(String countrySubDivisionCode) {
		this.countrySubDivisionCode = countrySubDivisionCode;
	}

	/** Getter/Setter.
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/** Getter/Setter.
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/** Getter/Setter.
	 * @return the longitude
	 */
	public float getLongitude() {
		return longitude;
	}

	/** Getter/Setter.
	 * @param longitude the longitude to set
	 */
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	/** Getter/Setter.
	 * @return the latitude
	 */
	public float getLatitude() {
		return latitude;
	}

	/** Getter/Setter.
	 * @param latitude the latitude to set
	 */
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

}
