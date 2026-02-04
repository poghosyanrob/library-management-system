package am.example.librarymanagementsystem.service.impl;

import am.example.librarymanagementsystem.model.Category;
import am.example.librarymanagementsystem.repository.CategoryRepository;
import am.example.librarymanagementsystem.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
   private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category getOne(Integer id) {
        return categoryRepository.getOne(id);
    }

}
