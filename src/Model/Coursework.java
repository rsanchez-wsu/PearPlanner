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

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * PearPlanner/RaiderPlanner
 * Created by Team BRONZE on 4/27/17.
 */
public class Coursework extends Assignment {
	/**
	 *serial version uid.
	 */
	private static final long serialVersionUID = 5621701916095916584L;
	private Event startDate;
	private Deadline deadline;
	private ArrayList<Extension> extensions;

	// private methods
	@Override
	protected void replace(VersionControlEntity receivedVce) {
		if (receivedVce instanceof Coursework) {
			Coursework castedVce = (Coursework) receivedVce;
			if (castedVce.getStartDate() != null) {
				this.startDate = castedVce.getStartDate();
			}
			if (castedVce.getDeadline() != null) {
				this.deadline = castedVce.getDeadline();
			}
			if (castedVce.getExtensions() != null) {
				this.extensions = castedVce.getExtensions();
			}
		}

		super.replace(receivedVce);
	}
	// public methods

	// getters
	/**get the start date of an event.
	 * @return startDate
	 */
	public Event getStartDate() {
		return startDate;
	}

	/**get dead line.
	 * @return deadline
	 */
	public Deadline getDeadline() {
		return deadline;
	}

	/**get an array list of extensions.
	 * @return extension
	 */
	public ArrayList<Extension> getExtensions() {
		return extensions;
	}

	/**get an array list of notes.
	 * @return notes
	 */
	public ArrayList<Note> getNotes() {
		return notes;
	}

	/**
	 * Returns a String representing the deadline.
	 * Used in JavaFX.
	 *
	 * @return String
	 */
	public String getDeadlineString() {
		return new SimpleDateFormat("dd/MM/yyyy HH:MM").format(this.deadline.getDate());
	}

	// setters
	/**add a note.
	 * @param newNote new note to be added
	 */
	public void addNote(Note newNote) {
		if (!notes.contains(newNote)) {
			notes.add(newNote);
		}
	}

	/**remove a note.
	 * @param oldNote note to be removed
	 */
	public void removeNote(Note oldNote) {
		if (notes.contains(oldNote)) {
			notes.remove(oldNote);
		}
	}

	// Constructors
	/**contructor.
	 * @param cWeighting	int
	 * @param cSetBy	Person
	 * @param cMarkedBy	Person
	 * @param cReviewedBy	Person
	 * @param cMarks	int
	 * @param cStartDate	Event
	 * @param cDeadline	Deadline
	 * @param cExtensions ArrayList of Extension
	 */
	public Coursework(int cWeighting, Person cSetBy, Person cMarkedBy,
						Person cReviewedBy, int cMarks, Event cStartDate,
						Deadline cDeadline, ArrayList<Extension> cExtensions) {
		super(cWeighting, cSetBy, cMarkedBy, cReviewedBy, cMarks);
		startDate = cStartDate;
		deadline = cDeadline;
		extensions = new ArrayList<>(cExtensions);
	}

}
