package uk.zectech.dictionary.api;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import uk.zectech.dictionary.api.error.NotFoundException;
import uk.zectech.dictionary.api.request.DictionaryRequest;
import uk.zectech.dictionary.api.response.DictionaryResponse;
import uk.zectech.dictionary.api.response.DictionaryScanResponse;
import uk.zectech.dictionary.service.DictionaryService;

/**
 * Test cases for the DictionaryController class.
 * 
 * @author Michael Conway
 *
 */
@WebMvcTest(controllers = DictionaryController.class)
public class DictionaryControllerTest extends ControllerTestBase {

	/** Test values. */
	private static final Long TEST_ID = Long.valueOf(23846986);

	/** The dictionary service. */
	@MockBean
	private DictionaryService dictionaryService;

	/**
	 * Show that a dictionary entity can be successfully retrieved.
	 * 
	 * @throws Exception
	 */
	@Test
	public void shouldSuccessfullyGetDictionaryEntity() throws Exception {
		when(dictionaryService.get(isA(Long.class))).thenReturn(aDictionaryResponse());

		mockMvc.perform(get("/api/dictionary/" + TEST_ID).contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.id").value(TEST_ID));

		verify(dictionaryService, times(1)).get(eq(TEST_ID));
		verifyNoMoreInteractions(dictionaryService);
	}

	/**
	 * Show that an 404 not found status code is returned when a dictionary entity
	 * cannot be found.
	 * 
	 * @throws Exception
	 */
	@Test
	public void shouldReturn404NotFoundWhenGettingDictionaryByInvalidId() throws Exception {
		when(dictionaryService.get(isA(Long.class))).thenThrow(new NotFoundException());

		mockMvc.perform(get("/api/dictionary/" + TEST_ID).contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isNotFound());

		verify(dictionaryService, times(1)).get(eq(TEST_ID));
		verifyNoMoreInteractions(dictionaryService);
	}

	/**
	 * Show that a new dictionary entity can be successfully created.
	 * 
	 * @throws Exception
	 */
	@Test
	public void shouldSuccessfullyPostNewDictionaryEntity() throws Exception {
		when(dictionaryService.create(isA(DictionaryRequest.class))).thenReturn(aDictionaryResponse());

		DictionaryRequest request = aDictionaryRequest();

		mockMvc.perform(
				post("/api/dictionary").contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(request)))
				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(TEST_ID));

		verify(dictionaryService, times(1)).create(isA(DictionaryRequest.class));
		verifyNoMoreInteractions(dictionaryService);
	}

	/**
	 * Show that the post dictionary entity will fail when entries is is null.
	 * 
	 * @throws Exception
	 */
	@Test
	public void shouldFailToPostNewDictionaryEntityWhenEntitesIsNull() throws Exception {
		DictionaryRequest request = aDictionaryRequest();
		request.setEntries(null);

		mockMvc.perform(
				post("/api/dictionary").contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(request)))
				.andDo(print()).andExpect(status().isBadRequest());

		verifyNoInteractions(dictionaryService);
	}

	/**
	 * Show that the post dictionary entity will fail when entries is is null.
	 * 
	 * @throws Exception
	 */
	@Test
	public void shouldFailToPostNewDictionaryEntityWhenEntitesIsEmpty() throws Exception {
		DictionaryRequest request = aDictionaryRequest();
		request.getEntries().clear();

		mockMvc.perform(
				post("/api/dictionary").contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(request)))
				.andDo(print()).andExpect(status().isBadRequest());

		verifyNoInteractions(dictionaryService);
	}

	/**
	 * Show that the post dictionary entity will fail when is_case_sensitive is is
	 * null.
	 * 
	 * @throws Exception
	 */
	@Test
	public void shouldFailToPostNewDictionaryEntityWhenIsCaseSensitiveIsNull() throws Exception {
		DictionaryRequest request = aDictionaryRequest();
		request.setCaseSensitive(null);

		mockMvc.perform(
				post("/api/dictionary").contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(request)))
				.andDo(print()).andExpect(status().isBadRequest());

		verifyNoInteractions(dictionaryService);
	}

	/**
	 * Show that a new dictionary entity can be successfully updated.
	 * 
	 * @throws Exception
	 */
	@Test
	public void shouldSuccessfullyPutNewDictionaryEntity() throws Exception {
		when(dictionaryService.update(isA(Long.class), isA(DictionaryRequest.class))).thenReturn(aDictionaryResponse());

		DictionaryRequest request = aDictionaryRequest();

		mockMvc.perform(put("/api/dictionary/" + TEST_ID).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(asJsonString(request))).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(TEST_ID));

		verify(dictionaryService, times(1)).update(eq(TEST_ID), isA(DictionaryRequest.class));
		verifyNoMoreInteractions(dictionaryService);
	}

	/**
	 * Show that a dictionary entity can be successfully deleted.
	 * 
	 * @throws Exception
	 */
	@Test
	public void shouldSuccessfullyDeleteDictionaryEntity() throws Exception {
		mockMvc.perform(delete("/api/dictionary/" + TEST_ID).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().string(""));

		verify(dictionaryService, times(1)).delete(eq(TEST_ID));
		verifyNoMoreInteractions(dictionaryService);
	}

	/**
	 * Show that target text can be successfully scanned.
	 * 
	 * @throws Exception
	 */
	@Test
	public void shouldSuccessfullyScanTargetText() throws Exception {
		when(dictionaryService.scan(isA(Long.class), isA(String.class))).thenReturn(aArrayOfDictionaryScanResponse(5));

		mockMvc.perform(get("/api/dictionary/" + TEST_ID + "/scan?target=Some target text")
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(5)));

		verify(dictionaryService, times(1)).scan(eq(TEST_ID), eq("Some target text"));
		verifyNoMoreInteractions(dictionaryService);
	}

	/**
	 * Create a test dictionary response.
	 * 
	 * @return The dictionary response
	 */
	private DictionaryResponse aDictionaryResponse() {
		DictionaryResponse response = new DictionaryResponse();
		response.setId(TEST_ID);
		response.setEntries(new HashSet<String>());
		response.getEntries().add("first");
		response.getEntries().add("second");
		response.getEntries().add("first");
		return response;
	}

	/**
	 * Create a test dictionary request.
	 * 
	 * @return The dictionary request
	 */
	private DictionaryRequest aDictionaryRequest() {
		DictionaryRequest request = new DictionaryRequest();
		request.setEntries(new HashSet<String>());
		request.getEntries().add("first");
		request.getEntries().add("second");
		request.getEntries().add("first");
		request.setCaseSensitive(Boolean.TRUE);
		return request;
	}

	/**
	 * Creat a list of document scan responses.
	 * 
	 * @param size Size of the list
	 * @return The list
	 */
	private List<DictionaryScanResponse> aArrayOfDictionaryScanResponse(int size) {
		List<DictionaryScanResponse> responses = new ArrayList<>();
		for (int index = 0; index < size; index++) {
			DictionaryScanResponse response = new DictionaryScanResponse();
			response.setStart(0);
			response.setEnd(10);
			response.setEntry("Search");
			responses.add(response);
		}
		return responses;
	}

}
