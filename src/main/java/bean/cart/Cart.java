package bean.cart;

public class Cart {
    private Integer cartID;
    private Integer customerID;
    private Integer productID;
    private Integer quantity;

    public Cart() {
    }

    public Integer getBuildID() { return cartID; }

    public void setCartID(Integer cartID) { this.cartID = cartID; }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Integer getQuantity() { return quantity; }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
