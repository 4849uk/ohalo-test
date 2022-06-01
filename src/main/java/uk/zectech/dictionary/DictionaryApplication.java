package uk.zectech.dictionary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import uk.zectech.dictionary.api.DictionaryController;
import uk.zectech.dictionary.mapping.DictionaryMapper;
import uk.zectech.dictionary.repository.DictionaryRepository;
import uk.zectech.dictionary.service.DictionaryService;

/**
 * Boot executable class.
 * 
 * @author Michael Conway
 *
 */
@SpringBootApplication
@EnableWebMvc
@ComponentScan(basePackageClasses = { DictionaryController.class, DictionaryRepository.class, DictionaryService.class,
		DictionaryMapper.class })
public class DictionaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DictionaryApplication.class, args);
	}

}
