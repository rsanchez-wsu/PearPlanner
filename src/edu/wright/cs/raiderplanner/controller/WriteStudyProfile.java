/*
 * Copyright (C) 2017 - Benjamin Dickson, Andrew Odintsov, Zilvinas Ceikauskas,
 * Bijan Ghasemi Afshar
 *
 * Copyright (C) 2020 - Joshua Ehlinger, Nathan Griffith, Sierra Sprungl, Bryten Jones
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


package edu.wright.cs.raiderplanner.controller;

import edu.wright.cs.raiderplanner.model.HubFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * This class generates an XML Hubfile based on some input
 * from the user and some hardcoded examples.
 */
public class WriteStudyProfile {

	/**
	 * This constructor writes to a studyProfile document.
	 * @param hubFile Hubfile that contains the user's data
	 */
	public WriteStudyProfile(HubFile hubFile) {

		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("hubfile");
			doc.appendChild(rootElement);
			//version
			Element version = doc.createElement("version");
			rootElement.appendChild(version);
			version.appendChild(doc.createTextNode(hubFile.getVersion() + ""));
			//assets - persons, buildings, rooms, timetableEventType
			final Element assets = doc.createElement("assets");
			final Element persons = doc.createElement("persons");

			//person
			Element person = doc.createElement("person");
			persons.appendChild(person);
			Element personName = doc.createElement("name");
			personName.appendChild(doc.createTextNode("Example Professor"));
			Element personDetails = doc.createElement("details");
			personDetails.appendChild(doc.createTextNode("This is an example professor."));
			Element personVersion = doc.createElement("version");
			personVersion.appendChild(doc.createTextNode("1"));
			Element personUiD = doc.createElement("uid");
			personUiD.appendChild(doc.createTextNode("PERSON01"));
			Element personGivenNames = doc.createElement("givenNames");
			personGivenNames.appendChild(doc.createTextNode("Example"));
			Element personFamilyName = doc.createElement("familyName");
			personFamilyName.appendChild(doc.createTextNode("Professor"));
			Element personSalutation = doc.createElement("salutation");
			personSalutation.appendChild(doc.createTextNode("Professor"));
			Element personEmail = doc.createElement("email");
			personEmail.appendChild(doc.createTextNode("example.professor@wright.edu"));
			Element personFamilyNameLast = doc.createElement("familyNameLast");
			personFamilyNameLast.appendChild(doc.createTextNode("true"));
			Element personPassword = doc.createElement("password");
			personPassword.appendChild(doc.createTextNode("Password1234"));
			person.appendChild(personName);
			person.appendChild(personDetails);
			person.appendChild(personVersion);
			person.appendChild(personUiD);
			person.appendChild(personGivenNames);
			person.appendChild(personFamilyName);
			person.appendChild(personSalutation);
			person.appendChild(personEmail);
			person.appendChild(personFamilyNameLast);
			person.appendChild(personPassword);
			assets.appendChild(persons);


			//buildings
			Element buildings = doc.createElement("buildings");
			assets.appendChild(buildings);
			Element building = doc.createElement("building");
			buildings.appendChild(building);
			Element buildingName = doc.createElement("name");
			buildingName.appendChild(doc.createTextNode("Russ"));
			building.appendChild(buildingName);
			Element buildingDetails = doc.createElement("details");
			buildingDetails.appendChild(doc.createTextNode("College of Engineering"
					+ "and Computer Science"));
			building.appendChild(buildingDetails);
			Element buildingVersion = doc.createElement("version");
			buildingVersion.appendChild(doc.createTextNode("1"));
			building.appendChild(buildingVersion);
			Element buildingUiD = doc.createElement("uid");
			buildingUiD.appendChild(doc.createTextNode("russ"));
			building.appendChild(buildingUiD);
			Element buildingCode = doc.createElement("code");
			buildingCode.appendChild(doc.createTextNode("A"));
			building.appendChild(buildingCode);
			Element buildingLatt = doc.createElement("lattitude");
			buildingLatt.appendChild(doc.createTextNode("51.532178"));
			building.appendChild(buildingLatt);
			Element buildingLong = doc.createElement("longitude");
			buildingLong.appendChild(doc.createTextNode("-0.123806"));
			building.appendChild(buildingLong);

			//rooms
			Element rooms = doc.createElement("rooms");
			assets.appendChild(rooms);
			Element room = doc.createElement("room");
			rooms.appendChild(room);
			Element roomName = doc.createElement("name");
			roomName.appendChild(doc.createTextNode("Russ Engineering"));
			room.appendChild(roomName);
			Element roomDetails = doc.createElement("details");
			roomDetails.appendChild(doc.createTextNode("Engineering College"));
			room.appendChild(roomDetails);
			Element roomVersion = doc.createElement("version");
			roomVersion.appendChild(doc.createTextNode("1"));
			room.appendChild(roomVersion);
			Element roomUiD = doc.createElement("uid");
			roomUiD.appendChild(doc.createTextNode("russEng"));
			room.appendChild(roomUiD);
			Element roomBuidling = doc.createElement("building");
			roomBuidling.appendChild(doc.createTextNode("russ"));
			room.appendChild(roomBuidling);
			Element roomNumber = doc.createElement("roomNumber");
			roomNumber.appendChild(doc.createTextNode("Room-1"));
			room.appendChild(roomNumber);

			//timetableEventTypes
			Element timetableEventTypes = doc.createElement("timetableEventTypes");
			assets.appendChild(timetableEventTypes);
			Element timetableEventType = doc.createElement("timetableEventType");
			timetableEventTypes.appendChild(timetableEventType);
			Element timetableName = doc.createElement("name");
			timetableName.appendChild(doc.createTextNode("Lecture"));
			timetableEventType.appendChild(timetableName);
			Element timetableDetails = doc.createElement("details");
			timetableDetails.appendChild(doc.createTextNode("Lectures "
					+ "are things that some people don't turn up to"));
			timetableEventType.appendChild(timetableDetails);
			Element timetableVersion = doc.createElement("version");
			timetableVersion.appendChild(doc.createTextNode("1"));
			timetableEventType.appendChild(timetableVersion);
			Element timetableUiD = doc.createElement("uid");
			timetableUiD.appendChild(doc.createTextNode("LECTURE"));
			timetableEventType.appendChild(timetableUiD);

			rootElement.appendChild(assets);

			//Study Profile Element
			Element studyProfile = doc.createElement("studyProfile");
			rootElement.appendChild(studyProfile);

			//name
			Element name = doc.createElement("name");
			name.appendChild(doc.createTextNode(hubFile.getSemesterName()));
			studyProfile.appendChild(name);
			//details
			Element details = doc.createElement("details");
			details.appendChild(doc.createTextNode(hubFile.getSemesterDetails().getAsString()));
			studyProfile.appendChild(details);
			//uid
			Element uid = doc.createElement("uid");
			uid.appendChild(doc.createTextNode(hubFile.getSemesterUId()));
			studyProfile.appendChild(uid);
			//year
			Element year = doc.createElement("year");
			year.appendChild(doc.createTextNode(hubFile.getYear() + ""));
			studyProfile.appendChild(year);
			//semester
			Element semester = doc.createElement("semester");
			semester.appendChild(doc.createTextNode(hubFile.getSemester() + ""));
			studyProfile.appendChild(semester);
			//modules
			Element modules = doc.createElement("modules");
			studyProfile.appendChild(modules);

			//module
			Element module = doc.createElement("module");
			modules.appendChild(module);
			Element moduleName = doc.createElement("name");
			moduleName.appendChild(doc.createTextNode("Example Module"));
			Element moduleDetails = doc.createElement("details");
			moduleDetails.appendChild(doc.createTextNode("This is an example module."));
			Element moduleVersion = doc.createElement("version");
			moduleVersion.appendChild(doc.createTextNode("1"));
			Element moduleUiD = doc.createElement("uid");
			moduleUiD.appendChild(doc.createTextNode("examp_exam01"));
			Element moduleOrganiser = doc.createElement("organiser");
			moduleOrganiser.appendChild(doc.createTextNode("PERSON01"));
			Element moduleCode = doc.createElement("moduleCode");
			moduleCode.appendChild(doc.createTextNode("EXAMP-001"));
			Element moduleTimetable = doc.createElement("timetable");
			Element timetableEvent = doc.createElement("timetableEvent");
			moduleTimetable.appendChild(timetableEvent);
			Element tteName = doc.createElement("name");
			tteName.appendChild(doc.createTextNode("Java 1"));
			timetableEvent.appendChild(tteName);
			Element tteDetails = doc.createElement("details");
			tteDetails.appendChild(doc.createTextNode(""));
			timetableEvent.appendChild(tteDetails);
			Element tteVersion = doc.createElement("version");
			tteVersion.appendChild(doc.createTextNode("1"));
			timetableEvent.appendChild(tteVersion);
			Element tteUiD = doc.createElement("uid");
			tteUiD.appendChild(doc.createTextNode("EXAMP-001-LEC"));
			timetableEvent.appendChild(tteUiD);
			Element tteDate = doc.createElement("date");
			tteDate.appendChild(doc.createTextNode("02/02/2020T09:45:00Z"));
			timetableEvent.appendChild(tteDate);
			Element tteRoom = doc.createElement("room");
			tteRoom.appendChild(doc.createTextNode("russEng"));
			timetableEvent.appendChild(tteRoom);
			Element tteLecturer = doc.createElement("lecturer");
			tteLecturer.appendChild(doc.createTextNode("PERSON01"));
			timetableEvent.appendChild(tteLecturer);
			Element tteTimetableEventType = doc.createElement("timetableEventType");
			tteTimetableEventType.appendChild(doc.createTextNode("LECTURE"));
			timetableEvent.appendChild(tteTimetableEventType);
			Element tteDuration = doc.createElement("duration");
			tteDuration.appendChild(doc.createTextNode("120"));
			timetableEvent.appendChild(tteDuration);

			Element moduleAssignments = doc.createElement("assignments");
			module.appendChild(moduleName);
			module.appendChild(moduleDetails);
			module.appendChild(moduleVersion);
			module.appendChild(moduleUiD);
			module.appendChild(moduleOrganiser);
			module.appendChild(moduleCode);
			module.appendChild(moduleTimetable);
			module.appendChild(moduleAssignments);

			//Assignments
			Element exam = doc.createElement("exam");
			moduleAssignments.appendChild(exam);
			Element examName = doc.createElement("name");
			examName.appendChild(doc.createTextNode("Example Exam"));
			exam.appendChild(examName);
			Element examDetails = doc.createElement("details");
			examDetails.appendChild(doc.createTextNode("This is an example exam."));
			exam.appendChild(examDetails);
			Element examVersion = doc.createElement("version");
			examVersion.appendChild(doc.createTextNode("1"));
			exam.appendChild(examVersion);
			Element examUiD = doc.createElement("uid");
			examUiD.appendChild(doc.createTextNode("examp_exam01"));
			exam.appendChild(examUiD);
			Element examResit = doc.createElement("resit");
			exam.appendChild(examResit);
			Element examTimeslot = doc.createElement("timeslot");
			exam.appendChild(examTimeslot);
			Element timeslotName = doc.createElement("name");
			timeslotName.appendChild(doc.createTextNode("Example Exam"));
			examTimeslot.appendChild(timeslotName);
			Element timeslotDetails = doc.createElement("details");
			timeslotDetails.appendChild(doc.createTextNode("Date of the exam"));
			examTimeslot.appendChild(timeslotDetails);
			Element timeslotVersion = doc.createElement("version");
			timeslotVersion.appendChild(doc.createTextNode("1"));
			examTimeslot.appendChild(timeslotVersion);
			Element timeslotUiD = doc.createElement("uid");
			timeslotUiD.appendChild(doc.createTextNode("EXAMPLE_EXAM_1_TIMESLOT"));
			examTimeslot.appendChild(timeslotUiD);
			Element timeslotDate = doc.createElement("date");
			timeslotDate.appendChild(doc.createTextNode("02/02/2020T09:45:00Z"));
			examTimeslot.appendChild(timeslotDate);
			Element timeslotRoom = doc.createElement("room");
			timeslotRoom.appendChild(doc.createTextNode("russEng"));
			examTimeslot.appendChild(timeslotRoom);
			Element timeslotDuration = doc.createElement("duration");
			timeslotDuration.appendChild(doc.createTextNode("120"));
			examTimeslot.appendChild(timeslotDuration);
			Element examWeighting = doc.createElement("weighting");
			examWeighting.appendChild(doc.createTextNode("25"));
			exam.appendChild(examWeighting);
			Element examSetBy = doc.createElement("setBy");
			examSetBy.appendChild(doc.createTextNode("PERSON01"));
			exam.appendChild(examSetBy);
			Element examMarkedBy = doc.createElement("markedBy");
			examMarkedBy.appendChild(doc.createTextNode("PERSON01"));
			exam.appendChild(examMarkedBy);
			Element examReviewedBy = doc.createElement("reviewedBy");
			examReviewedBy.appendChild(doc.createTextNode("PERSON01"));
			exam.appendChild(examReviewedBy);
			Element examMarks = doc.createElement("marks");
			examMarks.appendChild(doc.createTextNode("120"));
			exam.appendChild(examMarks);

			Element coursework = doc.createElement("coursework");
			moduleAssignments.appendChild(coursework);
			Element cwName = doc.createElement("name");
			cwName.appendChild(doc.createTextNode("Example Essay"));
			coursework.appendChild(cwName);
			Element cwDetails = doc.createElement("details");
			cwDetails.appendChild(doc.createTextNode("This is example coursework."));
			coursework.appendChild(cwDetails);
			Element cwVersion = doc.createElement("version");
			cwVersion.appendChild(doc.createTextNode("1"));
			coursework.appendChild(cwVersion);
			Element cwUiD = doc.createElement("uid");
			cwUiD.appendChild(doc.createTextNode("examp_cw01"));
			coursework.appendChild(cwUiD);
			Element cwStartDate = doc.createElement("startDate");
			coursework.appendChild(cwStartDate);
			Element startDateName = doc.createElement("name");
			startDateName.appendChild(doc.createTextNode("Start Date"));
			cwStartDate.appendChild(startDateName);
			Element startDateDetails = doc.createElement("details");
			startDateDetails.appendChild(doc.createTextNode("Start Date for the coursework."));
			cwStartDate.appendChild(startDateDetails);
			Element startDateVersion = doc.createElement("version");
			startDateVersion.appendChild(doc.createTextNode("1"));
			cwStartDate.appendChild(startDateVersion);
			Element startDateUiD = doc.createElement("uid");
			startDateUiD.appendChild(doc.createTextNode("examp_cw01_start"));
			cwStartDate.appendChild(startDateUiD);
			Element startDateDate = doc.createElement("date");
			startDateDate.appendChild(doc.createTextNode("02/02/2020T15:00:00Z"));
			cwStartDate.appendChild(startDateDate);
			Element cwDeadline = doc.createElement("deadline");
			coursework.appendChild(cwDeadline);
			Element deadlineName = doc.createElement("name");
			deadlineName.appendChild(doc.createTextNode("Deadline"));
			cwDeadline.appendChild(deadlineName);
			Element deadlineDetails = doc.createElement("details");
			deadlineDetails.appendChild(doc.createTextNode("End Date for the coursework."));
			cwDeadline.appendChild(deadlineDetails);
			Element deadlineVersion = doc.createElement("version");
			deadlineVersion.appendChild(doc.createTextNode("1"));
			cwDeadline.appendChild(deadlineVersion);
			Element deadlineUiD = doc.createElement("uid");
			deadlineUiD.appendChild(doc.createTextNode("examp_cw01_end"));
			cwDeadline.appendChild(deadlineUiD);
			Element deadlineDate = doc.createElement("date");
			deadlineDate.appendChild(doc.createTextNode("03/03/2020T15:00:00Z"));
			cwDeadline.appendChild(deadlineDate);
			Element cwExtensions = doc.createElement("extensions");
			coursework.appendChild(cwExtensions);
			Element cwWeighting = doc.createElement("weighting");
			cwWeighting.appendChild(doc.createTextNode("30"));
			coursework.appendChild(cwWeighting);
			Element cwSetBy = doc.createElement("setBy");
			cwSetBy.appendChild(doc.createTextNode("PERSON01"));
			coursework.appendChild(cwSetBy);
			Element cwMarkedBy = doc.createElement("markedBy");
			cwMarkedBy.appendChild(doc.createTextNode("PERSON01"));
			coursework.appendChild(cwMarkedBy);
			Element cwReviewedBy = doc.createElement("reviewedBy");
			cwReviewedBy.appendChild(doc.createTextNode("PERSON01"));
			coursework.appendChild(cwReviewedBy);
			Element cwMarks = doc.createElement("marks");
			cwMarks.appendChild(doc.createTextNode("100"));
			coursework.appendChild(cwMarks);

			//write to XML file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{https://urldefense.proofpoint.com/v2/url?u=http-3A__xml.apache.org_xslt-257Dindent-2Damount&d=DwIGAg&c=3buyMx9JlH1z22L_G5pM28wz_Ru6WjhVHwo-vpeS0Gk&r=NJsaOkIcsbD11vowtkPWAH9wBPv9p9_R7PfbWk-WlRg&m=5Ls_Dbj5OtiJVEqbnNDOYixWoJu7aubauuPcc7dvm9M&s=7xzzWlao2NPDELNaj-bX1wxn4MXR2iO9ZUFPQLB0ceI&e= ", "4");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(".\\StudyProfiles\\"
					+ hubFile.getSemesterUId() + ".xml"));

			transformer.transform(source, result);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

	}

}
