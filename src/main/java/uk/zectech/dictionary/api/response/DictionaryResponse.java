package uk.zectech.dictionary.api.response;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Dictionary response model.
 * 
 * @author Michael Conway
 *
 */
public class DictionaryResponse {

	/** The id. */
	private Long id;

	/** The case sensitive indicator. */
	@JsonProperty("is_case_sensitive")
	private boolean caseSensitive;

	/** The entries. */
	private Collection<String> entries;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the caseSensitive
	 */
	public boolean isCaseSensitive() {
		return caseSensitive;
	}

	/**
	 * @param caseSensitive the caseSensitive to set
	 */
	public void setCaseSensitive(boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}

	/**
	 * @return the entries
	 */
	public Collection<String> getEntries() {
		return entries;
	}

	/**
	 * @param entries the entries to set
	 */
	public void setEntries(Collection<String> entries) {
		this.entries = entries;
	}

}
