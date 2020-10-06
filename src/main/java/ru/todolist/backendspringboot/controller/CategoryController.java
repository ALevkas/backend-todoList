package ru.todolist.backendspringboot.controller;

import org.springframework.web.bind.annotation.*;
import ru.todolist.backendspringboot.entity.CategoryEntity;
import ru.todolist.backendspringboot.repo.CategoryRepository;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/test")
    public List<CategoryEntity> test() {

        return  categoryRepository.findAll();
    }

    @PostMapping("/add")
    public CategoryEntity add(@RequestBody CategoryEntity category) {
        return categoryRepository.save(category);
    }
}
