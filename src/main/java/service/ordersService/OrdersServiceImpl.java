package service.ordersService;

import bean.orders.Orders;
import bean.product.Product;
import com.google.gson.Gson;
import dao.build.BuildDAO;
import dao.build.BuildDAOImpl;
import dao.cart.CartDAO;
import dao.cart.CartDAOImpl;
import dao.customer.CustomerDAO;
import dao.customer.CustomerDAOImpl;
import dao.orders.OrdersDAO;
import dao.orders.OrdersDAOImpl;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class  OrdersServiceImpl implements OrdersService{
    private final CustomerDAO customerDAO = new CustomerDAOImpl();
    private final OrdersDAO ordersDAO = new OrdersDAOImpl();
    private final CartDAO cartDAO = new CartDAOImpl();
    private final BuildDAO buildDAO = new BuildDAOImpl();


    @Override
    public void getOrdersByDate(HttpSession session, String date){
        String account = (String) session.getAttribute("account");
        Integer customerID = customerDAO.getCustomerID(account);
        List<Product> productList = ordersDAO.getOrdersByDate(customerID, date);
        String productInorderJson = new Gson().toJson(productList);
        session.setAttribute("productInorderJson", productInorderJson);
    }

    @Override
    public void getOrder(HttpSession session, String account){
        Integer customerID = customerDAO.getCustomerID(account);
        List<Orders> ordersList = ordersDAO.getOrders(customerID);
        String ordersJson = new Gson().toJson(ordersList);
        session.setAttribute("ordersJson", ordersJson);
    }

    @Override
    public void buildOrder(String account, Integer amount){
        Integer customerID = customerDAO.getCustomerID(account);
        Integer orderID = buildDAO.getOrderID(customerID, 1).get(0);

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dates = dateFormat.format(now);

        ordersDAO.update(orderID, dates, amount,0);
        orderID = ordersDAO.initial();
        buildDAO.insert(customerID, orderID);
    }
}
