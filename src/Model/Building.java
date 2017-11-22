/*
 * Copyright (C) 2017 - Benjamin Dickson, Andrew Odintsov, Zilvinas Ceikauskas,
 * Bijan Ghasemi Afshar
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

package Model;

/**
 * PearPlanner/RaiderPlanner
 * Created by Team BRONZE on 4/27/17.
 */
public class Building extends VersionControlEntity {
	/**
	 *serial version UID.
	 */
	private static final long serialVersionUID = -754576463045731146L;
	// private Data
	private String code = null;
	private double latitude;
	private double longitude;

	@Override
	protected void replace(VersionControlEntity receivedVce) {
		if (receivedVce instanceof Building) {
			Building castedVce = (Building) receivedVce;
			if (castedVce.getCode() != null) {
				this.code = castedVce.getCode();
			}
			this.latitude = castedVce.getLatitude();
			this.longitude = castedVce.getLongitude();
		}

		super.replace(receivedVce);
	}

	// getters
	/**get name of the building.
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**get code of the building.
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**get latitude of the building.
	 * @return latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**get longtitude of the building.
	 * @return longtitude
	 */
	public double getLongitude() {
		return longitude;
	}

	// setters
	/**set name.
	 * @param newName String
	 */
	public void setName(String newName) {
		name = newName;
	}

	/**set code.
	 * @param newCode String
	 */
	public void setCode(String newCode) {
		code = newCode;
	}

	/**set Latitude.
	 * @param newLatitude double
	 */
	public void setLatitude(double newLatitude) {
		latitude = newLatitude;
	}

	/**set longtitude.
	 * @param newLongitude double
	 */
	public void setLongitude(double newLongitude) {
		longitude = newLongitude;
	}

	// constructor
	/**constructor.
	 * @param cCode	String
	 * @param cLatitude	double
	 * @param cLongitude	double
	 */
	public Building(String cCode, double cLatitude, double cLongitude) {
		code = cCode;
		latitude = cLatitude;
		longitude = cLongitude;
	}

	@Override
	public String toString() {
		return code + " " + name + " ( " + Double.toString(latitude)
				+ " , " + Double.toString(longitude) + " )";
	}
}

