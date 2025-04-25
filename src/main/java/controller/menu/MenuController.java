package controller.menu;

import bean.product.Product;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import service.cart.CartService;
import service.customer.CustomerService;
import service.orders.OrdersService;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@RestController
public class MenuController {

    @Autowired
    private final CustomerService customerService;

    @Autowired
    private final CartService cartService;

    @Autowired
    private final OrdersService ordersService;

    public MenuController(CustomerService customerService, CartService cartService, OrdersService ordersService) {
        this.customerService = customerService;
        this.cartService = cartService;
        this.ordersService = ordersService;
    }

    @GetMapping("/CustomerMenu")
    public ModelAndView showMenuView(HttpSession session) {
        String account = String.valueOf(session.getAttribute("account"));
        String phone =  String.valueOf(session.getAttribute("phone"));

        if (account == null) {
            return new ModelAndView("redirect:/Login");
        }

        List<Product> customerCart = cartService.showCart(account, phone);
        int totalPrice = customerCart.stream().mapToInt(Product::getPrice).sum();

        session.setAttribute("customerCart", new Gson().toJson(customerCart));
        session.setAttribute("totalPrice", totalPrice);

        return new ModelAndView("customer/Menu");
    }

    @PostMapping("/CustomerMenu")
    public ModelAndView handleCartActions(
            @RequestParam("ud") String ud,
            @RequestParam(value = "amount", required = false) Integer amount,
            @RequestParam(value = "productID", required = false) Integer productID,
            HttpSession session) {

        String account = (String) session.getAttribute("account");
        String phone = (String) session.getAttribute("phone");

        switch (ud) {
            case "sendOrder":
                ordersService.buildOrder(account, phone, amount);
                String level = customerService.updateLevel(account, phone);
                session.setAttribute("level", level);
                break;
            case "delete":
                cartService.deleteProduct(account, phone, productID);
                break;
            default:
                throw new IllegalArgumentException("invalid action: " + ud);
        }

        return new ModelAndView("redirect:/CustomerMenu");
    }
}
