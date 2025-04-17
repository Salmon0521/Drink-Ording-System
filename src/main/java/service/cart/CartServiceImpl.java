package service.cart;

import bean.product.Product;
import dao.build.BuildDAO;
import dao.build.BuildDAOImpl;
import dao.cart.CartDAO;
import dao.cart.CartDAOImpl;
import dao.user.UserDAO;
import dao.user.UserDAOImpl;
import dao.product.ProductDAO;
import dao.product.ProductDAOImpl;

import java.util.List;

public class CartServiceImpl implements CartService{
    private final ProductDAO productDAO = new ProductDAOImpl();
    private final UserDAO userDAO = new UserDAOImpl();
    private final CartDAO cartDAO = new CartDAOImpl();
    private final BuildDAO buildDAO = new BuildDAOImpl();

    @Override
    public void addProduct(String account, String phone, Product product){
        Integer productID = productDAO.getProductID(product);
        Integer customerID = userDAO.getUserID(account, phone);
        Integer orderID = buildDAO.getOrderID(customerID, 1).get(0);

        Integer currentQuantity = cartDAO.productExist(orderID, productID);
        if (currentQuantity != null) {
            cartDAO.updateQuantity(orderID, productID, product.getQuantity() + currentQuantity);
        } else{
            cartDAO.insert(orderID, productID, product.getQuantity());
        }
    }

    @Override
    public void deleteProduct(String account, String phone, Integer productID){
        Integer customerID = userDAO.getUserID(account, phone);
        Integer orderID = buildDAO.getOrderID(customerID, 1).get(0);
        cartDAO.deleteProduct(orderID, productID);
    }

    @Override
    public List<Product> showCart (String account, String phone){
        Integer customerID = userDAO.getUserID(account, phone);
        Integer orderID = buildDAO.getOrderID(customerID, 1).get(0);

        return cartDAO.getProduct(orderID);
    }
}
