package fi.haagahelia.Bookstore.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long categoryId;
	
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="category")
	private List<Book> books;
	
	public Category() {}
	
	public Category(String name) {
		this.name = name;
	}
	
	public Long getCategoryId() {
		return categoryId;
	}
	public String getName() {
		return name;
	}
	public List<Book> getBooks() {
		return books;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
}
