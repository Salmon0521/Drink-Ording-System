package controller.logout;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {

    @PostMapping("/Logout")
    public String doPost(HttpSession session) {
        session.invalidate();
        return "redirect:/Login";
    }
}
