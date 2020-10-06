package ru.todolist.backendspringboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.todolist.backendspringboot.entity.PriorityEntity;

import javax.annotation.Priority;
import java.util.List;

@Repository
public interface PriorityRepository extends JpaRepository<PriorityEntity, Long> {

    List<PriorityEntity> findAllByOrderByIdAsc();

    @Query("SELECT p FROM PriorityEntity p WHERE " +
            "(:title is null or :title = '' or lower(p.title) like lower(concat('%', :title, '%') ) ) " +
            "order by p.title asc")
    List<PriorityEntity> findByTitle(@Param("title") String title);

}
