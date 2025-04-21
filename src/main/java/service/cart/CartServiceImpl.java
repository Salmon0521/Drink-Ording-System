package service.cart;

import bean.product.Product;
import dao.build.BuildDAO;
import dao.cart.CartDAO;
import dao.user.UserDAO;
import dao.product.ProductDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{
    private final ProductDAO productDAO;
    private final UserDAO userDAO;
    private final CartDAO cartDAO;
    private final BuildDAO buildDAO;

    public CartServiceImpl(ProductDAO productDAO, UserDAO userDAO, CartDAO cartDAO, BuildDAO buildDAO) {
        this.productDAO = productDAO;
        this.userDAO = userDAO;
        this.cartDAO = cartDAO;
        this.buildDAO = buildDAO;
    }

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
