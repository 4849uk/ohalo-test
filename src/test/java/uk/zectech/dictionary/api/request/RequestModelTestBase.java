package uk.zectech.dictionary.api.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;

/**
 * Request model test base class. Used to simplify testing of validation
 * parameters on request model classes.
 * 
 * @author Michael Conway
 *
 * @param <T> The type of request model to test
 */
public abstract class RequestModelTestBase<T> {

	/** The validator. */
	private Validator validator;

	/** The requet. */
	protected T request;

	/**
	 * Initialise the validation framework.
	 */
	@BeforeEach
	public void initialiseValidator() {
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
		this.validator = vf.getValidator();
	}

	/**
	 * Assert that the violation set is of the defined size and contains the
	 * required messages.
	 * 
	 * @param size     The size
	 * @param messages The messages that must be contained in the violations
	 */
	protected void assertViolations(int size, String... messages) {
		Set<ConstraintViolation<T>> violations = validator.validate(this.request);
		assertEquals(size, violations.size(), "validator should produce correct number of violations");
		List<String> errorMessages = violations.stream().map(violation -> violation.getMessage())
				.collect(Collectors.toList());
		Arrays.stream(messages).forEach(message -> {
			assertTrue(errorMessages.contains(message), "violations should contain expected message");
		});
	}

}
