package ru.todolist.backendspringboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.todolist.backendspringboot.entity.CategoryEntity;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    List<CategoryEntity> findAllByOrderByIdAsc();

    @Query("SELECT c FROM CategoryEntity c WHERE " +
            "(:title is null or :title = '' or lower(c.title) like lower(concat('%', :title, '%') ) ) " +
            "order by c.title asc")
    List<CategoryEntity> findByTitle(@Param("title") String title);

}
