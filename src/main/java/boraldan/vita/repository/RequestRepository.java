package boraldan.vita.repository;

import boraldan.vita.domen.Person;
import boraldan.vita.domen.Request;
import boraldan.vita.domen.RequestStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findByPerson_Email(String email, Pageable pageable);

    Page<Request> findByPerson(Person person, Pageable pageable);

    Optional<Request> findByRequestIdAndPerson(Long id, Person person);

    Page<Request> findAllBy(Pageable pageable);

    Page<Request> findAllByStatus(RequestStatus status, Pageable pageable);

    Page<Request> findByPersonNameIgnoreCaseContaining(String name, Pageable pageable);
}
