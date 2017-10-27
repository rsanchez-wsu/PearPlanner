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
 * PearPlanner/RaiderPlanner
 * Created by Team BRONZE on 4/27/17.
 */
public class Assignment extends VersionControlEntity {
	/**serial version uid.
	 *
	 */
	private static final long serialVersionUID = -8889413763401987226L;
	protected ArrayList<Task> tasks = new ArrayList<>();
	protected ArrayList<Requirement> requirements = new ArrayList<>();
	protected int weighting;
	protected Person setBy = null;
	protected Person markedBy = null;
	protected Person reviewedBy = null;
	protected int marks;
	protected StateType state;  // this may not be needed as we can work it out

	@Override
	protected void replace(VersionControlEntity receivedVce) {
		if (receivedVce instanceof Assignment) {
			Assignment castedVce = (Assignment) receivedVce;
			// this.tasks = castedVCE.getTasks();
			// this.requirements = castedVCE.getRequirements();
			this.weighting = castedVce.getWeighting();
			if (castedVce.getSetBy() != null) {
				this.setBy = castedVce.getSetBy();
			}
			if (castedVce.getMarkedBy() != null) {
				this.markedBy = castedVce.getMarkedBy();
			}
			if (castedVce.getReviewedBy() != null) {
				this.reviewedBy = castedVce.getReviewedBy();
			}
			this.marks = castedVce.getMarks();
			// this.state = castedVCE.getState();
		}
		super.replace(receivedVce);
	}

	/**set State type.
	 */
	public enum StateType {
		IN_PROGRESS, DEADLINE_PASSED, NOT_STARTED
	}

	// public methods
	// getters
	@Override
	public String toString() {
		return "Assignment '" + name + "'";
	}

	/**to string method.
	 * @param verbose boolean
	 * @return String
	 */
	public String toString(boolean verbose) {
		if (verbose) {
			StringBuilder r = new StringBuilder();
			r.append(toString());
			r.append("\n");
			r.append("Total marks: " + Integer.toString(marks));
			r.append("\n");
			r.append("Total weighting: " + Integer.toString(weighting));

			r.append("\n");
			r.append("Set By: " + setBy.toString());
			r.append("\n");
			r.append("Marked By: " + markedBy.toString());
			r.append("\n");
			r.append("Reviewed By: " + reviewedBy.toString());

			return r.toString();
		} else {
			return toString();
		}
	}

	/**get an array list of task.
	 * @return task
	 */
	public ArrayList<Task> getTasks() {
		return tasks;
	}

	/**get an array list of requirements.
	 * @return requirements.
	 */
	public ArrayList<Requirement> getRequirements() {
		return requirements;
	}

	/**get weighting.
	 * @return weighting
	 */
	public int getWeighting() {
		return weighting;
	}

	/**get the person that set the assignment.
	 * @return setBy
	 */
	public Person getSetBy() {
		return setBy;
	}

	/**get the person that marked the assignment.
	 * @return markedBy
	 */
	public Person getMarkedBy() {
		return markedBy;
	}

	/**get the person that reviewed the assignment.
	 * @return reviewedBy
	 */
	public Person getReviewedBy() {
		return reviewedBy;
	}

	/**get number of marks.
	 * @return marks
	 */
	public int getMarks() {
		return marks;
	}

	/**get state type.
	 * @return state
	 */
	public StateType getState() {
		return state;
	}

	// Setters:

	/**
	 * Add a Task to this Assignment.
	 *
	 * @param task Task to be added
	 */
	public void addTask(Task task) {
		this.tasks.add(task);
		task.addAssignmentReference(this);
	}

	/**
	 * Removes the given Task from this assignment.
	 *
	 * @param task Task to be removed
	 * @return true if found and deleted, false otherwise
	 */
	public boolean removeTask(Task task) {
		task.removeAssignmentReference(this);
		return this.tasks.remove(task);
	}

	/**
	 * Add a Requirement to this Assignment.
	 *
	 * @param requirement Task to be added
	 */
	public void addRequirement(Requirement requirement) {
		this.requirements.add(requirement);
	}

	/**
	 * Removes the given Requirement from this Assignment.
	 *
	 * @param requirement Requirement to be removed
	 * @return true if found and deleted, false otherwise
	 */
	public boolean removeRequirement(Requirement requirement) {
		return this.requirements.remove(requirement);
	}

	/**
	 * Calculates how much of this Assignment has been completed in percentage.
	 *
	 * @return int (0-100)
	 */
	public int calculateProgress() {
		if (this.requirements.size() == 0 && this.tasks.size() == 0) {
			return 0;
		}

		int sum = 0;
		int n = 0;
		for (Requirement req : this.requirements) {
			sum += req.requirementProgress() * 100;
			n++;
		}

		for (Task task : this.tasks) {
			if (task.getRequirements().length > 0) {
				sum += task.calculateProgress();
				n++;
			}
		}

		return sum / n;
	}

	@Override
	public void open(MenuController.Window current) {
		try {
			MainController.ui.assignmentDetails(this, current);
		} catch (IOException e) {
			UiManager.reportError("Unable to open View file");
		}
	}

	// Constructor
	/**constructor.
	 * @param cWeighting int
	 * @param cSetBy	Person
	 * @param cMarkedBy	Person
	 * @param cReviewedBy	Person
	 * @param cMarks	int
	 */
	public Assignment(int cWeighting, Person cSetBy,
			Person cMarkedBy, Person cReviewedBy, int cMarks) {
		weighting = cWeighting;
		setBy = cSetBy;
		markedBy = cMarkedBy;
		reviewedBy = cReviewedBy;
		marks = cMarks;
		//MainController.getSPC().getPlanner().getDeadlineNotifications().put(this, new boolean[2]);
	}
}
