package boraldan.vita.controller;

import boraldan.vita.domen.Person;
import boraldan.vita.service.AdminService;
import boraldan.vita.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // Просмотр списка всех пользователей
    @GetMapping("/users")
    public String listAllUsers(@RequestParam(defaultValue = "0") int pageNumber,
                               Model model) {
        Page<Person> users = adminService.getAllUsers(pageNumber, 10);

        model.addAttribute("users", users);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("totalPages", users.getTotalPages());
        return "/admin/admin";
    }

    // Поиск пользователя по имени или части имени
    @GetMapping("/users/search")
    public String searchUserByName(@RequestParam(value = "name", required = false) String name,
                                   @RequestParam(defaultValue = "0") int pageNumber,
                                   Model model) {
        Page<Person> users = adminService.searchUsersByName(name, pageNumber, 10);

        model.addAttribute("users", users);
        model.addAttribute("name", name);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("totalPages", users.getTotalPages());

        return "/admin/admin";
    }

    // Назначение роли оператора пользователю
    @PostMapping("/users/{id}/assign-operator")
    public String assignOperatorRole(@PathVariable Long id) {
        adminService.assignOperatorRole(id);
        return "redirect:/admin/users";
    }

    // Отмена роли оператора у пользователя
    @PostMapping("/users/{id}/revoke-operator")
    public String revokeOperatorRole(@PathVariable Long id) {
        adminService.revokeOperatorRole(id);
        return "redirect:/admin/users";
    }
}