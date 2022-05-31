package uk.zectech.dictionary.service;

import java.util.Collection;
import java.util.List;

import uk.zectech.dictionary.api.request.DictionaryRequest;
import uk.zectech.dictionary.api.response.DictionaryResponse;
import uk.zectech.dictionary.api.response.DictionaryScanResponse;

/**
 * Service interface description for Dictionary related CRUD operations.
 * 
 * @author Michael Conway
 *
 */
public interface DictionaryService {

	/**
	 * Get the Dictionary entity.
	 * 
	 * @param id The dictionary id
	 * @return The dictionary element
	 */
	public DictionaryResponse get(Long id);

	/**
	 * Create a new Dictionary entity.
	 * 
	 * @param request The dictionary request
	 * @return The created dictionary entity
	 */
	public DictionaryResponse create(DictionaryRequest request);

	/**
	 * 
	 * @param id      The dictionary id
	 * @param request The dictionary request
	 * @return The updated dictionary entity
	 */
	public DictionaryResponse update(Long id, DictionaryRequest request);

	/**
	 * Delete a dictionary entity.
	 * 
	 * @param id The dictionary id
	 */
	public void delete(Long id);

	/**
	 * List all dictionary entities.
	 * 
	 * @return Collection of dictionary entites
	 */
	public Collection<DictionaryResponse> list();

	/**
	 * Scan text for dictionary entries.
	 *
	 * @param id     the id The dictionary id
	 * @param target the target The target text
	 * @return The list of target huits start and ent indexes
	 */
	public List<DictionaryScanResponse> scan(Long id, String target);

}
