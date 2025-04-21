package controller.login;

import bean.user.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import service.customer.CustomerService;
import service.customer.CustomerServiceImpl;

import jakarta.servlet.http.HttpSession;

@RestController
public class LoginController {

    private final CustomerService customerService = new CustomerServiceImpl();

    @GetMapping("/Login")
    public ModelAndView showLoginPage() {
        return new ModelAndView("login/Login");
    }

    @PostMapping("/Login")
    public String login(@RequestParam("Account") String account, @RequestParam("Password") String password, HttpSession session) {

        User user = customerService.login(account, password);
        if (user != null) {
            session.setAttribute("account", account);
            session.setAttribute("phone", user.getPhone());
            session.setAttribute("level", user.getLevel());
            return "0";
        }
        else {
            return "1";
        }
    }
}
