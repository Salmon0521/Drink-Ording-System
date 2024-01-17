package servlet.orderRecord;

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
        name = "OrderRecordServlet",
        urlPatterns = {"/OrderRecord"}
)
public class OrderRecordServlet extends HttpServlet {
    private static final String ORDERRECORD_URL = "WEB-INF/jsp/orderRecord/OrderRecord.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String account = (String) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect("Login");
            return;
        }
        request.getRequestDispatcher(ORDERRECORD_URL).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String date = request.getParameter("date");
        session.setAttribute("date", date);
        OrdersService ordersService = new OrdersServiceImpl();
        ordersService.getOrdersByDate(session, date);
    }
}
