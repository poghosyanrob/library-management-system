package am.example.librarymanagementsystem.service.impl;

import am.example.librarymanagementsystem.model.Book;
import am.example.librarymanagementsystem.model.Category;
import am.example.librarymanagementsystem.repository.BookRepository;
import am.example.librarymanagementsystem.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;


    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findByCategory(Category category) {
        return bookRepository.findByCategory(category);
    }

    @Override
    public List<Book> searchBooks(String value) {
        return bookRepository.searchBooks(value);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public  void deleteById(Integer id){
        bookRepository.deleteById(id);
    }

}
