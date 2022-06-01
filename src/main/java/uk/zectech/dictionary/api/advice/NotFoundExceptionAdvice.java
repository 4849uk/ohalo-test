package uk.zectech.dictionary.api.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import uk.zectech.dictionary.api.error.NotFoundException;

/**
 * Controller advice used to translate NotFoundException into a 404 NOT_FOUND
 * response.
 * 
 * @author Michael Conway
 *
 */
@ControllerAdvice(annotations = { RestController.class })
public class NotFoundExceptionAdvice {

	/**
	 * Handle the NotFoundException case.
	 * 
	 * @return A 404 response entity
	 */
	@ExceptionHandler({ NotFoundException.class })
	public ResponseEntity<Void> handleNotFoundException() {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
