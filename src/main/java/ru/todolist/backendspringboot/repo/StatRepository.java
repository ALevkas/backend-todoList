package ru.todolist.backendspringboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.todolist.backendspringboot.entity.StatEntity;

import java.util.List;

public interface StatRepository extends JpaRepository<StatEntity, Long> {
}
