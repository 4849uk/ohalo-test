package uk.zectech.dictionary.api;

import java.util.List;

import org.springframework.http.ResponseEntity;

import uk.zectech.dictionary.api.request.DictionaryRequest;
import uk.zectech.dictionary.api.response.DictionaryResponse;
import uk.zectech.dictionary.api.response.DictionaryScanResponse;

/**
 * Interface describing the controlled endpoints handling the dictionary entity
 * related requests.
 * 
 * @author Michael Conway
 *
 */
public interface DictionaryContract {

	/**
	 * Get a specific dictionary by id.
	 * 
	 * @param id The id
	 * @return Response entity containing the dictionary entity.
	 */
	ResponseEntity<DictionaryResponse> get(Long id);

	/**
	 * Create a new dictionary entity.
	 * 
	 * @param request The disctionary request
	 * @return Response entity containing the created dictionary entity.
	 */
	ResponseEntity<DictionaryResponse> post(DictionaryRequest request);

	/**
	 * Update an existing dictionary entity.
	 * 
	 * @param id      The dictionatry id
	 * @param request The request
	 * @return Response entity containing the updated dictionary entity.
	 */
	ResponseEntity<DictionaryResponse> put(Long id, DictionaryRequest request);

	/**
	 * Delete a dictionary entity.
	 * 
	 * @param id The dictionary id
	 * @return Response entity.
	 */
	ResponseEntity<Void> delete(Long id);

	/**
	 * Scan target text for dictionary entry matches.
	 * 
	 * @param id     The dictionary id
	 * @param target The target text
	 * @return Response entity containing the dictionary entry hits details
	 */
	ResponseEntity<List<DictionaryScanResponse>> scan(Long id, String target);

}
