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

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

/**This is test class for restful services.
 * @author zooko
 *
 */
public class RestResponseAndResultEntityTest {

	@Test
	public void responseEntityIsReceived() throws IOException {
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
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
		try {
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
//			System.out.println(positionList.get(0));
//			System.out.println(positionList.get(1));
//			System.out.println(positionList.get(2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
