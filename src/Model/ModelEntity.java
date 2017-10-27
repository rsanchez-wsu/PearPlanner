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

import Controller.MenuController;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * PearPlanner/RaiderPlanner Created by Team BRONZE on 4/27/17.
 */
public class ModelEntity implements Serializable {
	/**
	 * serial version uid.
	 */
	private static final long serialVersionUID = -2536798493663466670L;
	protected String name = "";
	protected MultilineString details = null;
	protected ArrayList<Note> notes;

	// getters
	/**get name of this model entity.
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**get details of this model entity.
	 * @return details
	 */
	public MultilineString getDetails() {
		return details;
	}

	/**set name for this model entity.
	 * @param newName name to be set
	 */
	public void setName(String newName) {
		name = newName;
	}

	/**set details for this model entity.
	 * @param newDetails details to be set
	 */
	public void setDetails(String newDetails) {
		details = new MultilineString(newDetails);
	}

	/**set details for this model entity.
	 * @param newDetails array of details to be set
	 */
	public void setDetails(String[] newDetails) {
		details = new MultilineString(newDetails);
	}

	/**set details for this model entity.
	 * @param newDetails array list of details to be set
	 */
	public void setDetails(ArrayList<String> newDetails) {
		details = new MultilineString(newDetails.toArray(new String[newDetails.size()]));
	}

	/**set details for this model entity.
	 * @param newDetails multilineString details to be set
	 */
	public void setDetails(MultilineString newDetails) {
		details = newDetails;
	}

	/**add properties.
	 * @param aName	name of the properties to be added
	 * @param aDetails details of the properties to be added
	 */
	public void addProperties(String aName, MultilineString aDetails) {
		setName(aName);
		setDetails(aDetails.clone());
	}

	/**add properties.
	 * @param aName	name of the properties to be added
	 * @param aDetails details of the properties to be added
	 */
	public void addProperties(String aName, String aDetails) {
		setName(aName);
		setDetails(aDetails);
	}

	/**
	 * Open the appropriate UI window for this class To be overridden by children.
	 */
	public void open(MenuController.Window current) {
	}

	/**
	 * contrustor.
	 */
	public ModelEntity() {
		this("");
	}

	/**
	 * contrustor.
	 */
	public ModelEntity(String cName) {
		this(cName, "");
	}

	/**
	 * contrustor.
	 */
	public ModelEntity(String cName, String cDetails) {
		this(cName, cDetails.split("\n"));
	}

	/**
	 * contrustor.
	 */
	public ModelEntity(String cName, String[] cDetails) {
		setName(cName);
		setDetails(cDetails);
		notes = new ArrayList<>();
	}

	/**
	 * contrustor.
	 */
	public ModelEntity(String cName, String[] cDetails, ArrayList<Note> cNotes) {
		this(cName, cDetails);
		notes = new ArrayList<>(cNotes);
	}
}
