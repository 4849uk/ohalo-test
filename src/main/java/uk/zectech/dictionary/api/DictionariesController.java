package uk.zectech.dictionary.api;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.zectech.dictionary.api.response.DictionaryResponse;
import uk.zectech.dictionary.service.DictionaryService;

/**
 * Implementation of the Dictionaries API contract.
 * 
 * @author Michael Conway
 *
 */
@RestController
@RequestMapping(value = "/api/dictionaries")
public class DictionariesController implements DictionariesContract {
	
	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DictionariesController.class);

	/** The dictionary service. */
	@Autowired
	private DictionaryService dictionaryService;

	@Override
	@GetMapping
	public ResponseEntity<Collection<DictionaryResponse>> list() {
		LOGGER.debug("listing dictionaries");
		return new ResponseEntity<>(this.dictionaryService.list(), HttpStatus.OK);
	}

}
