package ru.todolist.backendspringboot.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.todolist.backendspringboot.entity.CategoryEntity;
import ru.todolist.backendspringboot.entity.TaskEntity;
import ru.todolist.backendspringboot.repo.TaskRepository;
import ru.todolist.backendspringboot.search.CategorySearchValues;
import ru.todolist.backendspringboot.search.TaskSearchValues;
import ru.todolist.backendspringboot.services.TaskService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/task")
public class TaskController {

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    private final TaskService taskService;

    @GetMapping("/all")
    public ResponseEntity<List<TaskEntity>> findAll() {
        return ResponseEntity.ok(taskService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<TaskEntity> add(@RequestBody TaskEntity task) {

        if(task.getId() != null && task.getId() != 0) {
            return new ResponseEntity("Error: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if(task.getTitle() == null && task.getTitle().trim().length() == 0) {
            return new ResponseEntity("Error: title MUST NOT be null", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(taskService.add(task));
    }

    @PutMapping("/edit")
    public ResponseEntity edit(@RequestBody TaskEntity task) {

        if (task.getId() == null && task.getId() == 0) {
            return new ResponseEntity("Error: id MUST NOT be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if(task.getTitle() == null && task.getTitle().trim().length() == 0) {
            return new ResponseEntity("Error: title MUST NOT be null", HttpStatus.NOT_ACCEPTABLE);
        }
        taskService.update(task);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<TaskEntity> findById(@PathVariable Long id) {

        TaskEntity task = null;

        try {
            task = taskService.findById(id);
        }
        catch (NoSuchElementException e) {
            e.printStackTrace();
            return new ResponseEntity("id: " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TaskEntity> deleteById(@PathVariable Long id) {

        try {
            taskService.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id: " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<TaskEntity>> search(@RequestBody TaskSearchValues taskSearchValues) {

        String title = taskSearchValues.getTitle() != null ? taskSearchValues.getTitle() : null;
        Integer completed = taskSearchValues.getCompleted() != null ? taskSearchValues.getCompleted() : null;
        Long priorityId = taskSearchValues.getPriorityId() != null ? taskSearchValues.getPriorityId() : null;
        Long categoryId = taskSearchValues.getCategoryId() != null ? taskSearchValues.getCategoryId() : null;

        String sortColumn = taskSearchValues.getSortColumn() != null ? taskSearchValues.getSortColumn() : null;
        String sortDirection = taskSearchValues.getSortDirection() != null ? taskSearchValues.getSortDirection() : null;

        Integer pageNumber = taskSearchValues.getPageNumber() != null ? taskSearchValues.getPageNumber() : null;
        Integer pageSize = taskSearchValues.getPageSize() != null ? taskSearchValues.getPageSize() : null;

        Sort.Direction direction = sortDirection == null || sortDirection.trim().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        Sort sort = Sort.by(direction, sortColumn);

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page result = taskService.findByParams(title, completed, priorityId, categoryId, pageRequest);

        return ResponseEntity.ok(result);
    }
}
