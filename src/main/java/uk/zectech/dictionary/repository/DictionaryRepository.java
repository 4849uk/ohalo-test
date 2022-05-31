package uk.zectech.dictionary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uk.zectech.dictionary.domain.Dictionary;

/**
 * Dictionary repository definition.
 * 
 * @author Michael COnway
 *
 */
@Repository
public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {

}
