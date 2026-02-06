package am.example.librarymanagementsystem.controller;


import am.example.librarymanagementsystem.model.Book;
import am.example.librarymanagementsystem.model.Category;
import am.example.librarymanagementsystem.service.BookService;
import am.example.librarymanagementsystem.service.CategoryService;
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

    private final BookService bookService;
    private final CategoryService categoryService;

    @GetMapping("/book")
    public String books(ModelMap modelMap,
                        @RequestParam(required = false) Integer categoryId){
        modelMap.addAttribute("categories", categoryService.findAll());
        List<Book> result;
        if(categoryId != null){
            Category category = categoryService.getOne(categoryId);
            modelMap.addAttribute("selectedCategoryId", categoryId);
            result = bookService.findByCategory(category);
        }else{
            result = bookService.findAll();
        }
        modelMap.addAttribute("books", result);
        return "book/book";
    }

    @PostMapping("/book")
    public String books(ModelMap modelMap,@RequestParam(required = false) String value){
        List<Book> result;
        if (value == null || value.isBlank()) {
            result = bookService.findAll();
        } else {
            result = bookService.searchBooks(value);
        }
        modelMap.addAttribute("categories", categoryService.findAll());
        modelMap.addAttribute("books", result);
        return "book/book";
    }

    @GetMapping("/book/add")
    public String addBook(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryService.findAll());
        return "book/addBook";
    }

    @PostMapping("/book/add")
    public String addBook(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/book";

    }

    @GetMapping("/book/delete")
    public String deleteBook(@RequestParam("id") int id) {
        bookService.deleteById(id);
        return "redirect:/book";
    }
}
