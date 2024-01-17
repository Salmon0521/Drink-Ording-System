package servlet.menu;

import service.cartService.CartService;
import service.cartService.CartServiceImpl;
import service.customerService.CustomerService;
import service.customerService.CustomerServiceImpl;
import service.ordersService.OrdersService;
import service.ordersService.OrdersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        name = "MenuServlet",
        urlPatterns = {"/CustomerMenu"}
)
public class MenuServlet extends HttpServlet {
    private static final String MENU_URL = "WEB-INF/jsp/customer/Menu.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String account = (String) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect("Login");
            return;
        }
        CartService cartService = new CartServiceImpl();
        cartService.showCart(session, account);
        request.getRequestDispatcher(MENU_URL).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String account = (String) session.getAttribute("account");

        String ud = request.getParameter("ud");
        switch (ud) {
            case "sendOrder":
                Integer amount =  Integer.parseInt(request.getParameter("amount"));
                OrdersService ordersService = new OrdersServiceImpl();
                ordersService.buildOrder(account, amount);
                CustomerService customerService = new CustomerServiceImpl();
                customerService.updateLevel(session, account);
                break;
            case "delete":
                Integer productID = Integer.parseInt(request.getParameter("productID"));
                CartService cartService = new CartServiceImpl();
                cartService.deleteProduct(account, productID);
                break;
            default:
                break;
        }
    }
}
