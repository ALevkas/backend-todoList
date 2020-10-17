package ru.todolist.backendspringboot.services;

import org.springframework.stereotype.Service;
import ru.todolist.backendspringboot.entity.StatEntity;
import ru.todolist.backendspringboot.repo.StatRepository;

import javax.transaction.Transactional;

// всегда нужно создавать отдельный класс Service для доступа к данным, даже если кажется,
// что мало методов или это все можно реализовать сразу в контроллере
// Такой подход полезен для будущих доработок и правильной архитектуры (особенно, если работаете с транзакциями)
@Service

// все методы класса должны выполниться без ошибки, чтобы транзакция завершилась
// если в методе возникнет исключение - все выполненные операции откатятся (Rollback)
@Transactional
public class StatService {

    private final StatRepository repository; // сервис имеет право обращаьтся к репозиторию (БД)

    public StatService(StatRepository repository) {
        this.repository = repository;
    }

    public StatEntity findById(Long id){
        return repository.findById(id).get();
    }


}
