package service.cart;

import bean.product.Product;
import com.google.gson.Gson;
import dao.build.BuildDAO;
import dao.build.BuildDAOImpl;
import dao.cart.CartDAO;
import dao.cart.CartDAOImpl;
import dao.user.UserDAO;
import dao.user.UserDAOImpl;
import dao.product.ProductDAO;
import dao.product.ProductDAOImpl;

import javax.servlet.http.HttpSession;
import java.util.List;

public class CartServiceImpl implements CartService{
    private final ProductDAO productDAO = new ProductDAOImpl();
    private final UserDAO userDAO = new UserDAOImpl();
    private final CartDAO cartDAO = new CartDAOImpl();
    private final BuildDAO buildDAO = new BuildDAOImpl();

    @Override
    public void addProduct(String account, Product product){
        Integer productID = productDAO.getProductID(product);
        Integer customerID = userDAO.getCustomerID(account);
        Integer orderID = buildDAO.getOrderID(customerID, 1).get(0);
        Integer currentQuantity = cartDAO.productExist(orderID, productID);
        if (currentQuantity != null) {
            cartDAO.updateQuantity(orderID, productID, product.getQuantity() + currentQuantity);
        } else{
            cartDAO.insert(orderID, productID, product.getQuantity());
        }
    }

    @Override
    public void deleteProduct(String account, Integer productID){
        Integer customerID = userDAO.getCustomerID(account);
        Integer orderID = buildDAO.getOrderID(customerID, 1).get(0);
        cartDAO.deleteProduct(orderID, productID);
    }

    @Override
    public void showCart(HttpSession session, String account){
        Integer customerID = userDAO.getCustomerID(account);
        Integer orderID = buildDAO.getOrderID(customerID, 1).get(0);
        List<Product> customerCart= cartDAO.getProduct(orderID);
        Integer totalPrice = 0;
        for (Product product : customerCart) {
            totalPrice += product.getPrice();
        }
        String customerCartJson = new Gson().toJson(customerCart);
        session.setAttribute("customerCart", customerCartJson);
        session.setAttribute("totalPrice", totalPrice);
    }
}
