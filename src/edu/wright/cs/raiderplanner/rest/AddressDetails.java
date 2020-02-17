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

import com.fasterxml.jackson.annotation.JsonProperty;

/**This is a test entity.
 * @author zooko
 *
 */
public class AddressDetails {
	private String ipv4;
	private String continentName;
	private String countryName;
	private String subdivision1Name;
	private String subdivision2Name;
	private String cityName;
	private String latitude;
	private String longitude;

	/**This is default constructor.
	 */
	public AddressDetails(){
	}

	/**This is the constructor from fields.
	 * @param ipv4 is ip address
	 * @param continentName is continent name
	 * @param countryName is country name
	 * @param subdivision1Name is subdivision 1 name
	 * @param subdivision2Name is subdivision 2 name
	 * @param cityName is city name
	 * @param latitude is latitude
	 * @param longitude is longitude
	 */
	public AddressDetails(
			@JsonProperty ("ipv4") String ipv4,
			@JsonProperty ("continent_name") String continentName,
			@JsonProperty ("country_name") String countryName,
			@JsonProperty ("subdivision_1_name") String subdivision1Name,
			@JsonProperty ("subdivision_2_name") String subdivision2Name,
			@JsonProperty ("city_name") String cityName,
			@JsonProperty ("latitude") String latitude,
			@JsonProperty("longitude") String longitude) {
		super();
		this.ipv4 = ipv4;
		this.continentName = continentName;
		this.countryName = countryName;
		this.subdivision1Name = subdivision1Name;
		this.subdivision2Name = subdivision2Name;
		this.cityName = cityName;
		this.latitude = latitude;
		this.longitude = longitude;
	}


	/**Getter/setter.
	 * @return the ipv4
	 */
	public String getIpv4() {
		return ipv4;
	}

	/**Getter/setter.
	 * @param ipv4 the ipv4 to set
	 */
	public void setIpv4(String ipv4) {
		this.ipv4 = ipv4;
	}

	/**Getter/setter.
	 * @return the continent_name
	 */
	public String getContinent_name() {
		return continentName;
	}

	/**Getter/setter.
	 * @param continentName the continent_name to set
	 */
	public void setContinent_name(String continentName) {
		this.continentName = continentName;
	}

	/**Getter/setter.
	 * @return the country_name
	 */
	public String getCountry_name() {
		return countryName;
	}

	/**Getter/setter.
	 * @param countryName the country_name to set
	 */
	public void setCountry_name(String countryName) {
		this.countryName = countryName;
	}

	/**Getter/setter.
	 * @return the subdivision1Name
	 */
	public String getSubdivision1Name() {
		return subdivision1Name;
	}

	/**Getter/setter.
	 * @param subdivision1Name the subdivision_1_name to set
	 */
	public void setSubdivision1Name(String subdivision1Name) {
		this.subdivision1Name = subdivision1Name;
	}

	/**Getter/setter.
	 * @return the subdivision2Name
	 */
	public String getSubdivision2Name() {
		return subdivision2Name;
	}

	/**Getter/setter.
	 * @param subdivision2Name the subdivision_2_name to set
	 */
	public void setSubdivision2Name(String subdivision2Name) {
		this.subdivision2Name = subdivision2Name;
	}

	/**Getter/setter.
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**Getter/setter.
	 * @param cityName the city_name to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**Getter/setter.
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**Getter/setter.
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**Getter/setter.
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**Getter/setter.
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

}
