package controller.member;

import bean.order.Order;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import service.orders.OrdersService;
import service.orders.OrdersServiceImpl;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@RestController
public class MemberController {

    @GetMapping("/Member")
    public ModelAndView showMemberView(HttpSession session) {
        String account = String.valueOf(session.getAttribute("account"));
        String phone =  String.valueOf(session.getAttribute("phone"));
        if (account == null) {
            return new ModelAndView("redirect:/Login");
        }
        OrdersService ordersService = new OrdersServiceImpl();
        List<Order> orderList = ordersService.getOrder(account, phone);
        String ordersJson = new Gson().toJson(orderList);
        session.setAttribute("ordersJson", ordersJson);
        return new ModelAndView("member/Member");
    }
}
