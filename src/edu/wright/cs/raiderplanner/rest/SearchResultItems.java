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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**The Search Result Item entity contains information relevant to individual jobs openings.
 * @author zooko
 *
 */
public class SearchResultItems {
	private String matchedObjectId;
	private MatchedObjectDescriptor matchedObjectDescriptor;
	private double relevanceRank;

	/** Default constructor.
	 */
	public SearchResultItems() {

	}

	/** Field constructor.
	 * @param matchedObjectId job id
	 * @param matchedObjectDescriptor job description items
	 * @param relevanceRank job relevance rank
	 */
	@JsonCreator
	public SearchResultItems(
			@JsonProperty("MatchedObjectId") String matchedObjectId,
			@JsonProperty("MatchedObjectDescriptor")MatchedObjectDescriptor matchedObjectDescriptor,
			@JsonProperty("RelevanceRank") double relevanceRank) {
		this.matchedObjectId = matchedObjectId;
		this.matchedObjectDescriptor = matchedObjectDescriptor;
		this.relevanceRank = relevanceRank;
	}

	/** Getter/Setter.
	 * @return the matchedObjectId
	 */
	public String getMatchedObjectId() {
		return matchedObjectId;
	}

	/** Getter/Setter.
	 * @param matchedObjectId the matchedObjectId to set
	 */
	public void setMatchedObjectId(String matchedObjectId) {
		this.matchedObjectId = matchedObjectId;
	}

	/** Getter/Setter.
	 * @return the matchedObjectDescriptor
	 */
	public MatchedObjectDescriptor getMatchedObjectDescriptor() {
		return matchedObjectDescriptor;
	}

	/** Getter/Setter.
	 * @param matchedObjectDescriptor the matchedObjectDescriptor to set
	 */
	public void setMatchedObjectDescriptor(MatchedObjectDescriptor matchedObjectDescriptor) {
		this.matchedObjectDescriptor = matchedObjectDescriptor;
	}

	/** Getter/Setter.
	 * @return the relevanceRank
	 */
	public double getRelevanceRank() {
		return relevanceRank;
	}

	/** Getter/Setter.
	 * @param relevanceRank the relevanceRank to set
	 */
	public void setRelevanceRank(double relevanceRank) {
		this.relevanceRank = relevanceRank;
	}


}
