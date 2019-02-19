package fi.haagahelia.Bookstore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CateRepository extends CrudRepository<Category, Long> {
	List<Category> findAll();
	List<Category> findByName(String name);
}
