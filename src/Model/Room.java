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
 * PearPlanner/RaiderPlanner Created by Team BRONZE on 4/27/17.
 */
public class Room extends VersionControlEntity {
	/**
	 *serial version uid.
	 */
	private static final long serialVersionUID = -4397560912648311748L;
	// private data
	private Building building = null;
	private String roomNumber;

	@Override
	protected void replace(VersionControlEntity receivedVce) {
		if (receivedVce instanceof Room) {
			Room castedVce = (Room) receivedVce;
			if (castedVce.getBuilding() != null) {
				this.building = castedVce.getBuilding();
			}
			this.roomNumber = castedVce.getRoomNumber();
		}
		super.replace(receivedVce);
	}

	// public methods

	// getters
	/**get building.
	 * @return Building
	 */
	public Building getBuilding() {
		return building;
	}

	/**get room number.
	 * @return String
	 */
	public String getRoomNumber() {
		return roomNumber;
	}

	/**get location.
	 * @return String
	 */
	public String getLocation() {
		return name + "( " + roomNumber + " )";
	}

	@Override
	public String toString() {
		if (building == null) {
			return name + "( " + roomNumber + " )";
		} else {
			return name + "( " + roomNumber + " ) located in " + building.toString();
		}
	}

	// setters
	/**set building.
	 * @param newBuilding building to be set
	 */
	public void setBuilding(Building newBuilding) {
		building = newBuilding;
	}

	/**set room number.
	 * @param newRoomNumber room number to be set
	 */
	public void setRoomNumber(String newRoomNumber) {
		roomNumber = newRoomNumber;
	}

	// Constructors:
	/**constructor.
	 * @param cRoomNumber	room number to be set
	 * @param cBuilding		building to be set
	 */
	public Room(String cRoomNumber, Building cBuilding) {
		setRoomNumber(cRoomNumber);
		setBuilding(cBuilding);
	}

	/**constructor.
	 * @param cRoomNumber room number to be set
	 */
	public Room(String cRoomNumber) {
		setRoomNumber(cRoomNumber);
	}
}
