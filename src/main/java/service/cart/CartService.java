package service.cart;

import bean.product.Product;

import java.util.List;

public interface CartService {
    public void addProduct(String account, String phone, Product product);
    public void deleteProduct(String account, String phone, Integer productID);
    public List<Product> showCart(String account, String phone);

}
