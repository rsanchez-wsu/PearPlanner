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

/**This is the main JSON response body.
 * @author zooko
 *
 */
public class ResponseUsaJobs {
	private String languageCode;
	private SearchParameters searchParameters;
	private SearchResult searchResult;

	/**Default constructor.
	 */
	public ResponseUsaJobs() {
	}

	/**Javadoc.
	 @param languageCode language code
	 * @param searchParameters search parameters
	 * @param searchResult search result
	 */
	@JsonCreator
	public ResponseUsaJobs(
			@JsonProperty ("LanguageCode") String languageCode,
			@JsonProperty ("SearchParameters") SearchParameters searchParameters,
			@JsonProperty ("SearchResult") SearchResult searchResult) {
		this.languageCode = languageCode;
		this.searchParameters = searchParameters;
		this.searchResult = searchResult;
	}

	/**Getter.
	 * @return the languageCode
	 */
	public String getLanguageCode() {
		return languageCode;
	}

	/**Setter.
	 * @param languageCode the language code
	 */
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	/**Getter.
	 * @return the searchParameters
	 */
	public SearchParameters getSearchParameters() {
		return searchParameters;
	}

	/**Setter.
	 * @param searchParameters the searchParameters to set
	 */
	public void setSearchParameters(SearchParameters searchParameters) {
		this.searchParameters = searchParameters;
	}

	/**Getter.
	 * @return the searchResult
	 */
	public SearchResult getSearchResult() {
		return searchResult;
	}

	/** Setter.
	 * @param searchResult the searchResult to set
	 */
	public void setSearchResult(SearchResult searchResult) {
		this.searchResult = searchResult;
	}


}
