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
import Controller.MenuController;
import View.UiManager;

import java.io.IOException;
import java.util.ArrayList;

/**
 * PearPlanner/RaiderPlanner Created by Team BRONZE on 4/27/17 at 20:59.
 */
public class Module extends VersionControlEntity {
	/**
	 * serial version uid.
	 */
	private static final long serialVersionUID = -2128360383796838996L;
	// private data
	private ArrayList<Assignment> assignments = new ArrayList<>();
	private Person organiser;
	private String moduleCode;
	private ArrayList<TimetableEvent> timetable = new ArrayList<>();

	@Override
	protected void replace(VersionControlEntity receivedVce) {
		if (receivedVce instanceof Module) {
			Module castedVce = (Module) receivedVce;
			if (castedVce.getOrganiser() != null) {
				this.organiser = castedVce.getOrganiser();
			}
			if (castedVce.getModuleCode() != null) {
				this.moduleCode = castedVce.getModuleCode();
			}
			if (castedVce.getAssignments() != null) {
				this.assignments = castedVce.getAssignments();
			}
			if (castedVce.getAssignments() != null) {
				this.timetable = castedVce.getTimetable();
			}
		}

		super.replace(receivedVce);
	}

	// public methods
	/**
	 * verbose true recursively build a verbose report, false call method toString.
	 *
	 * @param verbose
	 *            boolean
	 * @return String
	 */
	public String toString(boolean verbose) {
		if (verbose) {
			StringBuilder r = new StringBuilder();
			r.append(toString());
			r.append("\n");
			r.append("Organiser: " + organiser.toString());
			r.append("\n");
			r.append("Total Assignments: " + Integer.toString(assignments.size()));
			r.append("\n");

			int i = -1;
			int ii = assignments.size();

			while (++i < ii) {
				r.append("\n");
				r.append(assignments.get(i).toString(true));
			}

			return r.toString();

		} else {
			return toString();
		}
	}

	@Override
	public String toString() {
		return "Module: " + this.name + " ( " + this.moduleCode + " )";
	}

	// getters
	/**
	 * get list of assignments.
	 *
	 * @return assignments
	 */
	public ArrayList<Assignment> getAssignments() {
		return assignments;
	}

	/**
	 * get the person that is an organiser.
	 *
	 * @return organsier
	 */
	public Person getOrganiser() {
		return organiser;
	}

	/**
	 * get module code.
	 *
	 * @return moduleCode
	 */
	public String getModuleCode() {
		return moduleCode;
	}

	/**
	 * get list of time table.
	 *
	 * @return timetable
	 */
	public ArrayList<TimetableEvent> getTimetable() {
		return timetable;
	}

	/**
	 * get the number of assignments.
	 *
	 * @return int
	 */
	public int getNoOfAssignments() {
		return this.assignments.size();
	}

	/**
	 * Calculates how much of this Module has been completed in percentage.
	 *
	 * @return int (0-100)
	 */
	public int calculateProgress() {
		if (this.assignments.size() == 0) {
			return 0;
		}

		int sum = 0;
		for (Assignment assignment : this.assignments) {
			sum += assignment.calculateProgress();
		}
		return sum / this.assignments.size();
	}

	// setters
	/**add new assignment.
	 * @param newAssignment assignment to be added
	 */
	public void addAssignment(Assignment newAssignment) {
		// initial set up code below - check if this needs updating
		if (!assignments.contains(newAssignment)) {
			assignments.add(newAssignment);
		}
	}

	/**remove assignment.
	 * @param newAssignment assignment to be removed
	 */
	public void removeAssignment(Assignment newAssignment) {
		// initial set up code below - check if this needs updating
		if (assignments.contains(newAssignment)) {
			assignments.remove(newAssignment);
		}
	}

	/**set organiser.
	 * @param newOrganiser person that to be an organiser
	 */
	public void setOrganiser(Person newOrganiser) {
		organiser = newOrganiser;
	}

	/**set module code.
	 * @param newModuleCode new module code to be added
	 */
	public void setModuleCode(String newModuleCode) {
		moduleCode = newModuleCode;
	}

	/**add time table event.
	 * @param newTimetableEvent TimeTableEvent to be added
	 */
	public void addTimetableEvent(TimetableEvent newTimetableEvent) {
		if (!timetable.contains(newTimetableEvent)) {
			timetable.add(newTimetableEvent);
		}
	}

	/**remove time table event.
	 * @param newTimetableEvent TimeTableEvent to be removed
	 */
	public void removeTimetableEvent(TimetableEvent newTimetableEvent) {
		if (timetable.contains(newTimetableEvent)) {
			timetable.remove(newTimetableEvent);
		}
	}

	@Override
	public void open(MenuController.Window current) {
		try {
			MainController.ui.moduleDetails(this, current);
		} catch (IOException e) {
			UiManager.reportError("Unable to open View file");
		}
	}

	// constructors
	/**costructor.
	 * @param cOrganiser orgainiser to be set
	 * @param cModuleCode	modul code to be set
	 */
	public Module(Person cOrganiser, String cModuleCode) {
		setOrganiser(cOrganiser);
		setModuleCode(cModuleCode);
	}
}
