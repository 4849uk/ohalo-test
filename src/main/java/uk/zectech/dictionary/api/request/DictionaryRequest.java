package uk.zectech.dictionary.api.request;

import java.util.Collection;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Dictionary request model.
 * 
 * @author Michael Conway
 *
 */
public class DictionaryRequest {

	/** The case sensitive indicator. */
	@JsonProperty("is_case_sensitive")
	@NotNull(message = "is_case_sensitive cannot be null")
	private Boolean caseSensitive;

	/** The entries. */
	@NotEmpty(message = "entities cannot be empty")
	private Collection<@NotEmpty(message = "entry cannot be empty") @Pattern(regexp = "^[a-zA-Z- ]*$", message = "entry can contain only alphas, hyphen or space") String> entries;

	/**
	 * @return the caseSensitive
	 */
	public Boolean isCaseSensitive() {
		return caseSensitive;
	}

	/**
	 * @param caseSensitive the caseSensitive to set
	 */
	public void setCaseSensitive(Boolean caseSensitive) {
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

	/**
	 * To string conversion method.
	 */
	@Override
	public String toString() {
		return "DictionaryRequest [caseSensitive=" + caseSensitive + ", entries=" + entries + "]";
	}

}
