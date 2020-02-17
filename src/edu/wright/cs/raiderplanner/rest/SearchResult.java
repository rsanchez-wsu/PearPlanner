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

/**Search Result contains array of job results from search parameters.
 * @author zooko
 *
 */
public class SearchResult {
	private String searchResultCount;
	private String searchResultCountAll;
	private SearchResultItems searchResultItems;
	private UserArea userArea;

	/**Default constructor.
	 */
	public SearchResult() {

	}

	/**Field constructor.
	 * @param searchResultCount search result count
	 * @param searchResultCountAll search result count all
	 * @param searchResultItems search result items
	 * @param userArea user area
	 */
	@JsonCreator
	public SearchResult(
			@JsonProperty("SearchResultCount")String searchResultCount,
			@JsonProperty("SearchResultCountAll") String searchResultCountAll,
			@JsonProperty("SearchResultItems") SearchResultItems searchResultItems,
			@JsonProperty("UserArea") UserArea userArea) {
		this.searchResultCount = searchResultCount;
		this.searchResultCountAll = searchResultCountAll;
		this.userArea = userArea;
	}

	/** Getter/setter.
	 * @return the searchResultItems
	 */
	public SearchResultItems getSearchResultItems() {
		return searchResultItems;
	}

	/** Getter/setter.
	 * @param searchResultItems the searchResultItems to set
	 */
	public void setSearchResultItems(SearchResultItems searchResultItems) {
		this.searchResultItems = searchResultItems;
	}

	/** Getter/Setter.
	 * @return the searchResultCount
	 */
	public String getSearchResultCount() {
		return searchResultCount;
	}

	/** Getter/Setter.
	 * @param searchResultCount the searchResultCount to set
	 */
	public void setSearchResultCount(String searchResultCount) {
		this.searchResultCount = searchResultCount;
	}

	/** Getter/Setter.
	 * @return the searchResultCountAll
	 */
	public String getSearchResultCountAll() {
		return searchResultCountAll;
	}

	/** Getter/Setter.
	 * @param searchResultCountAll the searchResultCountAll to set
	 */
	public void setSearchResultCountAll(String searchResultCountAll) {
		this.searchResultCountAll = searchResultCountAll;
	}

	/** Getter/Setter.
	 * @return the userArea
	 */
	public UserArea getUserArea() {
		return userArea;
	}

	/** Getter/Setter.
	 * @param userArea the userArea to set
	 */
	public void setUserArea(UserArea userArea) {
		this.userArea = userArea;
	}

}
