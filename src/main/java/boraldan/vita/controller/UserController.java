package boraldan.vita.controller;

import boraldan.vita.domen.Request;
import boraldan.vita.service.PersonService;
import boraldan.vita.service.RequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final RequestService requestService;

    // Просмотр заявок пользователя с сортировкой и пагинацией
    @GetMapping("/requests")
    public String listRequests(@AuthenticationPrincipal UserDetails userDetails,
                               @RequestParam(defaultValue = "0") int pageNumber,
                               @RequestParam(defaultValue = "asc") String sort,
                               Model model) {

        Page<Request> requests = requestService.getUserRequests(userDetails.getUsername(), sort, pageNumber, 5);
        model.addAttribute("requests", requests);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("totalPages", requests.getTotalPages());
        model.addAttribute("sort", sort);
        return "/user/user";
    }

    // Страница создания заявки
    @GetMapping("/request/create")
    public String showCreateForm(Model model) {
        model.addAttribute("request", new Request());
        return "/request/create";
    }

    // Обработка создания заявки
    @PostMapping("/request/create")
    public String createRequest(@AuthenticationPrincipal UserDetails userDetails,
                                @ModelAttribute("request") @Valid Request request,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("request", request);
            return "/request/create";
        }

        requestService.createRequest(request, userDetails.getUsername());
        return "redirect:/user/requests";
    }

    // Страница редактирования заявки
    @GetMapping("/request/edit/{id}")
    public String showEditForm(@AuthenticationPrincipal UserDetails userDetails,
                               @PathVariable Long id, Model model) {

        Request request = requestService.getByRequestIdAndPerson(id, userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Заявка не найдена или недоступна для редактирования"));
        model.addAttribute("request", request);
        return "/request/edit";
    }

    // Обработка редактирования заявки
    @PostMapping("/request/edit/{id}")
    public String editRequest(@AuthenticationPrincipal UserDetails userDetails,
                              @PathVariable Long id,
                              @ModelAttribute("request") @Valid Request newRequest,
                              BindingResult bindingResult,
                              Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("request", newRequest);
            return "/request/edit";
        }

        requestService.editRequest(id, newRequest, userDetails.getUsername());
        return "redirect:/user/requests";
    }

    // Отправка заявки на рассмотрение
    @PostMapping("/request/send/{id}")
    public String sendRequest(@AuthenticationPrincipal UserDetails userDetails,
                              @PathVariable Long id) {

        requestService.sendRequest(id, userDetails.getUsername());
        return "redirect:/user/requests";
    }

    // Метод для просмотра заявки
    @GetMapping("/request/view/{id}")
    public String viewRequest(@AuthenticationPrincipal UserDetails userDetails,
                              @PathVariable Long id,
                              Model model) {

        Request request = requestService.getByRequestIdAndPerson(id, userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Заявка не найдена или недоступна для просмотра"));

        model.addAttribute("request", request);
        return "/request/view";
    }
}
