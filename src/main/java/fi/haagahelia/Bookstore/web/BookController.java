package fi.haagahelia.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fi.haagahelia.Bookstore.domain.Book;
import fi.haagahelia.Bookstore.domain.BookRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository bookRepo;
	
	@RequestMapping("/index")
	public String home() {
		return "index";
	}
	
	@RequestMapping("/booklist")
	public String bookListPage(Model model) {
		model.addAttribute("Books", bookRepo.findAll());
		return "booklist";
	}
	
	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			Book book1 = new Book("title", "author", 2019, "isbn", (float) 99.99);
			Book book2 = new Book("Server Programming", "Juha", 2019, "SWD4TF021-3002", (float) 99.99);
			
			bookRepo.save(book1);
			bookRepo.save(book2);
		};
	}
}