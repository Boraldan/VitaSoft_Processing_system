package boraldan.vita.service;

import boraldan.vita.domen.Person;
import boraldan.vita.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Optional<Person> findByNameIgnoreCase(String name) {
        return personRepository.findByNameIgnoreCase(name);

    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

}