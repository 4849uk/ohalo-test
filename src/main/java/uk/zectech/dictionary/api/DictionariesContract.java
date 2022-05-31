package uk.zectech.dictionary.api;

import java.util.Collection;

import org.springframework.http.ResponseEntity;

import uk.zectech.dictionary.api.response.DictionaryResponse;

/**
 * Interface describing the controlled endpoints handling lists of the
 * dictionary entities related requests.
 * 
 * @author Michael Conway
 *
 */
public interface DictionariesContract {

	/**
	 * List all dictionary entities.
	 * 
	 * @return Response entity containing the dictionary entity.
	 */
	ResponseEntity<Collection<DictionaryResponse>> list();

}
