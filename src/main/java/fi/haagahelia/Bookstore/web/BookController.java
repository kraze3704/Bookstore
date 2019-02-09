package fi.haagahelia.Bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.Bookstore.domain.Book;
import fi.haagahelia.Bookstore.domain.BookRepository;
import fi.haagahelia.Bookstore.domain.CateRepository;
import fi.haagahelia.Bookstore.domain.Category;

@Controller
public class BookController {

	@Autowired
	private BookRepository bookRepo;
	@Autowired
	private CateRepository cateRepo;
	
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
		model.addAttribute("Categories", cateRepo.findAll());
		return "addBook";
	}
	
	@PostMapping("/addBook")
	public String addBookPOST(Book book) {
		bookRepo.save(book);
		return "redirect:booklist";
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteBook(@PathVariable("id") Long bookId) {
		bookRepo.deleteById(bookId);
		return "redirect:../booklist";
	}
	
	@RequestMapping("/edit/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("Book", bookRepo.findById(bookId));
		model.addAttribute("Categories", cateRepo.findAll());
		return "editBook";
	}
	
	@RequestMapping("/bookREST")
	public @ResponseBody List<Book> bookListREST() {
		return (List<Book>) bookRepo.findAll();
	}
	
	@RequestMapping("/bookREST/{id}")
	public @ResponseBody Optional<Book> findBookREST(@PathVariable("id") Long bookId) {
		return bookRepo.findById(bookId);
	}
	
	/*
	 * works?
	 * 
	 * curl -i -X POST -H "content-type:application/json" -d '{ "title": "for testing", "author": "Paul", "year": 2019, "isbn": "123456", "price": 22.22 }' http://localhost:8080/api/books
	*/
	
	@Bean
	public CommandLineRunner demo() {
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
}