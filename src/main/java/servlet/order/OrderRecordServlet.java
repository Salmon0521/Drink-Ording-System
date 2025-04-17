package servlet.order;

import bean.product.Product;
import com.google.gson.Gson;
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
        name = "OrderRecordServlet",
        urlPatterns = {"/OrderRecord"}
)
public class OrderRecordServlet extends HttpServlet {
    private static final String ORDER_RECORD_URL = "WEB-INF/jsp/orderRecord/OrderRecord.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String account = String.valueOf(session.getAttribute("account"));
        if (account == null) {
            response.sendRedirect("Login");
            return;
        }
        request.getRequestDispatcher(ORDER_RECORD_URL).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String account = String.valueOf(session.getAttribute("account"));
        String phone =  String.valueOf(session.getAttribute("phone"));

        String date = request.getParameter("date");
        session.setAttribute("date", date);

        OrdersService ordersService = new OrdersServiceImpl();
        List<Product> productList = ordersService.getOrdersByDate(account, phone, date);
        String productInorderJson = new Gson().toJson(productList);
        session.setAttribute("productInorderJson", productInorderJson);
    }
}
