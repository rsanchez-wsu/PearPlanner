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
public class Note implements Serializable {
	/**
	 *serial version uid.
	 */
	private static final long serialVersionUID = 5727477113455798093L;
	// private data
	private String title;
	private GregorianCalendar timeStamp;
	private MultilineString text;

	// public methods

	// getters
	/**get title of this note.
	 * @return String
	 */
	public String getTitle() {
		// initial set up code below - check if this needs updating
		return title;
	}

	/**get time stamp.
	 * @return GregorianCalendar
	 */
	public GregorianCalendar getTimeStamp() {
		// initial set up code below - check if this needs updating
		return timeStamp;
	}

	/**get text.
	 * @return MultilineString
	 */
	public MultilineString getText() {
		return text;
	}

	// setters
	/**set title.
	 * @param newTitle title to be set
	 */
	public void setTitle(String newTitle) {
		// initial set up code below - check if this needs updating
		title = newTitle;
	}

	/**set time stamp.
	 * @param newTimeStamp (GregorianCalendar) new time stamp to be set
	 */
	public void setTimeStamp(GregorianCalendar newTimeStamp) {
		// initial set up code below - check if this needs updating
		timeStamp = newTimeStamp;
	}

	/**set time stamp.
	 * @param Y	year
	 * @param M	month
	 * @param D	date
	 * @param h hours
	 * @param m	minutes
	 * @param s	seconds
	 */
	public void setTimeStamp(int Y, int M, int D, int h, int m, int s) {
		// initial set up code below - check if this needs updating
		timeStamp = new GregorianCalendar(Y, M, D, h, m, s);
	}

	/**set text.
	 * @param newText text to be set
	 */
	public void setText(MultilineString newText) {
		// initial set up code below - check if this needs updating
		text = newText;
	}

	/**constructor.
	 * @param title		title to be set
	 * @param timeStamp	time stamp to be set
	 * @param text		text to be set
	 */
	public Note(String title, GregorianCalendar timeStamp, MultilineString text) {
		this.title = title;
		this.timeStamp = timeStamp;
		this.text = text;
	}
}
