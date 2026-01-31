package am.example.librarymanagementsystem.controller;


import am.example.librarymanagementsystem.model.Book;
import am.example.librarymanagementsystem.model.Category;
import am.example.librarymanagementsystem.repository.BookRepository;
import am.example.librarymanagementsystem.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping("/book")
    public String books(ModelMap modelMap,
                        @RequestParam(required = false) Integer categoryId){
        modelMap.addAttribute("categories", categoryRepository.findAll());
        List<Book> result;
        if(categoryId != null){
            Category category = categoryRepository.getOne(categoryId);
            modelMap.addAttribute("selectedCategoryId", categoryId);
            result = bookRepository.findByCategory(category);
        }else{
            result = bookRepository.findAll();
        }
        modelMap.addAttribute("books", result);
        return "book/book";
    }

    @PostMapping("/book")
    public String books(ModelMap modelMap,@RequestParam(required = false) String value){
        List<Book> result;
        if (value == null || value.isBlank()) {
            result = bookRepository.findAll();
        } else {
            result = bookRepository.searchBooks(value);
        }
        modelMap.addAttribute("books", result);
        return "book/book";
    }

    @GetMapping("/book/add")
    public String addBook(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryRepository.findAll());
        return "book/addBook";
    }

    @PostMapping("/book/add")
    public String addBook(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/book";

    }

    @GetMapping("/book/delete")
    public String deleteBook(@RequestParam("id") int id) {
        bookRepository.deleteById(id);
        return "redirect:/book";
    }
}
