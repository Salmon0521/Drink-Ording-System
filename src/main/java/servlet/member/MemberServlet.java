package servlet.member;

import bean.order.Order;
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
        name = "MemberServlet",
        urlPatterns = {"/Member"}
)
public class MemberServlet extends HttpServlet {
    private static final String MEMBER_URL = "WEB-INF/jsp/member/Member.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String account = String.valueOf(session.getAttribute("account"));
        String phone =  String.valueOf(session.getAttribute("phone"));

        if (account == null) {
            response.sendRedirect("Login");
            return;
        }
        OrdersService ordersService = new OrdersServiceImpl();
        List<Order> orderList = ordersService.getOrder(account, phone);
        String ordersJson = new Gson().toJson(orderList);
        session.setAttribute("ordersJson", ordersJson);
        request.getRequestDispatcher(MEMBER_URL).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
