package fi.haagahelia.Bookstore.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BookRepository extends CrudRepository<Book, Long> {
	List<Book> findAll();
	
	List<Book> findByTitle(String title);
	
	Optional<Book> findById(Long id);
	
	List<Book> findByAuthor(@Param("author") String author);
}