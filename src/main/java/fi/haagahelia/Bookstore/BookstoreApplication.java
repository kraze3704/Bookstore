package fi.haagahelia.Bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import fi.haagahelia.Bookstore.domain.Book;
import fi.haagahelia.Bookstore.domain.BookRepository;
import fi.haagahelia.Bookstore.domain.CateRepository;
import fi.haagahelia.Bookstore.domain.Category;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookstoreDemo(BookRepository bookRepo, CateRepository cateRepo) {
		return (args) -> {
			
			Category cat1 = new Category("FICTION");
			Category cat2 = new Category("NONFICTION");
			
			cateRepo.save(cat1);
			cateRepo.save(cat2);
			
			Book book1 = new Book("Server Programming", "Juha", 2019, "SWD4TF021", (float) 30.02, cat2);
			Book book2 = new Book("Database Developer", "Kari", 2019, "SWD8TF040", (float) 30.02, cat2);
			Book book3 = new Book("Software Project", "Juhani", 2019, "SWD4TF024", (float) 30.01, cat1);
			
			bookRepo.save(book1);
			bookRepo.save(book2);
			bookRepo.save(book3);

		};
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("user")
				.build();
		
		return new InMemoryUserDetailsManager(user);
	}
}

