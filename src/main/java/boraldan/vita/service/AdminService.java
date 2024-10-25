package boraldan.vita.service;

import boraldan.vita.domen.Person;
import boraldan.vita.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final PersonRepository personRepository;


    // Получение списка всех пользователей с пагинацией
    public Page<Person> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return personRepository.findAll(pageable);
    }

    // Поиск пользователей по имени или части имени с пагинацией
    public Page<Person> searchUsersByName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return personRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    // Назначение пользователю роли оператора
    public void assignOperatorRole(Long personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        if (!person.getOperatorRole()) {
            person.setOperatorRole(true);
            personRepository.save(person);
        }
    }


    // Отмена роли оператора у пользователя
    public void revokeOperatorRole(Long personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        if (person.getOperatorRole()) {
            person.setOperatorRole(false);
            personRepository.save(person);
        }
    }

}
