package boraldan.vita.controller;

import boraldan.vita.domen.Person;
import boraldan.vita.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.security.Principal;
import java.util.Optional;

@Log4j2
@Controller
@RequiredArgsConstructor
public class AuthorizeController {

    private final PersonService personService;

    @GetMapping("/auth/login")
    public String loginPage(Model model,
                            @RequestParam(name = "invalid", required = false) String invalid) {
        if (invalid != null) {
            model.addAttribute("errorMessage", "Сессия недействительна. Пожалуйста, войдите снова.");
        }
        return "/auth/login";
    }

    @GetMapping("/account")
    public String viewAccount(Model model,
                              Principal principal) {

        if (principal == null) {
            return "redirect:/auth/login";
        }

        Optional<Person> person = personService.findByNameIgnoreCase(principal.getName());

        if (person.isEmpty()) {
            model.addAttribute("errorMessage", "Пользователь не найден.");
            return "account";
        }

        model.addAttribute("person", person.get());
        return "account";
    }

}
