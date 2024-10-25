package boraldan.vita.service;

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

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OperatorService {

    private final RequestRepository requestRepository;

    // Получение всех заявок со статусом SENT с сортировкой и пагинацией
    public Page<Request> getAllSentRequests(String sort, int page, int size) {
        Sort.Direction direction = sort.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, "createdDate"));
        return requestRepository.findAllBy(pageable);
    }

    // Поиск заявок по имени пользователя с сортировкой и пагинацией
    public Page<Request> getRequestsByUserName(String name, String sort, int page, int size) {
        Sort.Direction direction = sort.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, "createdDate"));
        return requestRepository.findByPersonNameIgnoreCaseContaining(name, pageable);
    }

    // Принятие заявки
    @Transactional
    public void acceptRequest(Long requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Заявка не найдена"));
        if (request.getStatus() == RequestStatus.SENT) {
            request.setStatus(RequestStatus.ACCEPTED);
            request.setUpdatedDate(LocalDateTime.now());
            requestRepository.save(request);
        } else {
            throw new RuntimeException("Заявка не в статусе 'отправлено', не может быть принята");
        }
    }

    // Отклонение заявки
    @Transactional
    public void rejectRequest(Long requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Заявка не найдена"));
        if (request.getStatus() == RequestStatus.SENT) {
            request.setStatus(RequestStatus.REJECTED);
            request.setUpdatedDate(LocalDateTime.now());
            requestRepository.save(request);
        } else {
            throw new RuntimeException("Заявка не в статусе 'отправлено', не может быть отклонена");
        }
    }
}
