package uk.zectech.dictionary.api;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uk.zectech.dictionary.api.request.DictionaryRequest;
import uk.zectech.dictionary.api.response.DictionaryResponse;
import uk.zectech.dictionary.api.response.DictionaryScanResponse;
import uk.zectech.dictionary.service.DictionaryService;

/**
 * Implementation of the Dictioary API contract.
 * 
 * @author Michael Conway
 *
 */
@RestController
@RequestMapping(value = "/api/dictionary")
public class DictionaryController implements DictionaryContract {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DictionaryController.class);

	/** The dictionary service. */
	@Autowired
	private DictionaryService dictionaryService;

	@Override
	@GetMapping(path = "/{id}")
	public ResponseEntity<DictionaryResponse> get(@PathVariable(name = "id", required = true) final Long id) {
		LOGGER.debug("getting dictionary : id {}", id);
		return new ResponseEntity<>(dictionaryService.get(id), HttpStatus.OK);
	}

	@Override
	@PostMapping
	public ResponseEntity<DictionaryResponse> post(@RequestBody @Valid final DictionaryRequest request) {
		LOGGER.debug("posting dictionary : request {}", request);
		return new ResponseEntity<>(dictionaryService.create(request), HttpStatus.OK);
	}

	@Override
	@PutMapping(path = "/{id}")
	public ResponseEntity<DictionaryResponse> put(@PathVariable(name = "id", required = true) final Long id,
			@RequestBody @Valid final DictionaryRequest request) {
		LOGGER.debug("putting dictionary : id {}, request {}", id, request);
		return new ResponseEntity<>(dictionaryService.update(id, request), HttpStatus.OK);
	}

	@Override
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id", required = true) final Long id) {
		LOGGER.debug("deleting dictionary : id {}", id);
		this.dictionaryService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	@GetMapping(path = "/{id}/scan")
	public ResponseEntity<List<DictionaryScanResponse>> scan(@PathVariable(name = "id", required = true) Long id,
			@RequestParam(name = "target", required = true) String target) {
		LOGGER.debug("scan dictionary : id {}, target {}", id, target);
		return new ResponseEntity<>(dictionaryService.scan(id, target), HttpStatus.OK);
	}

}
