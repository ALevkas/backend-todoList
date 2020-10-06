package ru.todolist.backendspringboot.controller;

import org.springframework.web.bind.annotation.*;
import ru.todolist.backendspringboot.entity.CategoryEntity;
import ru.todolist.backendspringboot.entity.PriorityEntity;
import ru.todolist.backendspringboot.repo.PriorityRepository;

import java.util.List;

@RestController
@RequestMapping("/priority")
public class PriorityController {

    private PriorityRepository  priorityRepository;

    public PriorityController(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @GetMapping("/test")
    public List<PriorityEntity> test() {

        return priorityRepository.findAll();
    }

    @PostMapping("/add")
    public PriorityEntity add(@RequestBody PriorityEntity priority) {
        return priorityRepository.save(priority);
    }
}
