package uk.zectech.dictionary.api.advice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Test cases for the NotFoundExceptionAdvice class.
 * 
 * @author Michael Conway
 *
 */
public class NotFoundExceptionAdviceTest {

	private NotFoundExceptionAdvice notFoundExceptionAdvice = new NotFoundExceptionAdvice();

	/**
	 * Show theat the expected response entity is returned when handling
	 * NotFoundException exceptions;
	 */
	@Test
	public void shouldReturn04NotFountResponseEntityWhenHandlingNotFoundException() {
		ResponseEntity<Void> responseEntity = notFoundExceptionAdvice.handleNotFoundException();

		assertNotNull(responseEntity, "responseEntity should be non null");
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode(), "statusCode should equal NOT_FOUND");
	}

}
