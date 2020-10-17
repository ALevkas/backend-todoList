package ru.todolist.backendspringboot.services;

import org.springframework.stereotype.Service;
import ru.todolist.backendspringboot.entity.PriorityEntity;
import ru.todolist.backendspringboot.repo.PriorityRepository;

import javax.transaction.Transactional;
import java.util.List;

// всегда нужно создавать отдельный класс Service для доступа к данным, даже если кажется,
// что мало методов или это все можно реализовать сразу в контроллере
// Такой подход полезен для будущих доработок и правильной архитектуры (особенно, если работаете с транзакциями)
@Service

// все методы класса должны выполниться без ошибки, чтобы транзакция завершилась
// если в методе возникнет исключение - все выполненные операции откатятся (Rollback)
@Transactional
public class PriorityService {

    private final PriorityRepository repository; // сервис имеет право обращаьтся к репозиторию (БД)

    public PriorityService(PriorityRepository repository) {
        this.repository = repository;
    }

    public List<PriorityEntity> findAll() {
        return repository.findAllByOrderByIdAsc();
    }

    public PriorityEntity add(PriorityEntity priority) {
        return repository.save(priority); // метод save обновляет или создает новый объект, если его не было
    }

    public PriorityEntity update(PriorityEntity priority){
        return repository.save(priority); // метод save обновляет или создает новый объект, если его не было
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public PriorityEntity findById(Long id){
        return repository.findById(id).get(); // т.к. возвращается Optional - нужно получить объект методом get()
    }

    public List<PriorityEntity> findByTitle(String text){
        return repository.findByTitle(text);
    }

}
