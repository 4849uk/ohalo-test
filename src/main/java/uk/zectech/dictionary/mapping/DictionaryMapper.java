package uk.zectech.dictionary.mapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.stereotype.Component;

import uk.zectech.dictionary.api.request.DictionaryRequest;
import uk.zectech.dictionary.api.response.DictionaryResponse;
import uk.zectech.dictionary.domain.Dictionary;

/**
 * Utility component used to map the Dictionary entity to and from various
 * formats.
 * 
 * @author Michael Conway
 *
 */
@Component
public class DictionaryMapper {

	/**
	 * Map a Dictionary to a DictionaryResponse.
	 * 
	 * @param dictionary The dictionary
	 * @return The DictionaryResponse
	 */
	public DictionaryResponse mapToDictionaryResponse(final Dictionary dictionary) {
		final DictionaryResponse response = new DictionaryResponse();
		response.setId(dictionary.getId());
		response.setCaseSensitive(dictionary.isCaseSensitive());
		if (dictionary.getEntries() != null) {
			response.setEntries(new HashSet<String>());
			dictionary.getEntries().forEach(entry -> response.getEntries().add(entry));
		}
		return response;
	}

	/**
	 * Map a collection of Dictionary to a collection of DictionaryResponse.
	 * 
	 * @param dictionaries The collection of Dictionary
	 * @return A collection of DictionaryResponse
	 */
	public Collection<DictionaryResponse> mapToCollectionOfDictionaryResponse(
			final Collection<Dictionary> dictionaries) {
		final Collection<DictionaryResponse> responses = new ArrayList<>();
		dictionaries.forEach(dictionary -> responses.add(mapToDictionaryResponse(dictionary)));
		return responses;
	}

	/**
	 * Map a DictionaryRequest to a Dictionary.
	 * 
	 * @param request The DictionaryRequest
	 * @return A Dictionary
	 */
	public Dictionary mapToDictionary(final DictionaryRequest request) {
		final Dictionary dictionary = new Dictionary();
		if (request.getEntries() != null) {
			dictionary.setEntries(new HashSet<String>());
			request.getEntries().forEach(entry -> dictionary.getEntries().add(entry));
		}
		dictionary.setCaseSensitive(request.isCaseSensitive().booleanValue());
		return dictionary;
	}

}
