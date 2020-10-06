package ru.todolist.backendspringboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.todolist.backendspringboot.entity.StatEntity;
import ru.todolist.backendspringboot.repo.StatRepository;

import java.util.List;

@RestController
public class StatController {

    private StatRepository statRepository;

    public StatController(StatRepository statRepository) {
        this.statRepository = statRepository;
    }

    private final Long defaultId = 1l;

    @GetMapping("/stat")
    public ResponseEntity<StatEntity> findById() {
        return ResponseEntity.ok(statRepository.findById(defaultId).get());
    }
}
