package boraldan.vita.service;

import boraldan.vita.domen.Person;
import boraldan.vita.domen.Request;
import boraldan.vita.domen.RequestStatus;
import boraldan.vita.repository.PersonRepository;
import boraldan.vita.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository requestRepository;
    private final PersonRepository personRepository;


    @Transactional
    public void createRequest(Request request, String username) {
       Optional< Person> person = personRepository.findByNameIgnoreCase(username);
        request.setPerson(person.get());
        request.setContent(request.getContent());
        request.setStatus(RequestStatus.DRAFT);
        request.setCreatedDate(LocalDateTime.now());
        requestRepository.save(request);
    }

    // Получение заявки
    public Optional<Request> getByRequestIdAndPerson(Long requestId, String username) {
        Optional< Person> person = personRepository.findByNameIgnoreCase(username);
        return requestRepository.findByRequestIdAndPerson(requestId, person.get());
    }

    // Получение заявок пользователя с пагинацией и сортировкой
    public Page<Request> getUserRequests(String username, String sort, int page, int size) {
        Optional< Person> person = personRepository.findByNameIgnoreCase(username);
        Sort.Direction direction = sort.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, "createdDate"));
        return requestRepository.findByPerson(person.get(), pageable);
    }

    @Transactional
    public void sendRequest(Long requestId, String username) {
        Optional< Person> person = personRepository.findByNameIgnoreCase(username);
        Request request = requestRepository.findByRequestIdAndPerson(requestId, person.get())
                .orElseThrow(() -> new RuntimeException("Заявка не найдена или недоступна для отправки"));
        if (request.getStatus() == RequestStatus.DRAFT) {
            request.setStatus(RequestStatus.SENT);
            requestRepository.save(request);
        } else {
            throw new RuntimeException("Невозможно отправить заявку, которая уже отправлена");
        }
    }

    // Редактирование заявки
    @Transactional
    public Request editRequest(Long requestId, Request newRequest, String username) {
        Optional< Person> person = personRepository.findByNameIgnoreCase(username);
        Request request = requestRepository.findByRequestIdAndPerson(requestId, person.get())
                .orElseThrow(() -> new RuntimeException("Заявка не найдена или недоступна для отправки"));
        request.setContent(newRequest.getContent());
        request.setUpdatedDate(LocalDateTime.now());
        return requestRepository.save(request);
    }


}

