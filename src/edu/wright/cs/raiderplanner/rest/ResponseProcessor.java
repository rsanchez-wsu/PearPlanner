/*
 * Copyright (C) 2019
 *
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

package edu.wright.cs.raiderplanner.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

/** Class that contains methods for viewer to call.
 * @author zooko
 *
 */

public class ResponseProcessor {

	/** Method that connects to USAJobs api, makes request, and returns as StringBuilder.
	 * @author zooko
	 */
	public StringBuilder requestFromEndpoint() throws IOException {
		URL url = new URL("https://data.usajobs.gov/api/search?Keyword=Software&LocationName=Dayton");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestProperty("Authorization-Key", "J30THoB+Rjb2iHSuhXkuoGY4ZPSlzO1RVzRUrY/AlYQ=");
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		StringBuilder build = new StringBuilder();
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			build.append(inputLine);
		}
		in.close();
		return build;
	}


	/** Method to return three recent Software jobs in Dayton.
	 * @author zooko
	 * @return 2-d ArrayList of three job positions
	 *      Indexing:
	 *      [0] : Job title
	 *      [1] : Job location
	 *      [2] : Company
	 *      [3] : USA Jobs URL
	 * 		  Additional fields from the responseNode may be added.
	 * 		  For available fields see documentation at: https://developer.usajobs.gov/API-Reference/GET-api-Search
	 */
	public ArrayList retrievePositionTitles() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
		ArrayList matches = new ArrayList<>();
		try {
			StringBuilder build = requestFromEndpoint();
			JsonNode responseNode = new ObjectMapper().readTree(build.toString());
			JsonNode responseResultNode = responseNode.path("SearchResult");
			ResponseUsaJobs responseJobs = new ResponseUsaJobs();
			responseJobs.setLanguageCode(responseNode.get("LanguageCode").textValue());
			ArrayNode itemArray = (ArrayNode) responseResultNode.path("SearchResultItems");
			JsonNode positionNode = responseResultNode.path("MatchedObjectID");
			System.out.println(positionNode);
			Iterator<JsonNode> itemIterator = itemArray.elements();
			int counter = 0;
			JsonNode position = itemArray.findValue("PositionTitle");
			JsonNode positionLocation = itemArray.findValue("PositionLocation");
			String organization = itemArray.findValue("OrganizationName").toString();
			ArrayList positionList = new ArrayList<>();
			ArrayList locationList = new ArrayList<>();
			ArrayList organizationList = new ArrayList<>();
			ArrayList positionUri = new ArrayList<>();
			while (itemIterator.hasNext() & counter < 3) {
				JsonNode item = itemIterator.next();
				positionList.add(item.findValue("PositionTitle").toString());
				locationList.add(item.findValue("CityName").toString());
				organizationList.add(item.findValue("OrganizationName").toString());
				positionUri.add(item.findValue("PositionURI").toString());
				counter++;
			}
			matches.add(positionList);
			matches.add(locationList);
			matches.add(organizationList);
			matches.add(positionUri);
			return matches;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return matches;
	}

}
