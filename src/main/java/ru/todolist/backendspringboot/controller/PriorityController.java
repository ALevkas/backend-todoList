package ru.todolist.backendspringboot.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.todolist.backendspringboot.entity.CategoryEntity;
import ru.todolist.backendspringboot.entity.PriorityEntity;
import ru.todolist.backendspringboot.repo.PriorityRepository;
import ru.todolist.backendspringboot.search.CategorySearchValues;
import ru.todolist.backendspringboot.search.PrioritySearchValues;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/priority")
public class PriorityController {

    private PriorityRepository  priorityRepository;

    public PriorityController(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @GetMapping("/all")
    public List<PriorityEntity> findAll() {

        return priorityRepository.findAllByOrderByIdAsc();
    }

    @PostMapping("/add")
    public ResponseEntity<PriorityEntity> add(@RequestBody PriorityEntity priority) {

        if (priority.getId() != null && priority.getId() != 0) {
            return new ResponseEntity("Error: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getTitle() == null && priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("Error: title MUST NOT be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getColor() == null && priority.getColor().trim().length() == 0) {
            return new ResponseEntity("Error: color MUST NOT be null", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priorityRepository.save(priority));
    }

    @PutMapping("/edit")
    public ResponseEntity<PriorityEntity> edit(@RequestBody PriorityEntity priority) {

        if (priority.getId() == null && priority.getId() == 0) {
            return new ResponseEntity("Error: id MUST NOT be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getTitle() == null && priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("Error: title MUST NOT be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getColor() == null && priority.getColor().trim().length() == 0) {
            return new ResponseEntity("Error: color MUST NOT be null", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priorityRepository.save(priority));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PriorityEntity> findById(@PathVariable Long id) {

        PriorityEntity priority = null;

        try {
            priority = priorityRepository.findById(id).get();
        }
        catch (NoSuchElementException e) {
            e.printStackTrace();
            return new ResponseEntity("id: " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priority);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PriorityEntity> deleteById(@PathVariable Long id) {

        try {
            priorityRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id: " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<PriorityEntity>> search(@RequestBody PrioritySearchValues prioritySearchValues) {

        return ResponseEntity.ok(priorityRepository.findByTitle(prioritySearchValues.getText()));
    }
}
