package servlet.products;

import bean.product.Product;
import com.google.gson.Gson;
import service.product.ProductService;
import service.product.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(
        name = "HandServlet",
        urlPatterns = {"/Hand"}
)
public class HandServlet extends HttpServlet {
    private static final String CUSTOMER_URL = "WEB-INF/jsp/products/Hand.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String account = String.valueOf(session.getAttribute("account"));
        if (account == null) {
            response.sendRedirect("Login");
            return;
        }

        ProductService productService = new ProductServiceImpl();
        List<Product> hand = productService.showProducts("Boutique hand coffee");
        String handJson = new Gson().toJson(hand);
        request.setAttribute("json", handJson);

        request.getRequestDispatcher(CUSTOMER_URL).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String productName = request.getParameter("productName");
        session.setAttribute("ProductName", productName);
    }
}
