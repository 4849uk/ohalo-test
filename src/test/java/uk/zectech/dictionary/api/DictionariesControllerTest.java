package uk.zectech.dictionary.api;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import uk.zectech.dictionary.api.response.DictionaryResponse;
import uk.zectech.dictionary.service.DictionaryService;

/**
 * Test cases for the DictionariesController class.
 * 
 * @author Michael Conway
 *
 */
@WebMvcTest(controllers = DictionariesController.class)
public class DictionariesControllerTest extends ControllerTestBase {

	/** The dictionary service. */
	@MockBean
	private DictionaryService dictionaryService;
	
	/**
	 * Show that all dictionary entities can be successfully listed.
	 * 
	 * @throws Exception
	 */
	@Test
	public void shouldSuccessfullyListAllDictionaryEntities() throws Exception {
		when(dictionaryService.list()).thenReturn(aCollectionOfDictionaryResponses(5));
		
		mockMvc.perform(get("/api/dictionaries").contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));

		verify(dictionaryService, times(1)).list();
		verifyNoMoreInteractions(dictionaryService);
	}

	/**
	 * Create a test collection of dictionary responses
	 * @param size The collection size
	 * @return The responses
	 */
	private Collection<DictionaryResponse> aCollectionOfDictionaryResponses(int size) {
		Collection<DictionaryResponse> responses = new ArrayList<>();
		for (long index = 0; index < size; index++) {
			responses.add(aDictionaryResponse(index));
		}
		return responses;
	}
	
	/**
	 * Create a test dictionary response.
	 * 
	 * @param id The id to use
	 * @return The dictionary response
	 */
	private DictionaryResponse aDictionaryResponse(long id) {
		DictionaryResponse response = new DictionaryResponse();
		response.setId(Long.valueOf(id));
		response.setEntries(new HashSet<String>());
		response.getEntries().add("first");
		response.getEntries().add("second");
		response.getEntries().add("first");
		return response;
	}

}
