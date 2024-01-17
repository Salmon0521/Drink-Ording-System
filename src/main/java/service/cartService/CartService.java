package service.cartService;

import bean.product.Product;

import javax.servlet.http.HttpSession;

public interface CartService {
    public void addProduct(String account, Product product);
    public void deleteProduct(String account, Integer productID);
    public void showCart(HttpSession session, String account);

}
