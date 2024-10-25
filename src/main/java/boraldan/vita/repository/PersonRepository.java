package boraldan.vita.repository;

import boraldan.vita.domen.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByNameIgnoreCase(String name);

    Page<Person> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
