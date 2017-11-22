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

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * PearPlanner/RaiderPlanner Created by Team BRONZE on 4/27/17.
 */
public class Notification implements Serializable {
	/**
	 *serial version uid.
	 */
	private static final long serialVersionUID = 4080378715756030248L;
	// private data
	private String title;
	private GregorianCalendar dateTime;
	private MultilineString details;
	private boolean read;
	private ModelEntity link;

	// public methods

	// getters
	/**get title.
	 * @return String
	 */
	public String getTitle() {
		return title;
	}

	/**get date time.
	 * @return GregorianCalendar
	 */
	public GregorianCalendar getDateTime() {
		return dateTime;
	}

	/**get details.
	 * @return MultilineString
	 */
	public MultilineString getDetails() {
		return details;
	}

	/**get details as String.
	 * @return String
	 */
	public String getDetailsAsString() {
		return this.details.getAsString();
	}

	/**check whether it is read or not.
	 * @return boolean
	 */
	public boolean isRead() {
		return read;
	}

	/**get link.
	 * @return ModelEntity
	 */
	public ModelEntity getLink() {
		return link;
	}

	@Override
	public String toString() {
		return this.title + ": " + this.getDetailsAsString();
	}

	// setters
	/**
	 * set it is read.
	 */
	public void read() {
		this.read = true;
	}

	/**
	 * set read as false.
	 */
	public void unread() {
		this.read = false;
	}

	/**
	 *change the current value of read to the opposite value e.g false to true and vice versa.
	 */
	public void toggle() {
		this.read = !read;
	}

	// constructors
	/**constructor.
	 * @param title		String title to be set
	 * @param dateTime	GregorianCalendar date to be set
	 * @param details	String details to be set
	 * @param link		ModelEntity
	 */
	public Notification(String title, GregorianCalendar dateTime, String details,
			ModelEntity link) {
		this.title = title;
		this.dateTime = dateTime;
		this.details = new MultilineString(details);
		this.read = false;
		this.link = link;
	}

	/**Constructor.
	 * @param title		String title to be set
	 * @param dateTime	GregorianCalendar date to be set
	 * @param details	String details to be set
	 */
	public Notification(String title, GregorianCalendar dateTime, String details) {
		this.title = title;
		this.dateTime = dateTime;
		this.details = new MultilineString(details);
		this.read = false;
	}

	/**constructors.
	 * @param title		String
	 * @param dateTime	GregorianCalendar
	 */
	public Notification(String title, GregorianCalendar dateTime) {
		this.title = title;
		this.dateTime = dateTime;
		this.read = false;
	}
}