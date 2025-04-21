package controller.register;

import bean.user.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import service.customer.CustomerService;
import service.customer.CustomerServiceImpl;

import jakarta.servlet.ServletException;

import java.io.IOException;

@RestController
public class RegisterController {

    @GetMapping("/Register")
    public ModelAndView showRegisterView() {
        return new ModelAndView("register/Register");
    }

    @PostMapping("/Register")
    public String handleRegister(@RequestParam("R_Account") String registeredAccount,
                                       @RequestParam("R_Password") String registeredPassword,
                                       @RequestParam("R_Tel") String registeredPhone) throws ServletException, IOException {

        String hashRegisteredPassword = BCrypt.hashpw(registeredPassword, BCrypt.gensalt());
        User user = new User(registeredAccount, hashRegisteredPassword, registeredPhone);
        CustomerService customerService = new CustomerServiceImpl();

        if (customerService.register(user)){
            return "0";
        }
        else {
            return "1";
        }
    }
}