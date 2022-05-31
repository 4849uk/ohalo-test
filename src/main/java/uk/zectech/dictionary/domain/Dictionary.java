package uk.zectech.dictionary.domain;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Definition for the Dictoinary entity.
 * 
 * @author Michael Conway
 *
 */
@Entity
@Table(name = "dictionary")
public class Dictionary {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dictionary_sequence")
	@SequenceGenerator(name = "dictionary_sequence", sequenceName = "dictionary_sequence", allocationSize = 1)
	@Column(name = "id", nullable = false)
	private Long id;

	/** The case sensitive indicator. */
	@Column(name = "is_case_sensitive", nullable = false)
	private boolean caseSensitive;

	/** The dictionary entries. */
	@ElementCollection
	@CollectionTable(name = "entries", joinColumns = @JoinColumn(name = "dictionary_id", referencedColumnName = "id"))
	@Column(name = "entry", nullable = false)
	private Set<String> entries;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the caseSensitive
	 */
	public boolean isCaseSensitive() {
		return caseSensitive;
	}

	/**
	 * @param caseSensitive the caseSensitive to set
	 */
	public void setCaseSensitive(boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}

	/**
	 * @return the entries
	 */
	public Set<String> getEntries() {
		return entries;
	}

	/**
	 * @param entries the entries to set
	 */
	public void setEntries(Set<String> entries) {
		this.entries = entries;
	}

}
