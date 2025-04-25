package controller.order;

import bean.product.Product;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import service.orders.OrdersService;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@RestController
public class OrderRecordController {

    @Autowired
    private final OrdersService ordersService;

    public OrderRecordController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping("/OrderRecord")
    public ModelAndView showOrderRecordView(HttpSession session) {
        String account = String.valueOf(session.getAttribute("account"));
        if (account == null) {
            return new ModelAndView("redirect:/Login");
        }
        return new ModelAndView("orderRecord/OrderRecord");
    }

    @PostMapping("/OrderRecord")
    public ModelAndView handleOrderRecord(@RequestParam("date") String date, HttpSession session) {
        String account = String.valueOf(session.getAttribute("account"));
        String phone =  String.valueOf(session.getAttribute("phone"));

        if (account == null) {
            return new ModelAndView("redirect:/Login");
        }
        session.setAttribute("date", date);

        List<Product> productList = ordersService.getOrdersByDate(account, phone, date);
        String productInorderJson = new Gson().toJson(productList);
        session.setAttribute("productInorderJson", productInorderJson);
        return new ModelAndView("orderRecord/OrderRecord");
    }
}
