package tsoy.sergey.springCrudSecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import tsoy.sergey.springCrudSecurity.models.User;
import tsoy.sergey.springCrudSecurity.services.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService1) {
        this.userService = userService1;
    }

    @GetMapping
    public String user(Principal principal, Model model) {
        String name = principal.getName();
        User user = userService.getByName(name);
        model.addAttribute("user", user);
        return "user";
    }

    @ModelAttribute("headerMessage") // @ModelAttribute на методе делает доступной в каждой модели класса пару:
    public String header() {         // key("headerMessage") <--> value("Task 2.3.1 (spring-crud-231)")
        return "Task springCrudSecurity-242"; // при необходимости можно вывести ее на странице в браузере
    }

}
