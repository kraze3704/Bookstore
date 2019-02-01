package fi.haagahelia.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@RequestMapping("/addBook")
	public String addBook(Model model) {
		model.addAttribute("Book", new Book());
		return "addBook";
	}
	
	@PostMapping("/addBook")
	public String addBookPOST(Book book) {
		bookRepo.save(book);
		return "redirect:booklist";
	}
	
	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			Book book1 = new Book("Server Programming", "Juha", 2019, "SWD4TF021", (float) 30.02);
			Book book2 = new Book("Database Developer", "Kari", 2019, "SWD8TF040", (float) 30.02);
			Book book3 = new Book("Software Project", "Juhani", 2019, "SWD4TF024", (float) 30.01);
			
			bookRepo.save(book1);
			bookRepo.save(book2);
			bookRepo.save(book3);
		};
	}
}