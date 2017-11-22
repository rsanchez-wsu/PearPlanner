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

/**
 * PearPlanner/RaiderPlanner Created by Team BRONZE on 4/27/17.
 */
public class Exam extends Assignment {
	/**
	 * serial version uid.
	 */
	private static final long serialVersionUID = 6435468033960550569L;
	// private data
	private Exam resit = null;
	private ExamEvent timeSlot = null;

	@Override
	protected void replace(VersionControlEntity receivedVce) {
		if (receivedVce instanceof Exam) {
			Exam castedVce = (Exam) receivedVce;
			if (castedVce.getResit() != null) {
				this.resit = castedVce.getResit();
			}
			if (castedVce.getTimeSlot() != null) {
				this.timeSlot = castedVce.getTimeSlot();
			}
		}

		super.replace(receivedVce);
	}

	// public methods

	// getters
	/**get resit.
	 * @return resit
	 */
	public Exam getResit() {
		return resit;
	}

	/**get time slot.
	 * @return timeSlot
	 */
	public ExamEvent getTimeSlot() {
		return timeSlot;
	}

	// constructors
	/**constructor.
	 * @param cWeighting	int
	 * @param cSetBy	Person
	 * @param cMarkedBy	Person
	 * @param cReviewedBy	Person
	 * @param cMarks	int
	 * @param cTimeSlot	ExamEvent
	 * @param cResit	Exam
	 */
	public Exam(int cWeighting, Person cSetBy, Person cMarkedBy, Person cReviewedBy, int cMarks,
			ExamEvent cTimeSlot, Exam cResit) {
		super(cWeighting, cSetBy, cMarkedBy, cReviewedBy, cMarks);
		timeSlot = cTimeSlot;
		resit = cResit;
	}

	/**constructor.
	 * @param cWeighting	int
	 * @param cSetBy	Person
	 * @param cMarkedBy	Person
	 * @param cReviewedBy	Person
	 * @param cMarks	int
	 * @param cTimeSlot	ExamEvent
	 */
	public Exam(int cWeighting, Person cSetBy, Person cMarkedBy, Person cReviewedBy, int cMarks,
			ExamEvent cTimeSlot) {
		super(cWeighting, cSetBy, cMarkedBy, cReviewedBy, cMarks);
		timeSlot = cTimeSlot;
		resit = null;
	}
}
