package fi.haagahelia.Bookstore.domain;

import org.springframework.data.repository.CrudRepository;

public interface CateRepository extends CrudRepository<Category, Long> {
	
}
