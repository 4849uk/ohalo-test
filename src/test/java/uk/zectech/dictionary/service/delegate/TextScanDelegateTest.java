package uk.zectech.dictionary.service.delegate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import uk.zectech.dictionary.api.response.DictionaryScanResponse;
import uk.zectech.dictionary.domain.Dictionary;

/**
 * Test cases for the TextScanDelegate class.
 * 
 * @author Michael Conway
 *
 */
public class TextScanDelegateTest {

	/** The text scan delegate. */
	private TextScanDelegate textScanDelegate = new TextScanDelegate();

	/**
	 * Show that a single match is found in a case sensitive dictionary with one
	 * entry.
	 */
	@Test
	public void shouldFindSingleMatchFromSingleEntryDictionaryCaseSensitive() {
		Dictionary dictionary = new Dictionary();
		dictionary.setEntries(new HashSet<>());
		dictionary.getEntries().add("First");
		dictionary.setCaseSensitive(true);

		List<DictionaryScanResponse> results = textScanDelegate.scanText(dictionary,
				"The First order of business is always first done");

		assertNotNull(results, "results should be non null");
		assertEquals(1, results.size(), "results size should mathc expected value");

		DictionaryScanResponse result = results.get(0);

		assertNotNull(result, "result should be non null");
		assertEquals(4, result.getStart(), "start should match expected value");
		assertEquals(9, result.getEnd(), "start should match expected value");
		assertEquals("First", result.getEntry(), "entry should match expected value");
	}

	/**
	 * Show that zero matches are found in a case sensitive dictionary with one
	 * entry.
	 */
	@Test
	public void shouldFindZeroMatchesFromSingleEntryDictionaryCaseSensitive() {
		Dictionary dictionary = new Dictionary();
		dictionary.setEntries(new HashSet<>());
		dictionary.getEntries().add("First");
		dictionary.setCaseSensitive(true);

		List<DictionaryScanResponse> results = textScanDelegate.scanText(dictionary,
				"The first order of business is always first done");

		assertNotNull(results, "results should be non null");
		assertEquals(0, results.size(), "results size should mathc expected value");
	}

	/**
	 * Show that a single match is found in a case sensitive dictionary with one
	 * entry.
	 */
	@Test
	public void shouldFindMultipleMatchesFromSingleEntryDictionaryCaseSensitive() {
		Dictionary dictionary = new Dictionary();
		dictionary.setEntries(new HashSet<>());
		dictionary.getEntries().add("First");
		dictionary.setCaseSensitive(true);

		List<DictionaryScanResponse> results = textScanDelegate.scanText(dictionary,
				"The First order of business is always First done and first completed");

		assertNotNull(results, "results should be non null");
		assertEquals(2, results.size(), "results size should mathc expected value");

		DictionaryScanResponse result = results.get(0);

		assertNotNull(result, "result should be non null");
		assertEquals(4, result.getStart(), "start should match expected value");
		assertEquals(9, result.getEnd(), "start should match expected value");
		assertEquals("First", result.getEntry(), "entry should match expected value");

		result = results.get(1);

		assertNotNull(result, "result should be non null");
		assertEquals(38, result.getStart(), "start should match expected value");
		assertEquals(43, result.getEnd(), "start should match expected value");
		assertEquals("First", result.getEntry(), "entry should match expected value");
	}

	/**
	 * Show that zero matches are found in a case sensitive dictionary with multiple
	 * entries.
	 */
	@Test
	public void shouldFindZeroMatchesFromMultipleEntryDictionaryCaseSensitive() {
		Dictionary dictionary = new Dictionary();
		dictionary.setEntries(new HashSet<>());
		dictionary.getEntries().add("Quick");
		dictionary.getEntries().add("Fox");
		dictionary.setCaseSensitive(true);

		List<DictionaryScanResponse> results = textScanDelegate.scanText(dictionary,
				"The quick brown fox jumped over the lazy dog");

		assertNotNull(results, "results should be non null");
		assertEquals(0, results.size(), "results size should mathc expected value");
	}

	/**
	 * Show that one match are found in a case sensitive dictionary with multiple
	 * entries.
	 */
	@Test
	public void shouldFindOneMatchFromMultipleEntryDictionaryCaseSensitive() {
		Dictionary dictionary = new Dictionary();
		dictionary.setEntries(new HashSet<>());
		dictionary.getEntries().add("quick");
		dictionary.getEntries().add("Fox");
		dictionary.setCaseSensitive(true);

		List<DictionaryScanResponse> results = textScanDelegate.scanText(dictionary,
				"The quick brown fox jumped over the lazy dog");

		assertNotNull(results, "results should be non null");
		assertEquals(1, results.size(), "results size should mathc expected value");

		DictionaryScanResponse result = results.get(0);

		assertNotNull(result, "result should be non null");
		assertEquals(4, result.getStart(), "start should match expected value");
		assertEquals(9, result.getEnd(), "start should match expected value");
		assertEquals("quick", result.getEntry(), "entry should match expected value");
	}

	/**
	 * Show that multiple matches are found in a case sensitive dictionary with
	 * multiple entries.
	 */
	@Test
	public void shouldFindMultipleMatchesFromMultipleEntryDictionaryCaseSensitive() {
		Dictionary dictionary = new Dictionary();
		dictionary.setEntries(new HashSet<>());
		dictionary.getEntries().add("quick");
		dictionary.getEntries().add("fox");
		dictionary.setCaseSensitive(true);

		List<DictionaryScanResponse> results = textScanDelegate.scanText(dictionary,
				"The quick brown fox jumped over the lazy dog");

		assertNotNull(results, "results should be non null");
		assertEquals(2, results.size(), "results size should mathc expected value");

		DictionaryScanResponse result = results.get(0);

		assertNotNull(result, "result should be non null");
		assertEquals(4, result.getStart(), "start should match expected value");
		assertEquals(9, result.getEnd(), "start should match expected value");
		assertEquals("quick", result.getEntry(), "entry should match expected value");

		result = results.get(1);

		assertNotNull(result, "result should be non null");
		assertEquals(16, result.getStart(), "start should match expected value");
		assertEquals(19, result.getEnd(), "start should match expected value");
		assertEquals("fox", result.getEntry(), "entry should match expected value");
	}

	/***************************************/

	/**
	 * Show that a single match is found in a case insensitive dictionary with one
	 * entry.
	 */
	@Test
	public void shouldFindSingleMatchFromSingleEntryDictionaryCaseInsensitive() {
		Dictionary dictionary = new Dictionary();
		dictionary.setEntries(new HashSet<>());
		dictionary.getEntries().add("first");
		dictionary.setCaseSensitive(false);

		List<DictionaryScanResponse> results = textScanDelegate.scanText(dictionary,
				"The First order of business is always done");

		assertNotNull(results, "results should be non null");
		assertEquals(1, results.size(), "results size should mathc expected value");

		DictionaryScanResponse result = results.get(0);

		assertNotNull(result, "result should be non null");
		assertEquals(4, result.getStart(), "start should match expected value");
		assertEquals(9, result.getEnd(), "start should match expected value");
		assertEquals("First", result.getEntry(), "entry should match expected value");
	}

	/**
	 * Show that zero matches are found in a case insensitive dictionary with one
	 * entry.
	 */
	@Test
	public void shouldFindZeroMatchesFromSingleEntryDictionaryCaseInsensitive() {
		Dictionary dictionary = new Dictionary();
		dictionary.setEntries(new HashSet<>());
		dictionary.getEntries().add("First");
		dictionary.setCaseSensitive(false);

		List<DictionaryScanResponse> results = textScanDelegate.scanText(dictionary,
				"The quick brown fox jumped over the lazy dog");

		assertNotNull(results, "results should be non null");
		assertEquals(0, results.size(), "results size should mathc expected value");
	}

	/**
	 * Show that a single match is found in a case insensitive dictionary with one
	 * entry.
	 */
	@Test
	public void shouldFindMultipleMatchesFromSingleEntryDictionaryCaseInsensitive() {
		Dictionary dictionary = new Dictionary();
		dictionary.setEntries(new HashSet<>());
		dictionary.getEntries().add("First");
		dictionary.setCaseSensitive(false);

		List<DictionaryScanResponse> results = textScanDelegate.scanText(dictionary,
				"The First order of business is always First done and first completed");

		assertNotNull(results, "results should be non null");
		assertEquals(3, results.size(), "results size should mathc expected value");

		DictionaryScanResponse result = results.get(0);

		assertNotNull(result, "result should be non null");
		assertEquals(4, result.getStart(), "start should match expected value");
		assertEquals(9, result.getEnd(), "start should match expected value");
		assertEquals("First", result.getEntry(), "entry should match expected value");

		result = results.get(1);

		assertNotNull(result, "result should be non null");
		assertEquals(38, result.getStart(), "start should match expected value");
		assertEquals(43, result.getEnd(), "start should match expected value");
		assertEquals("First", result.getEntry(), "entry should match expected value");
		
		result = results.get(2);

		assertNotNull(result, "result should be non null");
		assertEquals(53, result.getStart(), "start should match expected value");
		assertEquals(58, result.getEnd(), "start should match expected value");
		assertEquals("first", result.getEntry(), "entry should match expected value");
	}

	/**
	 * Show that zero matches are found in a case insensitive dictionary with multiple
	 * entries.
	 */
	@Test
	public void shouldFindZeroMatchesFromMultipleEntryDictionaryCaseInsensitive() {
		Dictionary dictionary = new Dictionary();
		dictionary.setEntries(new HashSet<>());
		dictionary.getEntries().add("slow");
		dictionary.getEntries().add("badger");
		dictionary.setCaseSensitive(false);

		List<DictionaryScanResponse> results = textScanDelegate.scanText(dictionary,
				"The quick brown fox jumped over the lazy dog");

		assertNotNull(results, "results should be non null");
		assertEquals(0, results.size(), "results size should mathc expected value");
	}

	/**
	 * Show that one match are found in a case insensitive dictionary with multiple
	 * entries.
	 */
	@Test
	public void shouldFindOneMatchFromMultipleEntryDictionaryCaseInensitive() {
		Dictionary dictionary = new Dictionary();
		dictionary.setEntries(new HashSet<>());
		dictionary.getEntries().add("QUICK");
		dictionary.getEntries().add("badger");
		dictionary.setCaseSensitive(false);

		List<DictionaryScanResponse> results = textScanDelegate.scanText(dictionary,
				"The quick brown fox jumped over the lazy dog");

		assertNotNull(results, "results should be non null");
		assertEquals(1, results.size(), "results size should mathc expected value");

		DictionaryScanResponse result = results.get(0);

		assertNotNull(result, "result should be non null");
		assertEquals(4, result.getStart(), "start should match expected value");
		assertEquals(9, result.getEnd(), "start should match expected value");
		assertEquals("quick", result.getEntry(), "entry should match expected value");
	}

	/**
	 * Show that multiple matches are found in a case insensitive dictionary with
	 * multiple entries.
	 */
	@Test
	public void shouldFindMultipleMatchesFromMultipleEntryDictionaryCaseInsensitive() {
		Dictionary dictionary = new Dictionary();
		dictionary.setEntries(new HashSet<>());
		dictionary.getEntries().add("QuicK");
		dictionary.getEntries().add("FOX");
		dictionary.setCaseSensitive(false);

		List<DictionaryScanResponse> results = textScanDelegate.scanText(dictionary,
				"The quick brown fox jumped over the lazy dog");

		assertNotNull(results, "results should be non null");
		assertEquals(2, results.size(), "results size should mathc expected value");

		DictionaryScanResponse result = results.get(0);

		assertNotNull(result, "result should be non null");
		assertEquals(4, result.getStart(), "start should match expected value");
		assertEquals(9, result.getEnd(), "start should match expected value");
		assertEquals("quick", result.getEntry(), "entry should match expected value");

		result = results.get(1);

		assertNotNull(result, "result should be non null");
		assertEquals(16, result.getStart(), "start should match expected value");
		assertEquals(19, result.getEnd(), "start should match expected value");
		assertEquals("fox", result.getEntry(), "entry should match expected value");
	}
	
	/**
	 * Show that a word placed at the start of the text to be scanned will be matched.
	 */
	@Test
	public void shouldMatchWordAtStartOfText() {
		Dictionary dictionary = new Dictionary();
		dictionary.setEntries(new HashSet<>());
		dictionary.getEntries().add("QUICK");
		dictionary.setCaseSensitive(false);

		List<DictionaryScanResponse> results = textScanDelegate.scanText(dictionary,
				"Quick brown fox jumped over the lazy dog");

		assertNotNull(results, "results should be non null");
		assertEquals(1, results.size(), "results size should mathc expected value");

		DictionaryScanResponse result = results.get(0);

		assertNotNull(result, "result should be non null");
		assertEquals(0, result.getStart(), "start should match expected value");
		assertEquals(5, result.getEnd(), "start should match expected value");
		assertEquals("Quick", result.getEntry(), "entry should match expected value");
	}
	
	/**
	 * Show that a word placed at the start of the text to be scanned will be matched.
	 */
	@Test
	public void shouldMatchWordAtEndOfText() {
		Dictionary dictionary = new Dictionary();
		dictionary.setEntries(new HashSet<>());
		dictionary.getEntries().add("QUICK");
		dictionary.setCaseSensitive(false);

		List<DictionaryScanResponse> results = textScanDelegate.scanText(dictionary,
				"The brown fox is not so quick");

		assertNotNull(results, "results should be non null");
		assertEquals(1, results.size(), "results size should mathc expected value");

		DictionaryScanResponse result = results.get(0);

		assertNotNull(result, "result should be non null");
		assertEquals(24, result.getStart(), "start should match expected value");
		assertEquals(29, result.getEnd(), "start should match expected value");
		assertEquals("quick", result.getEntry(), "entry should match expected value");
	}
	
	/**
	 * Show that a multiple word dictionary entry can be matched.
	 */
	@Test
	public void shouldMatchMultipleWordEntry() {
		Dictionary dictionary = new Dictionary();
		dictionary.setEntries(new HashSet<>());
		dictionary.getEntries().add("brown fox");
		dictionary.setCaseSensitive(false);

		List<DictionaryScanResponse> results = textScanDelegate.scanText(dictionary,
				"The brown fox is not so quick");

		assertNotNull(results, "results should be non null");
		assertEquals(1, results.size(), "results size should mathc expected value");

		DictionaryScanResponse result = results.get(0);

		assertNotNull(result, "result should be non null");
		assertEquals(4, result.getStart(), "start should match expected value");
		assertEquals(13, result.getEnd(), "start should match expected value");
		assertEquals("brown fox", result.getEntry(), "entry should match expected value");
	}
	
	/**
	 * Show that a hyphenated word dictionary entry can be matched.
	 */
	@Test
	public void shouldMatchHyphenatedWordEntry() {
		Dictionary dictionary = new Dictionary();
		dictionary.setEntries(new HashSet<>());
		dictionary.getEntries().add("Slow-quick");
		dictionary.setCaseSensitive(false);

		List<DictionaryScanResponse> results = textScanDelegate.scanText(dictionary,
				"The brown fox is slow-quick");

		assertNotNull(results, "results should be non null");
		assertEquals(1, results.size(), "results size should mathc expected value");

		DictionaryScanResponse result = results.get(0);

		assertNotNull(result, "result should be non null");
		assertEquals(17, result.getStart(), "start should match expected value");
		assertEquals(27, result.getEnd(), "start should match expected value");
		assertEquals("slow-quick", result.getEntry(), "entry should match expected value");
	}
	
}
