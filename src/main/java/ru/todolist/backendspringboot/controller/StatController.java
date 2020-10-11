package ru.todolist.backendspringboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.todolist.backendspringboot.entity.StatEntity;
import ru.todolist.backendspringboot.repo.StatRepository;
import ru.todolist.backendspringboot.services.StatService;

import java.util.List;

@RestController
public class StatController {

    private StatService statService;

    public StatController(StatRepository statRepository) {
        this.statService = statService;
    }

    private final Long defaultId = 1l;

    @GetMapping("/stat")
    public ResponseEntity<StatEntity> findById() {
        return ResponseEntity.ok(statService.findById(defaultId));
    }
}
