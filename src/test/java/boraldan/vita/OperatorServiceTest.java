package boraldan.vita;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import boraldan.vita.domen.Person;
import boraldan.vita.domen.Request;
import boraldan.vita.domen.RequestStatus;
import boraldan.vita.repository.RequestRepository;
import boraldan.vita.service.OperatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OperatorServiceTest {

    @Mock
    private RequestRepository requestRepository;

    @InjectMocks
    private OperatorService operatorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void testGetRequestsByUserName() {
        Request request1 = new Request();
        request1.setStatus(RequestStatus.SENT);
        request1.setPerson(new Person());
        request1.getPerson().setName("Test User");

        List<Request> requests = Arrays.asList(request1);
        Page<Request> requestPage = new PageImpl<>(requests);

        when(requestRepository.findByPersonNameIgnoreCaseContaining(anyString(), any(Pageable.class))).thenReturn(requestPage);

        Page<Request> result = operatorService.getRequestsByUserName("Test", "asc", 0, 5);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(requestRepository, times(1)).findByPersonNameIgnoreCaseContaining(anyString(), any(Pageable.class));
    }

    @Test
    void testAcceptRequest() {
        Long requestId = 1L;
        Request request = new Request();
        request.setRequestId(requestId);
        request.setStatus(RequestStatus.SENT);

        when(requestRepository.findById(requestId)).thenReturn(Optional.of(request));

        operatorService.acceptRequest(requestId);

        assertEquals(RequestStatus.ACCEPTED, request.getStatus());
        verify(requestRepository, times(1)).save(request);
    }

    @Test
    void testRejectRequest() {
        Long requestId = 1L;
        Request request = new Request();
        request.setRequestId(requestId);
        request.setStatus(RequestStatus.SENT);

        when(requestRepository.findById(requestId)).thenReturn(Optional.of(request));

        operatorService.rejectRequest(requestId);

        assertEquals(RequestStatus.REJECTED, request.getStatus());
        verify(requestRepository, times(1)).save(request);
    }
}
