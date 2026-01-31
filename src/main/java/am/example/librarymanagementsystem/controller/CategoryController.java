package am.example.librarymanagementsystem.controller;

import am.example.librarymanagementsystem.model.Category;
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
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @GetMapping("/category")
    public String categories(ModelMap modelMap) {
        List<Category> categories = categoryRepository.findAll();
        modelMap.addAttribute("categories", categories);
        return "category/category";
    }

    @GetMapping("/category/add")
    public String addCategory() {
        return "category/addCategory";
    }

    @PostMapping("/category/add")
    public String addCategory(@ModelAttribute Category category) {
        categoryRepository.save(category);
        return "redirect:/category";

    }

    @GetMapping("/category/delete")
    public String deleteCategory(@RequestParam("id") int id) {
        categoryRepository.deleteById(id);
        return "redirect:/category";
    }
}
