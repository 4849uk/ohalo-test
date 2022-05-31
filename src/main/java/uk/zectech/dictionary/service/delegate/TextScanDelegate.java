package uk.zectech.dictionary.service.delegate;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import uk.zectech.dictionary.api.response.DictionaryScanResponse;
import uk.zectech.dictionary.domain.Dictionary;

/**
 * Component responsible for the test scan function. Scans a target text block
 * for occurrences for dictionary entries.
 * 
 * @author Michael Conway
 *
 */
@Component
public class TextScanDelegate {

	/**
	 * Scan the target text for occurrences of dictionary elements.
	 * 
	 * @param dictionary The dictioanry to use
	 * @param target     The target text
	 * @return
	 */
	public List<DictionaryScanResponse> scanText(final Dictionary dictionary, final String target) {
		final Pattern pattern = this.getPattern(dictionary);
		final Matcher matcher = pattern.matcher(target);
		return this.getTextMathes(matcher);
	}

	/**
	 * Create the regex pattern for all the entries in a specific dictionary.
	 * 
	 * @param dictionary The dictionary
	 * @return The regex pattern
	 */
	private Pattern getPattern(final Dictionary dictionary) {
		final StringBuilder stringBuilder = new StringBuilder();
		dictionary.getEntries().forEach(entry -> {
			if (stringBuilder.length() > 0) {
				stringBuilder.append("|");
			}
			stringBuilder.append("(\\b");
			if (!dictionary.isCaseSensitive()) {
				stringBuilder.append("(?i)");
			}
			stringBuilder.append(entry);
			stringBuilder.append("\\b)");
		});
		return Pattern.compile(stringBuilder.toString());
	}

	/**
	 * Get the matches within the specified text block.
	 * 
	 * @param matcher The regex matcher
	 * @return List of match results
	 */
	private List<DictionaryScanResponse> getTextMathes(final Matcher matcher) {
		final List<DictionaryScanResponse> results = new ArrayList<>();
		while (matcher.find()) {
			final DictionaryScanResponse result = new DictionaryScanResponse();
			result.setStart(matcher.start());
			result.setEnd(matcher.end());
			result.setEntry(matcher.group());
			results.add(result);
		}
		return results;
	}

}
