package servlet.customization;

import bean.product.Product;
import service.cart.CartService;
import service.cart.CartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        name = "CustomizationServlet",
        urlPatterns = {"/Customization"}
)
public class CustomizationServlet extends HttpServlet {
    private static final String CUSTOMIZATION_URL = "WEB-INF/jsp/customization/Customization.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String productName =  String.valueOf(session.getAttribute("ProductName"));
        String account =  String.valueOf(session.getAttribute("account"));
        if (productName == null || account == null) {
            response.sendRedirect("Login");
            return;
        }
        request.setAttribute("productName", productName);

        request.getRequestDispatcher(CUSTOMIZATION_URL).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account =  String.valueOf(request.getSession().getAttribute("account"));
        String phone =  String.valueOf(request.getSession().getAttribute("phone"));
        String productName = request.getParameter("productName");
        String size = request.getParameter("size");
        String sugar = request.getParameter("sugar");
        String ice = request.getParameter("ice");
        String quantity = request.getParameter("quantity");

        Product product = new Product();
        product.setName(productName);
        if (size.equals("中杯")) {
            product.setSize("M");
        } else if (size.equals("大杯")) {
            product.setSize("L");
        }
        product.setSugar(sugar);
        product.setIce(ice);
        product.setQuantity(Integer.parseInt(quantity));

        CartService cartService = new CartServiceImpl();
        cartService.addProduct(account, phone, product);
    }
}
