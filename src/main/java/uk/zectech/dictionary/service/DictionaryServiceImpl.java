package uk.zectech.dictionary.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import uk.zectech.dictionary.api.error.NotFoundException;
import uk.zectech.dictionary.api.request.DictionaryRequest;
import uk.zectech.dictionary.api.response.DictionaryResponse;
import uk.zectech.dictionary.api.response.DictionaryScanResponse;
import uk.zectech.dictionary.domain.Dictionary;
import uk.zectech.dictionary.mapping.DictionaryMapper;
import uk.zectech.dictionary.repository.DictionaryRepository;
import uk.zectech.dictionary.service.delegate.TextScanDelegate;

/**
 * Implementation of the DictionaryService interface.
 * 
 * @author Michael COnway
 *
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DictionaryServiceImpl.class);

	/** The dictionary repository. */
	@Autowired
	private DictionaryRepository dictionaryRepository;

	/** The dictionary mapper. */
	@Autowired
	private DictionaryMapper dictionaryMapper;

	/** The text scanner delegate. */
	@Autowired
	private TextScanDelegate textScannerDelegate;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public DictionaryResponse get(final Long id) {
		LOGGER.debug("retrieving dictionary: id {}", id);
		final Optional<Dictionary> dictionary = this.dictionaryRepository.findById(id);
		return this.dictionaryMapper.mapToDictionaryResponse(dictionary.orElseThrow(NotFoundException::new));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public DictionaryResponse create(final DictionaryRequest request) {
		LOGGER.debug("creating dictionary: request {}", request);
		final Dictionary dictionary = this.dictionaryMapper.mapToDictionary(request);
		this.dictionaryRepository.save(dictionary);
		return this.dictionaryMapper.mapToDictionaryResponse(dictionary);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public DictionaryResponse update(final Long id, final DictionaryRequest request) {
		LOGGER.debug("updating dictionary: id {}, request {}", id, request);
		this.assertDictionaryExists(id);
		final Dictionary dictionary = this.dictionaryMapper.mapToDictionary(request);
		dictionary.setId(id);
		this.dictionaryRepository.save(dictionary);
		return this.dictionaryMapper.mapToDictionaryResponse(dictionary);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(final Long id) {
		LOGGER.debug("deleting dictionary: id {}", id);
		this.assertDictionaryExists(id);
		this.dictionaryRepository.deleteById(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Collection<DictionaryResponse> list() {
		LOGGER.debug("listing dictionaries");
		final Collection<Dictionary> dictionaries = this.dictionaryRepository.findAll();
		return this.dictionaryMapper.mapToCollectionOfDictionaryResponse(dictionaries);
	}

	@Override
	public List<DictionaryScanResponse> scan(final Long id, final String target) {
		LOGGER.debug("scanning dictionary: id {}, target {}", id, target);
		final Optional<Dictionary> dictionary = this.dictionaryRepository.findById(id);
		return this.textScannerDelegate.scanText(dictionary.orElseThrow(NotFoundException::new), target);
	}

	/**
	 * Assert that a dictionary of a given id exists. NotFoundException thrown if id
	 * does not exist.
	 * 
	 * @param id The dictionary id
	 */
	private void assertDictionaryExists(final Long id) {
		if (!this.dictionaryRepository.existsById(id)) {
			throw new NotFoundException();
		}
	}

}
