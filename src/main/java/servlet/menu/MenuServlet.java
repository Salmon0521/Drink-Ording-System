package servlet.menu;

import bean.product.Product;
import com.google.gson.Gson;
import service.cart.CartService;
import service.cart.CartServiceImpl;
import service.customer.CustomerService;
import service.customer.CustomerServiceImpl;
import service.orders.OrdersService;
import service.orders.OrdersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(
        name = "MenuServlet",
        urlPatterns = {"/CustomerMenu"}
)
public class MenuServlet extends HttpServlet {
    private static final String MENU_URL = "WEB-INF/jsp/customer/Menu.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String account = String.valueOf(session.getAttribute("account"));
        String phone =  String.valueOf(session.getAttribute("phone"));

        if (account == null) {
            response.sendRedirect("Login");
            return;
        }

        CartService cartService = new CartServiceImpl();
        List<Product> customerCart = cartService.showCart(account, phone);

        Integer totalPrice = 0;
        for (Product product : customerCart) {
            totalPrice += product.getPrice();
        }

        session.setAttribute("customerCart", new Gson().toJson(customerCart));
        session.setAttribute("totalPrice", totalPrice);
        request.getRequestDispatcher(MENU_URL).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String account = String.valueOf(session.getAttribute("account"));

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
