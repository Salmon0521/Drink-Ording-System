package servlet.register;

import bean.user.User;
import org.mindrot.jbcrypt.BCrypt;
import service.customer.CustomerService;
import service.customer.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "RegisterServlet",
        urlPatterns = {"/Register"}
)
public class RegisterServlet extends HttpServlet {
    private static final String REGISTER_URL = "WEB-INF/jsp/register/Register.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(REGISTER_URL).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String registeredAccount = request.getParameter("R_Account");
        String registeredPassword = request.getParameter("R_Password");
        String registeredPhone = request.getParameter("R_Tel");

        String hashRegisteredPassword = BCrypt.hashpw(registeredPassword, BCrypt.gensalt());

        User user = new User(registeredAccount, hashRegisteredPassword, registeredPhone);
        CustomerService customerService = new CustomerServiceImpl();

        if (customerService.register(user)){
            response.getWriter().print("0");
        }
        else {
            response.getWriter().print("1");
        }

    }
}