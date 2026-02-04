package am.example.librarymanagementsystem.service;

import am.example.librarymanagementsystem.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();
    Category save(Category category);
    void deleteById(Integer id);
    Category getOne(Integer id);

}
