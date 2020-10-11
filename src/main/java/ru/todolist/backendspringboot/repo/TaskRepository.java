package ru.todolist.backendspringboot.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.todolist.backendspringboot.entity.TaskEntity;
import ru.todolist.backendspringboot.entity.TaskEntity;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    @Query("SELECT t FROM TaskEntity t WHERE " +
            "(:title is null or :title = '' or lower(t.title) like lower(concat('%', :title, '%') ) )  and" +
            "(:completed is null or t.completed=:completed) and" +
            "(:priorityId is null or t.priority.id=:priorityId) and" +
            "(:categoryId is null or t.category.id=:categoryId)"
    )
    Page<TaskEntity> findByParams(
            @Param("title") String title,
            @Param("completed") Integer completed,
            @Param("priorityId") Long priorityId,
            @Param("categoryId") Long categoryId,
            Pageable pageable);
}
