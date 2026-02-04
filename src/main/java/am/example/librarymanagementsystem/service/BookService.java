package am.example.librarymanagementsystem.service;

import am.example.librarymanagementsystem.model.Book;
import am.example.librarymanagementsystem.model.Category;

import java.util.List;

public interface BookService {

    List<Book> findAll();
    List<Book> findByCategory(Category category);
    List<Book> searchBooks(String value);
    Book save(Book book);
    void deleteById(Integer id);

}
