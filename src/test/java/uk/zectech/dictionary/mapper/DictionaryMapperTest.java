package uk.zectech.dictionary.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import uk.zectech.dictionary.api.request.DictionaryRequest;
import uk.zectech.dictionary.api.response.DictionaryResponse;
import uk.zectech.dictionary.domain.Dictionary;
import uk.zectech.dictionary.mapping.DictionaryMapper;

/**
 * Test cases for the DictionaryMapper class.
 * 
 * @author Michael Conway
 *
 */
@ExtendWith(MockitoExtension.class)
public class DictionaryMapperTest {

	@InjectMocks
	private DictionaryMapper dictionaryMapper;

	/**
	 * Show that a Dictionary can be successfully mapped to a DictionaryResponse
	 */
	@Test
	public void shouldSuccessfullyMapFromDictionaryToDictionaryResponse() {
		Dictionary dictionary = aDictionary(123l);

		DictionaryResponse response = dictionaryMapper.mapToDictionaryResponse(dictionary);

		assertNotNull(response, "response should be non null");
		assertEquals(dictionary.getId(), response.getId(), "id should mach expected value");
		assertEquals(dictionary.isCaseSensitive(), response.isCaseSensitive(), "id should mach expected value");
		assertNotNull(response.getEntries(), "entries should be non null");
		assertEquals(dictionary.getEntries().size(), response.getEntries().size(),
				"entries size should match expected value");
		dictionary.getEntries().forEach(entry -> assertTrue(response.getEntries().contains(entry),
				"response entries should contain expected value (" + entry + ")"));
	}

	/**
	 * Show that a collection of Dictionary can be successfully mapped to a
	 * collection of DictionaryResponse.
	 */
	@Test
	public void shouldSuccessfullyMapFromCollectionOfDictionaryToACollectionOfDictionaryResponse() {
		Collection<Dictionary> dictionaries = aCollectionOfDictionary(5);

		Collection<DictionaryResponse> responses = dictionaryMapper.mapToCollectionOfDictionaryResponse(dictionaries);

		assertNotNull(responses, "responses should be non null");
		assertEquals(dictionaries.size(), responses.size(), "responses size should match expected value");
		dictionaries.forEach(dictionary -> {
			Optional<DictionaryResponse> response = responses.stream().filter(r -> dictionary.getId().equals(r.getId()))
					.findFirst();
			assertTrue(response.isPresent(), "response should be present");
			assertEquals(dictionary.getId(), response.get().getId(), "id should mach expected value");
			assertEquals(dictionary.isCaseSensitive(), response.get().isCaseSensitive(),
					"id should mach expected value");
			assertNotNull(response.get().getEntries(), "entries should be non null");
			assertEquals(dictionary.getEntries().size(), response.get().getEntries().size(),
					"entries size should match expected value");
			dictionary.getEntries().forEach(entry -> assertTrue(response.get().getEntries().contains(entry),
					"response entries should contain expected value (" + entry + ")"));
		});
	}

	/**
	 * Shiow that a DictionaryRequest can be successfully mapped to a Dictionary.
	 */
	@Test
	public void shouldSuccessfullyMapFromDictionaryRequestToDictionary() {
		DictionaryRequest request = new DictionaryRequest();
		request.setCaseSensitive(Boolean.FALSE);
		request.setEntries(new HashSet<>());
		request.getEntries().add("first");
		request.getEntries().add("second");
		request.getEntries().add("third");

		Dictionary dictionary = dictionaryMapper.mapToDictionary(request);

		assertNotNull(dictionary, "dictionary should be non null");
		assertNull(dictionary.getId(), "id should be null");
		assertEquals(request.isCaseSensitive(), dictionary.isCaseSensitive(),
				"isCaseSensitive should match expected value");
		assertNotNull(dictionary.getEntries(), "entries should be non null");
		request.getEntries().forEach(entry -> assertTrue(dictionary.getEntries().contains(entry),
				"dictionary entries should contain expected value (" + entry + ")"));
	}

	/**
	 * Create a collection of Dictionary.
	 * 
	 * @param size The collection size
	 * @return The collection
	 */
	private Collection<Dictionary> aCollectionOfDictionary(int size) {
		Collection<Dictionary> dictionaries = new ArrayList<>();
		for (long index = 0; index < size; index++) {
			dictionaries.add(aDictionary(index));
		}
		return dictionaries;
	}

	/**
	 * Create a test Dictionary.
	 * 
	 * @param id The id to use
	 * @return The Dictionary
	 */
	private Dictionary aDictionary(long id) {
		Dictionary dictionary = new Dictionary();
		dictionary.setId(id);
		dictionary.setCaseSensitive(Boolean.TRUE);
		dictionary.setEntries(new HashSet<>());
		dictionary.getEntries().add("first");
		dictionary.getEntries().add("second");
		dictionary.getEntries().add("third");
		return dictionary;
	}

}
