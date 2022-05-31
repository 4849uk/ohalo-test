package uk.zectech.dictionary.api.response;

/**
 * Reponse model used by the dictionary scan process.
 * 
 * @author Michael Conway
 *
 */
public class DictionaryScanResponse {

	/** The start. */
	private int start;

	/** The end. */
	private int end;

	/** The entry. */
	private String entry;

	/**
	 * @return the start
	 */
	public int getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(int end) {
		this.end = end;
	}

	/**
	 * @return the entry
	 */
	public String getEntry() {
		return entry;
	}

	/**
	 * @param entry the entry to set
	 */
	public void setEntry(String entry) {
		this.entry = entry;
	}

}
