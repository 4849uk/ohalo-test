package uk.zectech.dictionary.api.request;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test cases for the DictionaryRequest class.
 * 
 * @author Michael Conway
 *
 */
public class DictionaryRequestTest extends RequestModelTestBase<DictionaryRequest> {

	/**
	 * Set up a valid request.
	 */
	@BeforeEach
	public void setUpRequest() {
		request = new DictionaryRequest();
		request.setCaseSensitive(Boolean.TRUE);
		request.setEntries(new ArrayList<>());
		request.getEntries().add("First");
	}

	/**
	 * A valid requedst.
	 */
	@Test
	public void validRequest() {
		assertViolations(0);
	}

	/**
	 * Case sensitive set to null.
	 */
	@Test
	public void caseSensitiveNull() {
		request.setCaseSensitive(null);
		assertViolations(1, "is_case_sensitive cannot be null");
	}
	
	/**
	 * Entries set to null.
	 */
	@Test
	public void entriesNull() {
		request.setEntries(null);
		assertViolations(1, "entities cannot be empty");
	}
	
	/**
	 * Entries empty.
	 */
	@Test
	public void entriesEmpty() {
		request.getEntries().clear();
		assertViolations(1, "entities cannot be empty");
	}
	
	/**
	 * Single entry is null.
	 */
	@Test
	public void entryNull() {
		request.getEntries().clear();
		String string = null;
		request.getEntries().add(string);
		assertViolations(1, "entry cannot be empty");
	}

	/**
	 * Single entry is null.
	 */
	@Test
	public void entryEmpty() {
		request.getEntries().clear();
		request.getEntries().add("");
		assertViolations(1, "entry cannot be empty");
	}
	
	/**
	 * Single entry is null.
	 */
	@Test
	public void entryInvalidChars() {
		request.getEntries().clear();
		request.getEntries().add("invalid$");
		assertViolations(1, "entry can contain only alphas, hyphen or space");
	}
}
