package uk.zectech.dictionary.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Base class for controller tests.
 * 
 * @author Michael Conway
 *
 */
public abstract class ControllerTestBase {

	/** The object mapper. */
	@Autowired
	private ObjectMapper objectMapper;
	
	/** The mock mvc. */
	@Autowired
	protected MockMvc mockMvc;
	
	/**
	 * Generate a JSON string.
	 * 
	 * @param obj The source object
	 * @return The JSON string
	 */
	public String asJsonString(final Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
