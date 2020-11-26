package tsoy.sergey.springCrudSecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tsoy.sergey.springCrudSecurity.models.Role;
import tsoy.sergey.springCrudSecurity.models.User;
import tsoy.sergey.springCrudSecurity.services.RoleService;
import tsoy.sergey.springCrudSecurity.services.UserService;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String user(Principal principal, Model model) {
        String name = principal.getName();
        User admin = userService.getByName(name);
        model.addAttribute("admin", admin);
        return "admin";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "userById";
    }

//    @GetMapping("/newUser") // получаем форму для создания нового юзера
//    public String newUser(Model model) { // через пустого new User()
//        model.addAttribute("user", new User());
//        return "/new";
//    }

    //    @GetMapping("/users/newUser") // получаем форму для создания нового юзера
//    public String newUser(@ModelAttribute("user") User user) {// то же самое через @ModelAttribute
//        return "newUser";
//    }
    @GetMapping("/users/newUser")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAll());
        return "newUser";
    }

//    @PostMapping("/users")
//    public String addUser(@ModelAttribute("user") User user) {
//        userService.add(user);
//        return "redirect:/admin/users";
//    }

    @PostMapping("/users")
    public String create(@ModelAttribute("user") User user, @RequestParam(value = "roles") String[] roles){
        Set<Role> rolesSet = new HashSet<>();
        for (String roleName : roles){
            rolesSet.add(roleService.getByName(roleName));
        }
        user.setRoles(rolesSet);
        userService.add(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getById(id));
        return "edit";
    }

    @PatchMapping("/users/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.update(id, user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }

    @ModelAttribute("headerMessage") // @ModelAttribute на методе делает доступной в каждой модели класса пару:
    public String header() {         // key("headerMessage") <--> value("Task 2.3.1 (spring-crud-231)")
        return "Task springCrudSecurity-242"; // при необходимости можно вывести ее на странице в браузере
    }
}




