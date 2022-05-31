package uk.zectech.dictionary.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import uk.zectech.dictionary.domain.Dictionary;

/**
 * Test cases for the DictionaryRepository calss.
 * 
 * @author Michael Conway
 *
 */
@SpringBootTest
public class DictionaryRepositoryTest {

	@Autowired
	public DictionaryRepository dictionaryRepository;

	/**
	 * Clear down repository before each test.
	 */
	@BeforeEach
	public void clearRepositoryData() {
		dictionaryRepository.deleteAll();
		assertEquals(dictionaryRepository.count(), 0, "dicrionary repository should be empty");
	}

	/**
	 * Show that a dictionary entity can be successfully saved and retrieved.
	 */
	@Test
	public void shouldSuccessfullyGetDictionary() {
		Dictionary dictionary = new Dictionary();
		dictionary.setCaseSensitive(false);
		this.dictionaryRepository.save(dictionary);

		assertNotNull(dictionary.getId(), "dictionary id should be non null");

		Optional<Dictionary> savedDectionary = dictionaryRepository.findById(dictionary.getId());

		assertTrue(savedDectionary.isPresent(), "saved dictionary should be present");
		assertEquals(dictionary.getId(), savedDectionary.get().getId(),
				"saved dictionary id should match expected value");
	}

}
