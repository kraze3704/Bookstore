package fi.haagahelia.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.Bookstore.domain.CateRepository;
import fi.haagahelia.Bookstore.domain.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {

	@Autowired
	private CateRepository catRepo;
	
	@Test
	public void CategoryIdAutoGenerated() {
		Category category = new Category("randomCategory");
		catRepo.save(category);
		assertThat(category.getCategoryId()).isNotNull();
	}

	@Test
	public void findByNameShouldReturnCategory() {
		List<Category> categories = catRepo.findByName("randomCategory");
		assertThat(categories).hasSize(0);
	}
	
	@Test
	public void findAllShouldReturnAllCategories() {
		List<Category> categories = catRepo.findAll();
		assertThat(categories).hasSize(2); // two test categories are present
		assertThat(categories.get(0).getName()).isEqualTo("FICTION");
	}
}
