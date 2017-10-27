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
 * PearPlanner/RaiderPlanner Created by Team BRONZE on 4/27/17.
 */
public class Requirement extends ModelEntity {
	/**
	 *serial version uid.
	 */
	private static final long serialVersionUID = 6784600947667494980L;
	protected boolean checkedCompleted;
	protected double estimatedTimeInHours;
	protected ArrayList<Activity> activityLog = new ArrayList<>();
	protected int initialQuantity;
	protected int remainingQuantity;
	protected QuantityType quantityType;

	// public methods

	// Getters:
	/**check whether it is complete.
	 * @return boolean
	 */
	public boolean isComplete() {
		return this.checkedCompleted;
	}

	/**
	 * Returns the QuantityType of this Requirement.
	 *
	 * @return QuantityType
	 */
	public QuantityType getQuantityType() {
		return this.quantityType;
	}

	/**
	 * Returns the estimated time of this Requirement (in hours).
	 *
	 * @return double
	 */
	public double getEstimatedTimeInHours() {
		return estimatedTimeInHours;
	}

	/**
	 * Returns the initial quantity of this Requirement.
	 *
	 * @return int
	 */
	public int getInitialQuantity() {
		return initialQuantity;
	}

	/**
	 * Returns the remaining quantity of this Requirement.
	 */
	public int getRemainingQuantity() {
		return remainingQuantity;
	}

	/**
	 * Returns an array of ActivityEvents that are associated with this Requirement.
	 *
	 * @return Array of activity
	 */
	public Activity[] getActivityLog() {
		return this.activityLog.toArray(new Activity[this.activityLog.size()]);
	}

	/**
	 * Returns a double value representing the progress of this Requirement.
	 *
	 * @return value between 0.0 and 0.1
	 */
	public double requirementProgress() {
		return (double) (this.initialQuantity - this.remainingQuantity) / this.initialQuantity;
	}

	// Setters:
	/**set estimate time in hours.
	 * @param estimatedTimeInHours time in hours to be set
	 */
	public void setEstimatedTimeInHours(double estimatedTimeInHours) {
		this.estimatedTimeInHours = estimatedTimeInHours;
	}

	/**
	 * Change the initial quantity. This will update the progress of this Requirement
	 * to reflect the change.
	 *
	 * @param initialQuantity quantity to bet set
	 */
	public void setInitialQuantity(int initialQuantity) {
		if (this.initialQuantity == this.remainingQuantity) {
			this.initialQuantity = this.remainingQuantity = initialQuantity;
		} else {
			this.initialQuantity = initialQuantity;
			this.update();
		}
	}

	/**set quantity type.
	 * @param quantityType quantityType to be set
	 */
	public void setQuantityType(String quantityType) {
		this.quantityType = QuantityType.get(quantityType);
	}

	/**
	 * Add an Activity to the current Requirement and update the progress of this Requirement
	 * accordingly.
	 *
	 * @param activity
	 *            Activity to be added.
	 */
	public void addActivity(Activity activity) {
		this.activityLog.add(activity);
		this.remainingQuantity -= activity.getActivityQuantity();
		if (remainingQuantity <= 0) {
			this.remainingQuantity = 0;
			this.checkedCompleted = true;
		}
	}

	/**
	 * Update the current Requirement to reflect changes.
	 *
	 * @return whether any changes were made
	 */
	public boolean update() {

		this.remainingQuantity = this.initialQuantity;
		this.checkedCompleted = false;
		for (Activity activity : this.activityLog) {
			this.remainingQuantity -= activity.getActivityQuantity();
		}

		int tempQuantity = this.remainingQuantity;

		if (this.remainingQuantity <= 0) {
			this.remainingQuantity = 0;
			this.checkedCompleted = true;
		}

		return tempQuantity == this.remainingQuantity;
	}

	/**
	 * Returns the Name of the Requirement (used for JavaFX).
	 *
	 * @return Name of the task
	 */
	@Override
	public String toString() {
		return this.name;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activityLog == null) ? 0 : activityLog.hashCode());
		result = prime * result + (checkedCompleted ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(estimatedTimeInHours);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + initialQuantity;
		result = prime * result + ((quantityType == null) ? 0 : quantityType.hashCode());
		result = prime * result + remainingQuantity;
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Requirement other = (Requirement) obj;
		if (activityLog == null) {
			if (other.activityLog != null) {
				return false;
			}
		} else if (!activityLog.equals(other.activityLog)) {
			return false;
		}
		if (checkedCompleted != other.checkedCompleted) {
			return false;
		}
		if (Double.doubleToLongBits(estimatedTimeInHours) != Double
				.doubleToLongBits(other.estimatedTimeInHours)) {
			return false;
		}
		if (initialQuantity != other.initialQuantity) {
			return false;
		}
		if (quantityType == null) {
			if (other.quantityType != null) {
				return false;
			}
		} else if (!quantityType.equals(other.quantityType)) {
			return false;
		}
		if (remainingQuantity != other.remainingQuantity) {
			return false;
		}
		return true;
	}

	@Override
	public void open(MenuController.Window current) {
		try {
			MainController.ui.requirementDetails(this);
		} catch (IOException e) {
			UiManager.reportError("Unable to open View file");
		}
	}

	// Constructors:
	/**constructor.
	 * @param name		name of this requirement
	 * @param details	details
	 * @param time		time
	 * @param quantity	quantity
	 * @param type		type
	 */
	public Requirement(String name, String details, double time, int quantity, String type) {
		super(name, details);
		this.estimatedTimeInHours = time;
		this.initialQuantity = this.remainingQuantity = quantity;
		this.quantityType = QuantityType.get(type);
	}
}
