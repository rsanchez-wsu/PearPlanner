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

package edu.wright.cs.raiderplanner.model;

import edu.wright.cs.raiderplanner.controller.MenuController;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * PearPlanner/RaiderPlanner.
 * Created by Team BRONZE on 4/27/17
 */
public class ModelEntity implements Serializable {
	private static final long serialVersionUID = -6684777070822468240L;
	protected String name = "";
	protected MultilineString details = null;
	protected ArrayList<Note> notes;


	// getters
	public String getName() {
		return name;
	}

	public MultilineString getDetails() {
		return details;
	}

	public void setName(String newName) {
		name = newName;
	}

	public void setDetails(String newDetails) {
		details = new MultilineString(newDetails);
	}

	public void setDetails(String[] newDetails) {
		details = new MultilineString(newDetails);
	}

	public void setDetails(ArrayList<String> newDetails) {
		details = new MultilineString(newDetails.toArray(new String[newDetails.size()]));
	}

	public void setDetails(MultilineString newDetails) {
		details = newDetails;
	}


	public void addProperties(String aName, MultilineString aDetails) {
		setName(aName);
		setDetails(aDetails.clone());
	}

	public void addProperties(String aName, String aDetails) {
		setName(aName);
		setDetails(aDetails);
	}

	/**
	 * Open the appropriate UI window for this class
	 * To be overridden by children.
	 */
	public void open(MenuController.Window current) {
	}

	public ModelEntity() {
		this("");
	}

	public ModelEntity(String cname) {
		this(cname, "");
	}

	public ModelEntity(String cname, String cdetails) {
		this(cname, cdetails.split("\n"));
	}

	public ModelEntity(String cname, String[] cdetails) {
		setName(cname);
		setDetails(cdetails);
		notes = new ArrayList<>();
	}

	public ModelEntity(String cname, String[] cdetails, ArrayList<Note> cnotes){
	this(cname, cdetails);
		notes = new ArrayList<Note>(cnotes);
	}

	/* the reason overriding hashCode() because it subclass, QuantityType,
	 * overrided the equals method and it doesn't have any non-static field.
	 * Therefore, hashCode() can't be implemented there,
	 * instead in its superclass
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((details == null) ? 0 : details.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		return result;
	}
}
