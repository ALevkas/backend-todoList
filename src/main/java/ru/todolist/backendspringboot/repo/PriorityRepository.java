package ru.todolist.backendspringboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.todolist.backendspringboot.entity.PriorityEntity;

import javax.annotation.Priority;

@Repository
public interface PriorityRepository extends JpaRepository<PriorityEntity, Long> {
}
