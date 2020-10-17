package ru.todolist.backendspringboot.services;

import org.springframework.stereotype.Service;
import ru.todolist.backendspringboot.entity.CategoryEntity;
import ru.todolist.backendspringboot.repo.CategoryRepository;

import javax.transaction.Transactional;
import java.util.List;

// всегда нужно создавать отдельный класс Service для доступа к данным, даже если кажется,
// что мало методов или это все можно реализовать сразу в контроллере
// Такой подход полезен для будущих доработок и правильной архитектуры (особенно, если работаете с транзакциями)
@Service

// все методы класса должны выполниться без ошибки, чтобы транзакция завершилась
// если в методе возникнет исключение - все выполненные операции откатятся (Rollback)
@Transactional
public class CategoryService {

    private final CategoryRepository repository; // сервис имеет право обращаьтся к репозиторию (БД)

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }


    public List<CategoryEntity> findAll() {
        return repository.findAll();
    }

    public CategoryEntity add(CategoryEntity category) {
        return repository.save(category); // метод save обновляет или создает новый объект, если его не было
    }

    public CategoryEntity update(CategoryEntity category){
        return repository.save(category); // метод save обновляет или создает новый объект, если его не было
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public List<CategoryEntity> findByTitle(String text){
        return repository.findByTitle(text);
    }

    public CategoryEntity findById(Long id){
        return repository.findById(id).get(); // т.к. возвращается Optional - нужно получить объект методом get()
    }

    public List<CategoryEntity> findAllByOrderByTitleAsc(){
        return repository.findAllByOrderByTitleAsc();
    }


}
