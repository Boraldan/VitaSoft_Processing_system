package boraldan.vita.service;


import boraldan.vita.domen.Person;
import boraldan.vita.repository.PersonRepository;
import boraldan.vita.security.PersonDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Сервис для загрузки информации о пользователе по его имени пользователя.
 */
@Service
@RequiredArgsConstructor
public class PersonDetailsService implements UserDetailsService {

    private final PersonRepository personRepository;


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
      Optional<Person> person = personRepository.findByNameIgnoreCase(name);
        if (person.isEmpty()) throw new UsernameNotFoundException("User not found");
        return new PersonDetails(person.get());
    }
}
