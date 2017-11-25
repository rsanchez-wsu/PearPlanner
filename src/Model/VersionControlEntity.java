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

import Controller.MainController;

import java.util.HashMap;

/**
 * PearPlanner/RaiderPlanner Created by Team BRONZE on 4/27/17.
 */
public class VersionControlEntity extends ModelEntity {
	/**
	 *serial version uid.
	 */
	private static final long serialVersionUID = 8015228884552591551L;
	protected int version;
	protected String uid;
	protected boolean sealed;
	private static HashMap<String, VersionControlEntity> library = new HashMap<>();
	protected boolean importer = false; // used for VCEs created during XML import
	// private methods

	/**
	 * This method overwrites the data in the received object with that received This method will
	 * need to overrode in every class that extends it.
	 *
	 * @param receivedVce  vce that used to set name, details, and version.
	 */
	protected void replace(VersionControlEntity receivedVce) {
		name = receivedVce.getName();
		details = receivedVce.getDetails();
		version = receivedVce.getVersion();
		// super.replace(receivedVCE);
	}

	// public methods

	/**
	 * Update ths VCE with a given one.
	 *
	 * @param receivedVce
	 *            received VCE for updating the current one.
	 * @return whether updated successfully.
	 */
	public boolean update(VersionControlEntity receivedVce) {
		if (uid.equals(receivedVce.getUID()) && version < receivedVce.getVersion()) {
			replace(receivedVce);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Find the given VCE in the library and then update it.
	 *
	 * @param receivedVce
	 *            a VCE to be looked for and updated.
	 * @return whether found and updated successfully.
	 */
	public static boolean findAndUpdate(VersionControlEntity receivedVce) {
		String UId = receivedVce.getUID();
		if (inLibrary(UId)) {
			library.get(UId).update(receivedVce);
			return true;
		} else {
			return false;
		}
	}

	/**if sealed is not true than set importer true otherwise false.
	 * @return boolean
	 */
	public boolean makeImporter() {
		if (!sealed) {
			importer = true;
			return true;
		} else {
			return false;
		}
	}

	/**check whether it is importer.
	 * @return boolean
	 */
	public boolean isImporter() {
		return importer;
	}

	/**
	 * Add this VCE to the library.
	 *
	 * @return whether added successfully.
	 */
	public boolean addToLibrary() {
		if (importer) {
			if (inLibrary(uid)) {
				return false;
			} else {
				importer = false;
				sealed = true;
				library.put(uid, this);
				MainController.getSpc().getPlanner().addToVersionControlLibrary(this);
				return true;
			}
		} else {
			return false;
		}
	}

	/**
	 * Get a VCE from the library by it's UID
	 *
	 * @param UId
	 *            UID to be looked for.
	 * @return a valid VCE if found, null otherwise.
	 */
	public static VersionControlEntity get(String UId) {
		if (inLibrary(UId)) {
			return library.get(UId);
		} else {
			return null;
		}
	}

	/**
	 * Check whether a VCE with the given UID exists in the library.
	 *
	 * @param UId
	 *            UID to be checked for.
	 * @return true if found, false otherwise.
	 */
	public static boolean inLibrary(String UId) {
		return library.containsKey(UId);
	}

	// getters
	/**get version.
	 * @return int
	 */
	public int getVersion() {
		return version;
	}

	/**get uid.
	 * @return String
	 */
	public String getUID() {
		return uid;
	}

	/**
	 * Returns the VCE library.
	 *
	 * @return HashMap containing all VCEs.
	 */
	public static HashMap<String, VersionControlEntity> getLibrary() {
		return library;
	}

	/**
	 * Returns a summary of the VCEs in the library.
	 *
	 * @return String
	 */
	public static String libraryReport() {
		return "Total Entries: " + library.size();
	}

	/**
	 * Set a new UID and version for this VCE.
	 *
	 * @param newUId
	 *            new UID
	 * @param newVersion
	 *            new version
	 * @return whether changed successfully.
	 */
	public boolean setUId(String newUId, int newVersion) {
		// setUID(newUID);
		if (importer) {
			setUId(newUId);
			version = newVersion;
			return true;
		} else if (sealed || library.containsKey(newUId)) {
			return false;
		} else {
			setUId(newUId);
			version = newVersion;
			return true;
		}
	}

	/**
	 * Set a new UID for this VCE.
	 *
	 * @param newUId
	 *            new UID
	 * @return whether changed successfully.
	 */
	public boolean setUId(String newUId) {
		if (importer) {
			uid = newUId;
			return true;
		} else if (sealed || library.containsKey(newUId)) {
			return false;
		} else {
			uid = newUId;
			library.put(newUId, this);
			MainController.getSpc().getPlanner().addToVersionControlLibrary(this);
			return true;
		}
	}

	/**
	 * Called once the program is loaded, adds this VCE to the library if possible.
	 */
	public void reload() {
		if (!inLibrary(this.uid) && !importer && sealed) {
			library.put(this.uid, this);
		}
	}

	// Constructors
	/**constructor.
	 * @param leaveUnsealed boolean value to set for sealed variable
	 */
	public VersionControlEntity(boolean leaveUnsealed) {
		super();
		sealed = !leaveUnsealed;
	}

	/**
	 * default constructor.
	 */
	public VersionControlEntity() {
		super();
		sealed = false;
	}

	/**constructor.
	 * @param UId uid to be set
	 */
	public VersionControlEntity(String UId) {
		super();
		sealed = setUId(UId);
	}

}
