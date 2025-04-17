package service.orders;

import bean.order.Order;
import bean.product.Product;
import dao.build.BuildDAO;
import dao.build.BuildDAOImpl;
import dao.user.UserDAO;
import dao.user.UserDAOImpl;
import dao.orders.OrdersDAO;
import dao.orders.OrdersDAOImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class  OrdersServiceImpl implements OrdersService{
    private final UserDAO userDAO = new UserDAOImpl();
    private final OrdersDAO ordersDAO = new OrdersDAOImpl();
    private final BuildDAO buildDAO = new BuildDAOImpl();


    @Override
    public List<Product> getOrdersByDate(String account, String phone, String date){
        Integer customerID = userDAO.getUserID(account, phone);
        return ordersDAO.getOrdersByDate(customerID, date);
    }

    @Override
    public List<Order> getOrder(String account, String phone){
        Integer customerID = userDAO.getUserID(account, phone);
        return ordersDAO.getOrders(customerID);

    }

    @Override
    public void buildOrder(String account, String phone, Integer amount){
        Integer customerID = userDAO.getUserID(account, phone);
        Integer orderID = buildDAO.getOrderID(customerID, 1).get(0);

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dates = dateFormat.format(now);

        ordersDAO.update(orderID, dates, amount,0);
        orderID = ordersDAO.initial();
        buildDAO.insert(customerID, orderID);
    }
}
