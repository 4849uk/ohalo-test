package uk.zectech.dictionary.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import uk.zectech.dictionary.api.error.NotFoundException;
import uk.zectech.dictionary.api.request.DictionaryRequest;
import uk.zectech.dictionary.api.response.DictionaryResponse;
import uk.zectech.dictionary.api.response.DictionaryScanResponse;
import uk.zectech.dictionary.domain.Dictionary;
import uk.zectech.dictionary.mapping.DictionaryMapper;
import uk.zectech.dictionary.repository.DictionaryRepository;
import uk.zectech.dictionary.service.delegate.TextScanDelegate;

/**
 * Test cases for the DictionaryServiceImpl class.
 * 
 * @author Michael Conway
 *
 */
@ExtendWith(MockitoExtension.class)
public class DictionaryServiceImplTest {

	/** Test values. */
	private static final Long TEST_ID = Long.valueOf(783456);

	/** The dictionary repository. */
	@Mock
	private DictionaryRepository dictionaryRepository;

	/** The dictionary mapper. */
	@Mock
	private DictionaryMapper dictionaryMapper;

	/** The text scanner delegate. */
	@Mock
	private TextScanDelegate textScannerDelegate;

	/** The dictionary service. */
	@InjectMocks
	private DictionaryServiceImpl dictionaryService;

	/**
	 * Show that a dictionary can be successfully retrieved.
	 */
	@Test
	public void shouldSuccessfullyRetrieveDictionary() {
		when(dictionaryRepository.findById(isA(Long.class))).thenReturn(Optional.of(new Dictionary()));
		when(dictionaryMapper.mapToDictionaryResponse(isA(Dictionary.class))).thenReturn(new DictionaryResponse());

		DictionaryResponse response = dictionaryService.get(TEST_ID);

		assertNotNull(response, "response should be non null");

		verify(dictionaryRepository, times(1)).findById(eq(TEST_ID));
		verify(dictionaryMapper, times(1)).mapToDictionaryResponse(isA(Dictionary.class));
		verifyNoMoreInteractions(dictionaryRepository, dictionaryMapper);
		verifyNoInteractions(textScannerDelegate);
	}

	/**
	 * Show that the expected exception is thrown when retrieving a dictionary that
	 * cannot be found.
	 */
	@Test
	public void shouldThrowExpectedExcetionWhenDictionaryCannotBeRetrievedDueToNotFound() {
		when(dictionaryRepository.findById(isA(Long.class))).thenReturn(Optional.empty());

		assertThrows(NotFoundException.class, () -> dictionaryService.get(TEST_ID));

		verify(dictionaryRepository, times(1)).findById(eq(TEST_ID));
		verifyNoMoreInteractions(dictionaryRepository);
		verifyNoInteractions(dictionaryMapper, textScannerDelegate);
	}

	/**
	 * Show that a new dictionary can be created.
	 */
	@Test
	public void shouldSuccessfullyCreateANewDictionary() {
		when(dictionaryMapper.mapToDictionary(isA(DictionaryRequest.class))).thenReturn(new Dictionary());

		DictionaryResponse mapperResponse = new DictionaryResponse();
		when(dictionaryMapper.mapToDictionaryResponse(isA(Dictionary.class))).thenReturn(mapperResponse);

		DictionaryRequest request = new DictionaryRequest();
		DictionaryResponse response = dictionaryService.create(request);

		assertNotNull(response, "response should be non null");
		assertSame(mapperResponse, response, "reponse should be the same as mapperResponse");

		verify(dictionaryMapper, times(1)).mapToDictionary(same(request));
		verify(dictionaryMapper, times(1)).mapToDictionaryResponse(isA(Dictionary.class));
		verify(dictionaryRepository, times(1)).save(isA(Dictionary.class));
		verifyNoMoreInteractions(dictionaryRepository, dictionaryMapper);
		verifyNoInteractions(textScannerDelegate);
	}

	/**
	 * Show that an existing dictionary can be updated successfully.
	 */
	@Test
	public void shouldSuccessfullyUpdateAnExistingDictionaryEntity() {
		when(dictionaryRepository.existsById(isA(Long.class))).thenReturn(true);
		when(dictionaryMapper.mapToDictionary(isA(DictionaryRequest.class))).thenReturn(new Dictionary());

		DictionaryResponse mapperResponse = new DictionaryResponse();
		when(dictionaryMapper.mapToDictionaryResponse(isA(Dictionary.class))).thenReturn(mapperResponse);

		DictionaryRequest request = new DictionaryRequest();
		DictionaryResponse response = dictionaryService.update(TEST_ID, request);

		verify(dictionaryRepository, times(1)).existsById(eq(TEST_ID));

		assertNotNull(response, "response should be non null");
		assertSame(mapperResponse, response, "reponse should be the same as mapperResponse");
		verify(dictionaryMapper, times(1)).mapToDictionary(same(request));
		verify(dictionaryMapper, times(1)).mapToDictionaryResponse(isA(Dictionary.class));

		ArgumentCaptor<Dictionary> dictionaryCaptor = ArgumentCaptor.forClass(Dictionary.class);
		verify(dictionaryRepository, times(1)).save(dictionaryCaptor.capture());
		Dictionary savedDictionary = dictionaryCaptor.getValue();
		assertNotNull(savedDictionary, "savedDictionary should be non null");
		assertEquals(TEST_ID, savedDictionary.getId(), "savedDictionary id should match expected value");

		verifyNoMoreInteractions(dictionaryRepository, dictionaryMapper);
		verifyNoInteractions(textScannerDelegate);
	}

	/**
	 * Show that the expected exception is thrown when updating an nuknown
	 * dictionary.
	 */
	@Test
	public void shouldThrowExpectedExceptionWhenUpdatingAnUnknownDictionary() {
		when(dictionaryRepository.existsById(isA(Long.class))).thenReturn(false);

		assertThrows(NotFoundException.class, () -> dictionaryService.update(TEST_ID, new DictionaryRequest()));

		verify(dictionaryRepository, times(1)).existsById(eq(TEST_ID));
		verifyNoMoreInteractions(dictionaryRepository);
		verifyNoInteractions(dictionaryMapper, textScannerDelegate);
	}

	/**
	 * Show that a dictionary can be successfully deleted.
	 */
	@Test
	public void shouldSuccessfullyDeleteDictionary() {
		when(dictionaryRepository.existsById(isA(Long.class))).thenReturn(true);

		dictionaryService.delete(TEST_ID);

		verify(dictionaryRepository, times(1)).existsById(eq(TEST_ID));
		verify(dictionaryRepository, times(1)).deleteById(eq(TEST_ID));
		verifyNoMoreInteractions(dictionaryRepository);
		verifyNoInteractions(textScannerDelegate, dictionaryMapper);
	}

	/**
	 * Show that a list of dictionaries can be successfully retrieved.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void shouldSccessfullyListDictionaries() {
		when(dictionaryRepository.findAll()).thenReturn(new ArrayList<>());

		Collection<DictionaryResponse> mapperResponse = new ArrayList<>();
		when(dictionaryMapper.mapToCollectionOfDictionaryResponse(isA(Collection.class))).thenReturn(mapperResponse);

		Collection<DictionaryResponse> response = dictionaryService.list();

		assertNotNull(response, "response should be non null");
		assertSame(mapperResponse, response, "response sould mathe expeced vaule");

		verify(dictionaryRepository, times(1)).findAll();
		verify(dictionaryMapper, times(1)).mapToCollectionOfDictionaryResponse(isA(Collection.class));
		verifyNoMoreInteractions(dictionaryRepository, dictionaryMapper);
		verifyNoInteractions(textScannerDelegate);
	}

	/**
	 * Show that text can be successfully scanned using a dictionary.
	 */
	@Test
	public void shouldSuccessfullyScanTextUsingADictionary() {
		when(dictionaryRepository.findById(isA(Long.class))).thenReturn(Optional.of(new Dictionary()));

		List<DictionaryScanResponse> scanResponse = new ArrayList<>();
		when(textScannerDelegate.scanText(isA(Dictionary.class), isA(String.class))).thenReturn(scanResponse);

		List<DictionaryScanResponse> response = dictionaryService.scan(TEST_ID, "Target text");
		assertSame(scanResponse, response, "response should match expected value");

		verify(dictionaryRepository, times(1)).findById(eq(TEST_ID));
		verify(textScannerDelegate, times(1)).scanText(isA(Dictionary.class), eq("Target text"));
		verifyNoMoreInteractions(dictionaryRepository, textScannerDelegate);
		verifyNoInteractions(dictionaryMapper);
	}

	/**
	 * Show that the expected exception is thrown whenan attempt is made to scan a
	 * text arget and the dictionary is not found.
	 */
	@Test
	public void shouldThrowExpectedExcetionWhenScanningTextUsingAnUnknownDictionary() {
		when(dictionaryRepository.findById(isA(Long.class))).thenReturn(Optional.empty());

		assertThrows(NotFoundException.class, () -> dictionaryService.scan(TEST_ID, "Target text"));

		verify(dictionaryRepository, times(1)).findById(eq(TEST_ID));
		verifyNoMoreInteractions(dictionaryRepository);
		verifyNoInteractions(textScannerDelegate, dictionaryMapper);
	}

}
