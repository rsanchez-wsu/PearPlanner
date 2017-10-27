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

import java.util.ArrayList;

/**
 * PearPlanner/RaiderPlanner Created by Team BRONZE on 4/27/17.
 */
public class QuantityType extends ModelEntity {
	/**
	 * serial version uid.
	 */
	private static final long serialVersionUID = -5872076719268398409L;
	private static ArrayList<QuantityType> quantityDatabase = new ArrayList<>();

	/**get a list of names.
	 * @return String[]
	 */
	public static String[] listOfNames() {
		String[] rr = new String[quantityDatabase.size()];
		int ii = quantityDatabase.size();
		int i = -1;
		while (++i < ii) {
			rr[i] = quantityDatabase.get(i).getName();
		}
		return rr;
	}

	/**get list of quantity types.
	 * @return QuantityType[]
	 */
	public static QuantityType[] listOfQuantityTypes() {
		QuantityType[] r = new QuantityType[quantityDatabase.size()];
		int i = -1;
		int ii = quantityDatabase.size();
		while (++i < ii) {
			r[i] = quantityDatabase.get(i);
		}
		return r;
	}

	/**get quantity type.
	 * @param tt task type
	 * @return QuantityType
	 */
	public static QuantityType get(String tt) {
		int i = -1;
		int ii = quantityDatabase.size();
		while (++i < ii) {
			if (quantityDatabase.get(i).equals(tt)) {
				return quantityDatabase.get(i);
			}
		}
		return DEFAULT;
	}

	/**check whether this quantity type exists.
	 * @param qt quantity type
	 * @return boolean
	 */
	public static boolean exists(QuantityType qt) {
		int i = -1;
		int ii = quantityDatabase.size();
		while (++i < ii) {
			if (quantityDatabase.get(i).equals(qt)) {
				return true;
			}
		}
		return false;
	}

	/**check whether this task type exists.
	 * @param tt task type
	 * @return boolean
	 */
	public static boolean exists(String tt) {
		int i = -1;
		int ii = quantityDatabase.size();
		while (++i < ii) {
			if (quantityDatabase.get(i).equals(tt)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Create a new QuantityType.
	 *
	 * @param cName
	 *            Name of the quantity.
	 * @param cDetails
	 *            Details of the quantity.
	 * @return QuantityType
	 */
	public static QuantityType create(String cName, String cDetails) {
		QuantityType t = new QuantityType(cName, cDetails);
		if (MainController.getSpc() != null) {
			MainController.getSpc().addQuantityType(t);
		}
		return t;
	}

	/**
	 * Create a new QuantityType.
	 *
	 * @param cName
	 *            Name of the quantity.
	 * @return QuantityType
	 */
	public static QuantityType create(String cName) {
		QuantityType t = new QuantityType(cName);
		if (MainController.getSpc() != null) {
			MainController.getSpc().addQuantityType(t);
		}
		return t;
	}

	/**
	 * Create a new QuantityType from an existing one.
	 *
	 * @param type
	 *            QuantityType object
	 */
	public static void create(QuantityType type) {
		if (!QuantityType.quantityDatabase.contains(type)) {
			QuantityType.quantityDatabase.add(type);
			if (MainController.getSpc() != null) {
				MainController.getSpc().addQuantityType(type);
			}
		}
	}

	/**
	 * A toString method used in TableView.
	 *
	 * @return String
	 */
	public String toString() {
		return this.name;
	}

	// this is a temporary way to populate the array until we later replace from reading a set up
	// file
	static {
		/**
		 *inner class.
		 */
		class pair {
			public String a;
			public String b;

			/**set name and details in pair.
			 * @param name		name to be set
			 * @param details	details to be set
			 */
			pair(String name, String details) {
				a = name;
				b = details;
			}
		}

		pair[] staticTypes = { new pair("Other", "Other"), new pair("Hours", "Work in hours"),
				new pair("Books read", "Read this number of books"),
				new pair("Videos watched", "Watched this number of videos"),
				new pair("thousand words written", "Number of thousand words written"),
				new pair("questions answered", "Number of questions answered") };
		int i = -1;
		int ii = staticTypes.length;
		while (++i < ii) {
			@SuppressWarnings("unused")
			QuantityType t = new QuantityType(staticTypes[i].a, staticTypes[i].b);
		}
	}

	/**constructor.
	 * @param cName name to be set
	 */
	private QuantityType(String cName) {
		super(cName);
		if (!exists(this)) {
			quantityDatabase.add(this);
		}
	}

	/**constuctor.
	 * @param cName name to be set
	 * @param cDetails details to be set
	 */
	private QuantityType(String cName, String cDetails) {
		super(cName, cDetails);
		if (!exists(this)) {
			quantityDatabase.add(this);
		}
	}


	/**check whether this quantityType and obj are equal.
	 * @param obj object to be compared
	 * @return boolean
	 */
	public boolean isEquals(Object obj) {
		QuantityType that = (QuantityType) obj;
		return getName().equals(that.getName());
	}

	/**check if it has the same name.
	 * @param c name to be compare
	 * @return boolean
	 */
	public boolean equals(String c) {
		return getName().equals(c);
	}

	public static QuantityType DEFAULT = quantityDatabase.get(0);
}
