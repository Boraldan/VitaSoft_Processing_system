package boraldan.vita.controller;

import boraldan.vita.domen.Request;
import boraldan.vita.domen.RequestStatus;
import boraldan.vita.service.OperatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/operator")
@RequiredArgsConstructor
public class OperatorController {

    private final OperatorService operatorService;

    // Просмотр всех заявок со статусом кроме  DRAFT
    @GetMapping("/requests")
    public String listAllRequests(@RequestParam(defaultValue = "0") int pageNumber,
                                  @RequestParam(defaultValue = "asc") String sort,
                                  @RequestParam(defaultValue = "") String name,
                                  Model model) {
        Page<Request> requests = name.isBlank()
                ? operatorService.getAllSentRequests(sort, pageNumber, 5)
                : operatorService.getRequestsByUserName(name, sort, pageNumber, 5);

        List<Request> filteredRequests = requests.stream()
                .filter(request -> request.getStatus() != RequestStatus.DRAFT)
                .collect(Collectors.toList());

        List<String> formattedContents = requests.getContent().stream()
                .map(request -> formatContent(request.getContent()))
                .toList();


        model.addAttribute("requests", filteredRequests);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("formattedContents", formattedContents);
        model.addAttribute("totalPages", requests.getTotalPages());
        model.addAttribute("sort", sort);
        model.addAttribute("name", name);
        return "/operator/operator";
    }

    // Просмотр заявок конкретного пользователя с возможностью фильтрации по имени/части имени и сортировкой
    @GetMapping("/requests/search")
    public String searchRequestsByName(@RequestParam(defaultValue = "0") int pageNumber,
                                       @RequestParam(defaultValue = "asc") String sort,
                                       @RequestParam(value = "name", required = false) String name,
                                       Model model) {
        Page<Request> requests = operatorService.getRequestsByUserName(name, sort, pageNumber, 5);

        List<Request> filteredRequests = requests.stream()
                .filter(request -> request.getStatus() != RequestStatus.DRAFT)
                .collect(Collectors.toList());

        List<String> formattedContents = requests.getContent().stream()
                .map(request -> formatContent(request.getContent()))
                .toList();

        model.addAttribute("requests", filteredRequests);
        model.addAttribute("formattedContents", formattedContents);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("totalPages", requests.getTotalPages());
        model.addAttribute("sort", sort);
        model.addAttribute("name", name);
        return "/operator/operator";
    }

    // Принятие заявки
    @PostMapping("/requests/accept")
    public String acceptRequest(@ModelAttribute("requestId") Long requestId) {
        operatorService.acceptRequest(requestId);
        return "redirect:/operator/requests";
    }

    // Отклонение заявки
    @PostMapping("/requests/reject")
    public String rejectRequest(@ModelAttribute("requestId") Long requestId) {
        operatorService.rejectRequest(requestId);
        return "redirect:/operator/requests";
    }

    // Метод для форматирования текста с добавлением знака "<->" между символами
    private String formatContent(String content) {
        return content.chars()
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining("-"));
    }
}
