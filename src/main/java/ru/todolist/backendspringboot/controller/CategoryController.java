package ru.todolist.backendspringboot.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.todolist.backendspringboot.entity.CategoryEntity;
import ru.todolist.backendspringboot.entity.PriorityEntity;
import ru.todolist.backendspringboot.repo.CategoryRepository;
import ru.todolist.backendspringboot.search.CategorySearchValues;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/all")
    public List<CategoryEntity> findAll() {

        return  categoryRepository.findAllByOrderByIdAsc();
    }

    @PostMapping("/add")
    public ResponseEntity<CategoryEntity> add(@RequestBody CategoryEntity category) {

        if(category.getId() != null && category.getId() != 0) {
            return new ResponseEntity("Error: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if(category.getTitle() == null && category.getTitle().trim().length() == 0) {
            return new ResponseEntity("Error: title MUST NOT be null", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(categoryRepository.save(category));
    }

    @PutMapping("/edit")
    public ResponseEntity<CategoryEntity> edit(@RequestBody CategoryEntity category) {

        if (category.getId() == null && category.getId() == 0) {
            return new ResponseEntity("Error: id MUST NOT be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if(category.getTitle() == null && category.getTitle().trim().length() == 0) {
            return new ResponseEntity("Error: title MUST NOT be null", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(categoryRepository.save(category));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CategoryEntity> findById(@PathVariable Long id) {

        CategoryEntity category = null;

        try {
            category = categoryRepository.findById(id).get();
        }
        catch (NoSuchElementException e) {
            e.printStackTrace();
            return new ResponseEntity("id: " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CategoryEntity> deleteById(@PathVariable Long id) {

        try {
            categoryRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id: " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<CategoryEntity>> search(@RequestBody CategorySearchValues categorySearchValues) {

        return ResponseEntity.ok(categoryRepository.findByTitle(categorySearchValues.getText()));
    }
}
