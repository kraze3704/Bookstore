package fi.haagahelia.Bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.Bookstore.domain.Book;
import fi.haagahelia.Bookstore.domain.BookRepository;
import fi.haagahelia.Bookstore.domain.CateRepository;
import fi.haagahelia.Bookstore.domain.Category;
import fi.haagahelia.Bookstore.domain.User;
import fi.haagahelia.Bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookstoreDemo(BookRepository bookRepo, CateRepository cateRepo, UserRepository userRepo) {
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
			
			// test user role:USER id:user pw:password
			User user1 = new User("user", "$2a$10$NlvPgFjQ0gaWaUrvnYothOeFgRkmS22SiYfNac/M1eucq2LbD8o5G", "", "USER");
			// test user role:ADMIN id:admin pw:admin
			User admin1 = new User("admin", "$2a$10$gosnLht4KLxiTlg4NFY5reGutRy4OJ0Sli4rzcVkkJQrfU/R7qnH6", "admin@bookstore.fi", "ADMIN");

			userRepo.save(user1);
			userRepo.save(admin1);
		};
	}
	
/*
 *  old in-memory test user injection
 * import org.springframework.security.core.userdetails.User;
 * 
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("user")
				.build();
		
		return new InMemoryUserDetailsManager(user);
	}
*/
}

